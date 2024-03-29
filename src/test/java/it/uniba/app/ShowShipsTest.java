package it.uniba.app;

import static it.uniba.app.App.showShips;
import static it.uniba.app.OutputHandler.captureOutput;
import static it.uniba.app.App.setMatch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertTrue;


/*
 * ShowShipsTest - Test class used to check
 * the behaviour of the "showShips()" method
 * of the class "App" under certain conditions
*/
class ShowShipsTest {

    /**
     * Before all tests, the match is set to "null"
     * to terminate all started matches.
     */
    @BeforeAll
    static void setUp() {
        setMatch(null);
    }

    /**
     * Tests the method {@link App#showShips()} to check
     * if the app prevents the user from seeing
     * the current state of the ships,
     * if the match has not started yet.
     */
    @Test
    @DisplayName("Check that ships are not shown if the game has not started yet.")
    void testShowShipsMatchNull() {
        final int cr = 13;
        String expectedOutput = "Non e' in esecuzione nessuna partita!\nDigita /gioca per iniziare una nuova partita!";

        /*
         * The "replace" method is used to remove control characters
         * which may prevent the result string from being compared
         * correctly with the expected output.
         */
        String output = captureOutput(() -> showShips()).replace((char) (cr) + "\n", "\n");
        assertTrue(expectedOutput.contains(output),
            "The current state of the ships should not be revealed, "
            + "because the match hasn't started yet.");
    }
}
