package it.uniba.app;

import static it.uniba.app.App.exit;
import static it.uniba.app.App.isRunning;
import static it.uniba.app.App.setRunning;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * ExitTest - Test class used to verify that the
 * "exit()"" function of class "App" behaves as expected.
 */
class ExitTest {

    /**
     * After each test, the state of the game
     * is set as "running".
     */
    @AfterEach
    void reset() {
        setRunning(true);
    }

    /**
     * Tests the method {@link App#isRunning()}
     * to check if, after inserting "s", the
     * app terminates as expected.
     */
    @Test
    @DisplayName("Check if the app is terminated after successful input.")
    void testExitYes() {
        Shell.setInput("s");
        exit();
        assertFalse(isRunning(), "The app must terminate.");
    }

    /**
     * Tests the method {@link App#isRunning()}
     * to check if, after inserting "n", the
     * app continues to work as expected.
     */
    @Test
    @DisplayName("Check if the app stays active after successful input.")
    void testExitNo() {
        Shell.setInput("n");
        exit();
        assertTrue(isRunning(), "The app must not terminate.");
    }

}
