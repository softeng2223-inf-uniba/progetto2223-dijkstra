package it.uniba.app;

/**
 * .
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

    private long getRemainingTime() {
        return (long) (gameTime - getElapsedTime());
    }

    /**
    * Returns true if the time of the game is over.
    * @return boolean - true if the game is over.
    */
    public boolean isTimeOver() {
        return getRemainingTime() <= 0;
    }

    /**
     * Shows the game time.
     */
    public String showGameTime() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tempo trascorso: " + getElapsedTime() + " m");
        sb.append("\n");
        sb.append("Tempo rimanente: " + getRemainingTime() + " m");
        sb.append("\n");
        return sb.toString();
    }
}
