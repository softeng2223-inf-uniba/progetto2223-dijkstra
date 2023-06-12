package it.uniba.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * << Entity >>:
 * Class that represents a match of the game.
 */
public final class Match {
    private Map map;
    private Difficulty currentDifficulty;
    private int numberOfFailedAttempts;
    private int numberOfAttempts;
    private Timer timer;
    private int numberOfAliveShips;

    /**
     * Match constructor.
     * @param level - sets the current difficulty
     */
    public Match(final MapType mapType, final Difficulty.Level level, final int maxMinutes) {
        map = new Map(mapType);
        currentDifficulty = new Difficulty(level);
        numberOfFailedAttempts = 0;
        numberOfAttempts = 0;
        timer = new Timer(maxMinutes);
        numberOfAliveShips = Map.TOTAL_SHIPS_NUMBER;
    }

    /**
     * Match copy constructor.
     * @param inMatch - the Match that needs to be copied to this object
     */
    public Match(final Match inMatch) {
        map = new Map(inMatch.getMap().getMapType());
        currentDifficulty = new Difficulty(inMatch.getCurrentDifficulty().getCurrentLevel());
        numberOfFailedAttempts = 0;
        numberOfAttempts = 0;
        timer = new Timer(((int) inMatch.getTimer().getMaxMinutes()));
        numberOfAliveShips = Map.TOTAL_SHIPS_NUMBER;
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
     * Returns true if the game is over, false otherwise.
     * @return boolean
     */
    public boolean isGameOver() {
        if (numberOfFailedAttempts >= currentDifficulty.getCurrentMaxFailures()
            || timer.isTimeOver() || numberOfAliveShips <= 0) {
                return true;
            }
        return false;
    }

    public boolean isTimeOver() {
        return timer.isTimeOver();
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

        if (!timer.isTimeOver()) {
            if (isWithinBounds(move)) {
                /*
                 * Hit the cell (if not already done)
                 * and check if there is a ship in it.
                */
                Cell hitCell = map.getCell(row, column);

                if (!hitCell.isHit()) {
                    hitCell.hit();
                    incrementAttempts();

                    if (hitCell.getShip() == null) {
                        message.append(
                            ANSICodes.FCYAN
                            + "\nacqua"
                            + ANSICodes.RESET + "\n");
                        incrementFailedAttempts();
                    } else {
                        if (hitCell.getShip().isSunken()) {
                            numberOfAliveShips--;
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
                    if (numberOfFailedAttempts >= currentDifficulty.getCurrentMaxFailures()) {
                        message.append("E' stato raggiunto il numero massimo di tentativi falliti!\nHai perso!\n");
                    } else if (numberOfAliveShips <= 0) {
                        message.append("Complimenti!\nHai vinto!\n");
                    }
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
        }

        // Shows the main information of the match
        message.append("Tentativi: " + String.valueOf(getNumberOfAttempts()) + "\n\n");
        message.append(timer.getGameTime());

        /*
        * Displays a different message, depending
        * on the success of the attempt
        */
        if (!isGameOver()) {
            message.append(map.getMapGrid() + "\n");
        }

        return message.toString();
    }

    /**
     * Returns the current map.
     * @return
     */
    public Map getMap() {
        if (map != null) {
            return new Map(map);
        } else {
            return null;
        }
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

    public Timer getTimer() {
        return timer;
    }
}
