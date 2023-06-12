package it.uniba.app;

import static it.uniba.app.App.setDifficulty;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*
 * SetDifficultyTest - Test class used to verify that
 * the methods for checking the properties of game
 * difficulty have been defined correctly
 */
class SetDifficultyTest {

    /**
     * Tests the methods {@link App#setDifficulty(String[], Difficulty)}
     * and {@link Difficulty#getCurrentLevel()} to verify that the
     * user input difficulty is set as expected.
     */
    @Test
    @DisplayName("Check if the new difficulty is set correctly.")
    void testLevelInizialization() {
        String[] command = {"/facile", "25"};
        Difficulty difficulty = new Difficulty(Difficulty.Level.EASY);

        setDifficulty(command, difficulty);
        assertEquals(Difficulty.Level.EASY, difficulty.getCurrentLevel(),
        "The current level should match the user input level.");
    }

    /**
     * Tests the methods {@link Difficulty#setHardMaxFailures(int)}
     * and {@link Difficulty#getHardMaxFailures()} to check that
     * the new maximum number of failed attempts is set correctly
     * to the chosen difficulty.
     */
    @Test
    @DisplayName("Check if the total attempts of a difficulty are correctly changed.")
    void testTotalAttemptsInizialization() {
        final int totalAttempts = 10;

        Difficulty.setHardMaxFailures(totalAttempts);
        assertEquals(totalAttempts, Difficulty.getHardMaxFailures(),
        "The maximum number of failed attempts should be changed,"
        + "according to the user input.");
    }

    /**
     * Uses the methods {@link App#setDifficulty(String[], Difficulty)}
     * and {@link Match#getCurrentDifficulty()} to check if the app
     * prevents the user from changing the current
     * difficulty after starting the game.
     */
    @Test
    @DisplayName("Check that the difficulty is not changed after the game starts.")
    void testSetDifficultyWhenGameHasAlreadyStarted() {
        String[] command = {"/difficile"};
        Difficulty difficulty = new Difficulty(Difficulty.Level.HARD);

        // Assume a game has already started
        Match match = new Match(MapType.LARGE, Difficulty.Level.MEDIUM, Integer.MAX_VALUE);

        setDifficulty(command, difficulty);

        assertNotEquals(match.getCurrentDifficulty().getCurrentLevel(),
            difficulty.getCurrentLevel(), "The difficulty should not "
            + "change as the game has already started.");
    }
}
