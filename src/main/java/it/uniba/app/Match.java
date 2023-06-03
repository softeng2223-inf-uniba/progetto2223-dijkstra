package it.uniba.app;

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

}
