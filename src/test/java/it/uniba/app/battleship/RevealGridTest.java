package it.uniba.app.battleship;

import static it.uniba.app.App.revealGrid;
import static it.uniba.app.utils.OutputHandler.captureOutput;
import static it.uniba.app.App.setMatch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;

/*
 * RevealGridTest - Test class used to check
 * the behaviour of the "revealGrid()" method
 * of the class "App" under certain conditions
*/
class RevealGridTest {

    /**
     * Before all tests, the match is set to "null"
     * to terminate all started matches.
     */
    @BeforeAll
    static void setUp() {
        setMatch(null);
    }

    /**
     * Tests the method {@link App#revealGrid()} to check
     * if the app prevents the user from seeing the grid,
     * if the match has not started yet.
     */
    @Test
    @DisplayName("Check if the grid hasn't been revealed, if the match hasn't started yet.")
    void testRevealGridMatchNull() {
        final int cr = 13;
        String expectedOutput = "La partita non e' ancora iniziata.\nDigita /gioca per iniziare una nuova partita!";

        /*
         * The "replace" method is used to remove control characters
         * which may prevent the result string from being compared
         * correctly with the expected output.
         */
        String output = captureOutput(() -> revealGrid())
                        .replace((char) (cr) + "\n", "\n");

        assertTrue(expectedOutput.contains(output),
            "The grid should not be revealed,"
            + "because the match hasn't started yet.");
    }
}
