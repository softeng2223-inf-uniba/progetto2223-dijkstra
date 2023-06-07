package it.uniba.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that represents a match of the game.
 */
public final class Match {
    private Map map;
    private Difficulty currentDifficulty;
    private int numberOfFailedAttempts;
    private int numberOfAttempts;
    // timer

    /**
     * Match constructor.
     * @param level - sets the current difficulty
     */
    public Match(final MapType mapType, final Difficulty.Level level) {
        map = new Map(mapType);
        currentDifficulty = new Difficulty(level);
        numberOfFailedAttempts = 0;
        numberOfAttempts = 0;
    }

    /**
     * Checks that both the letter and the number inserted by
     * the user is within the bounds of the map.
     * @param move
     */
    public boolean isWithinBounds(final String[] move) {
        final int ten = 10;

        int maxNumber = map.getMapType().getSize();
        int tenMultiples = (maxNumber / ten) - 1;
        int tenModule = maxNumber % ten;
        int i;

        // Initialize with regex to match letters
        StringBuilder boundsRegex = new StringBuilder("[a-" + (char) (maxNumber + (int) 'a' - 1) + "]-");

        // Add numbers check to regex
        boundsRegex.append("(0?[1-9]");
        for (i = 0; i < tenMultiples; i++) {
            boundsRegex.append("|" + (i + 1) + "[0-9]");
        }

        boundsRegex.append("|" + (i + 1) + "[0-" + tenModule + "])");

        Pattern pattern = Pattern.compile(boundsRegex.toString(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(String.join("-", move));

        return matcher.matches();
    }

    /**
     * Make an attempt for a move
     * on a specific cell of the map.
     * @param command
     */
    public String makeMove(final String command) {
        StringBuilder message = new StringBuilder("");
        String[] move = command.split("\\-");
        int row = Integer.parseInt(move[1]) - 1;
        int column = (move[0].toUpperCase()).charAt(0) - 'A';

        if (isWithinBounds(move)) {
            /*
             * Hit the cell (if not already done)
             * and check if there is a ship in it.
            */
            Cell hitCell = map.getCell(row, column);
            if (!hitCell.isHit()) {
                hitCell.hit();
                incrementAttempts();

                /*
                * Displays a different message, depending
                * on the success of the attempt
                */
                message.append(map.getMapGrid() + "\n");

                if (hitCell.getShip() == null) {
                    message.append(
                        ANSICodes.FCYAN
                        + "\nacqua"
                        + ANSICodes.RESET + "\n");
                    incrementFailedAttempts();
                } else {
                    hitCell.getShip().hit();

                    if (hitCell.getShip().isSunken()) {
                        message.append(
                            ANSICodes.FGREEN
                            + "\ncolpito e affondato"
                            + ANSICodes.RESET + "\n");
                    } else {
                        message.append(
                            ANSICodes.FYELLOW
                            + "\ncolpito"
                            + ANSICodes.RESET + "\n");
                    }
                }

                // Shows the main information of the match
                message.append("Tentativi: " + String.valueOf(getNumberOfAttempts()) + "\n\n");
            } else {
                message.append(
                "La cella e' stata gia' selezionata. \nRiprova.\n\n");
            }

        } else {
            message.append(
                "Il comando inserito contiene caratteri al di fuori della mappa.\n");
            message.append(
                "Rispettare le dimensioni della mappa specificate.\n");
        }

        return message.toString();
    }

    public Map getMap() {
        return new Map(map);
    }

    public void setCurrentDifficulty(final Difficulty.Level level) {
        currentDifficulty = new Difficulty(level);
    }

    public int getNumberOfFailedAttempts() {
        return numberOfFailedAttempts;
    }

    public Difficulty getCurrentDifficulty() {
        return new Difficulty(currentDifficulty.getCurrentLevel());
    }

    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    /**
     * Increments the total number of
     * attempts made by the user.
     */
    public void incrementAttempts() {
        numberOfAttempts++;
    }

    /**
     * Increments the number of failed
     * attempts done by the user.
     */
    public void incrementFailedAttempts() {
        numberOfFailedAttempts++;
    }

}
