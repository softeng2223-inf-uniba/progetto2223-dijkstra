package it.uniba.app;

import static it.uniba.app.App.showGrid;
import static it.uniba.app.OutputHandler.captureOutput;
import static it.uniba.app.App.setMatch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * ShowGridTest - Test class used to check
 * the behaviour of the "showGrid()" method
 * of the class "App" under certain conditions
*/
class ShowGridTest {

    /**
     * Before all tests, the match is set to "null"
     * to terminate all started matches.
     */
    @BeforeAll
    static void setUp() {
        setMatch(null);
    }

    /**
     * Tests the method {@link App#showGrid()} to check
     * if the app prevents the user from seeing the
     * current grid, if the match has not started yet.
     */
    @Test
    @DisplayName("Check if the grid hasn't been shown, if the match hasn't started yet.")
    void testShowGridMatchNull() {
        final int cr = 13;
        String expectedOutput = "Non e' in esecuzione nessuna partita!\nDigita /gioca per iniziare una nuova partita!";

        /*
         * The "replace" method is used to remove control characters
         * which may prevent the result string from being compared
         * correctly with the expected output.
         */
        String output = captureOutput(() -> showGrid())
                        .replace((char) (cr) + "\n", "\n");

        assertTrue(expectedOutput.contains(output),
            "The current grid should not be revealed, "
            + "because the match hasn't started yet.");
    }
}

