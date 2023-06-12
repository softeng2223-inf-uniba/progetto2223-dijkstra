package it.uniba.app;

import static it.uniba.app.App.showLevel;
import static it.uniba.app.OutputHandler.captureOutput;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.uniba.app.Difficulty.Level;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * ShowLevelTest - Test class used to check
 * that the information of the "showLevel()" method
 * in the class "App" is displayed correctly.
*/
class ShowLevelTest {

    /**
     * Test the method {@link App#showLevel(Difficulty)}
     * to check if the current difficulty information
     * and the maximum number of attempts is displayed correctly.
     */
    @Test
    @DisplayName("Check if the information about the current"
                + "difficulty is displayed correctly.")
    void testShowMediumDifficulty() {

        final int totalAttempts = 5;
        final int cr = 13;

        Difficulty difficulty = new Difficulty(Level.MEDIUM);
        Difficulty.setMediumMaxFailures(totalAttempts);

        String expectedOutput = "Difficolta': " + difficulty.getName() + "\n"
        + "Max. Tentativi Falliti: " + difficulty.getCurrentMaxFailures();

        /*
         * The "replace" method is used to remove control characters
         * which may prevent the result string from being compared
         * correctly with the expected output.
         */
        String output = captureOutput(() -> showLevel(difficulty))
                        .replace((char) (cr) + "\n", "\n");

        assertTrue(expectedOutput.toString().compareTo(output) == 0,
                    "The difficulty and the maximum number of "
                    + "failed attempts should be updated as expected.");
    }
}

