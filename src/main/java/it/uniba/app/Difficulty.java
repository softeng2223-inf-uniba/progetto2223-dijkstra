package it.uniba.app;

/**
 * Class that represents the game difficulty.
 */
public final class Difficulty {
    public static final int NUMBER_OF_DIFFICULTIES = 3;

    public static final int DEFAULT_EASY_MAX_FAILURES = 50;
    public static final int DEFAULT_MEDIUM_MAX_FAILURES = 30;
    public static final int DEFAULT_HARD_MAX_FAILURES = 10;

    private static int[] difficulties = {DEFAULT_EASY_MAX_FAILURES,
        DEFAULT_MEDIUM_MAX_FAILURES, DEFAULT_HARD_MAX_FAILURES};

    /**
     * Enumeration that represents all difficulties levels implemented.
     */
    public enum Level {
        EASY, MEDIUM, HARD
    }

    private Level currentLevel;

    /**
     * Constructor for Difficulty class.
     * @param level - sets the current difficulty level.
     */
    public Difficulty(final Level level) {
        currentLevel = level;
    }

    public void setCurrentLevel(final Level level) {
        currentLevel = level;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public int getCurrentMaxFailures() {
        return difficulties[currentLevel.ordinal()];
    }

    public static int getEasyMaxFailures() {
        return difficulties[Level.EASY.ordinal()];
    }

    public static void setEasyMaxFailures(final int easyMaxFailures) {
        difficulties[Level.EASY.ordinal()] = easyMaxFailures;
    }

    public static int getMediumMaxFailures() {
        return difficulties[Level.MEDIUM.ordinal()];
    }

    public static void setMediumMaxFailures(final int mediumMaxFailures) {
        difficulties[Level.MEDIUM.ordinal()] = mediumMaxFailures;
    }

    public static int getHardMaxFailures() {
        return difficulties[Level.HARD.ordinal()];
    }

    public static void setHardMaxFailures(final int hardMaxFailures) {
        difficulties[Level.HARD.ordinal()] = hardMaxFailures;
    }

    /**
     * .
     * @return
     */
    public String getName() {
        switch (currentLevel) {
            case EASY:
                return "Facile";
            case MEDIUM:
                return "Medio";
            case HARD:
                return "Difficile";
            default:
                return null;
        }
    }
}
