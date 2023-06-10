package it.uniba.app.battleship;

import static it.uniba.app.App.showShips;
import static it.uniba.app.utils.OutputHandler.captureOutput;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * ShowShipsTest - Test class used to check
 * the behaviour of the "showShips()" method
 * of the class "App" under certain conditions
*/
class ShowShipsTest {

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

        assertTrue(expectedOutput.toString().compareTo(output) == 0,
            "The current state of the ships should not be revealed, "
            + "because the match hasn't started yet.");
    }
}
