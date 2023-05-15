package it.uniba.app;
/**
 * Enum class for the match difficulty.
 */
public enum Difficulty {
    EASY(50),
    MEDIUM(30),
    HARD(10);

    private final int maxFailures;

    Difficulty(final int inMaxFailures) {
        this.maxFailures = inMaxFailures;
    }

    public int getMaxFailures() {
        return maxFailures;
    }

}
