package it.uniba.app;

/**
 * << Entity >>:
 * Class to manage the game time.
 */
public class Timer {
    private long initialTime;
    private long gameTime;
    private static final double NANOTIME_TO_SECONDS = 1e9;
    private static final double SECONDS_TO_MINUTES = 60;

    /**
     * Timer constructor.
     * @param maxMinuteString
     */
    public Timer(final int maxMinuteString) {
        gameTime = maxMinuteString;
        initialTime = 0;
    }

    /**
     * Starts the timer.
     */
    public void startTimer() {
        initialTime = System.nanoTime();
    }

    private long getElapsedTime() {
        return (long) (((System.nanoTime() - initialTime) / NANOTIME_TO_SECONDS) / SECONDS_TO_MINUTES);
    }

    /**
     * Returns the remaining time of the match.
     * @return
     */
    public long getRemainingTime() {
        long remainingTime = gameTime - getElapsedTime();
        if (remainingTime < 0) {
            remainingTime = 0;
        }
        return remainingTime;
    }

    /**
    * Returns true if the time of the game is over.
    * @return boolean - true if the game is over.
    */
    public boolean isTimeOver() {
        return getRemainingTime() <= 0;
    }

    /**
     * Returns a string representing the game time.
     * @return String - represents the game time stats.
     */
    public String getGameTime() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tempo trascorso: " + getElapsedTime() + " m");
        sb.append("\n");
        sb.append("Tempo rimanente: " + getRemainingTime() + " m");
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Returns the maximum number of minutes of the game.
     * @return
     */
    public long getMaxMinutes() {
        return gameTime;
    }
}
