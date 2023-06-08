package it.uniba.app;

/**
 * .
 */
public class Timer {
    private long initialTime = 0;
    private long gameTime = 0;
    private static final double NANOTIME_TO_SECONDS = 1e9;
    private static final double SECONDS_TO_MINUTES = 60;

    /**
     * Timer constructor.
     * @param maxMinuteString
     */
    public Timer(final int maxMinuteString) {
        gameTime = maxMinuteString;
    }

    /**
     * Starts the timer.
     */
    public void startTimer() {
        initialTime = System.nanoTime();
        System.out.println(initialTime);
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
    public void showGameTime() {
        Shell.printlnMessage("Tempo trascorso: " + getElapsedTime() + " m");
        Shell.printlnMessage("Tempo rimanente: " + getRemainingTime() + " m");
    }
}
