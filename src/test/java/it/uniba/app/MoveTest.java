package it.uniba.app;

import static it.uniba.app.App.isAMove;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * MoveTest - Test class used to verify that the program
 * correctly uses the regular expressions defined for a move
 */
class MoveTest {

    private static Match match;

    /**
     * Initialize the Match object before all tests.
     */
    @BeforeAll
    static void setUp() {
        match = new Match(MapType.LARGE, Difficulty.Level.MEDIUM, Integer.MAX_VALUE);
    }

    /**
     * Uses the method {@link App#isAMove(String)}
     * to check if the user inputs are
     * considered as a valid command.
     */
    @Test
    @DisplayName("Check if the user input is a valid command.")
    void testValidCommand() {
        String command = "a-5";
        assertTrue(isAMove(command), "The command must be considered "
        + "as a valid move.");
    }

    @Test
    @DisplayName("Check if the user input is an invalid command.")
    void testInvalidCommand() {
        String command = "c--7";
        assertFalse(isAMove(command), "The command must be considered "
        + "as an invalid move.");
    }

    @Test
    @DisplayName("Check if an input with two numbers is a valid command.")
    void testNumberAsFirstParam() {
        String command = "4-6";
        assertFalse(isAMove(command), "The method should not accept "
        + "a number as the first parameter.");
    }

    @Test
    @DisplayName("Check if an input with two characters is a valid command.")
    void testCharacterAsSecondParam() {
        String command = "a-b";
        assertFalse(isAMove(command), "The method should not accept "
        + "a letter as the second parameter.");
    }

    /**
     * Tests the method {@link Match#isWithinBounds(String[])}
     * to check if the input commands
     * can be labeled as valid moves.
     */
    @Test
    @DisplayName("Check if the user input is a valid move.")
    void testValidMoveWithinBounds() {
        String[] move = {"a", "1"};
        boolean result = match.isWithinBounds(move);

        assertTrue(result, "The move should be considered valid.");
    }

    @Test
    @DisplayName("Check if the method recognizes the invalid letter in the input.")
    void testInvalidMoveWithInvalidLetter() {
        String[] move = {"z", "5"};
        boolean result = match.isWithinBounds(move);

        assertFalse(result, "Method shouldn't accept a move"
        + "with a letter outside the bounds of the map.");
    }

    @Test
    @DisplayName("Check if the method recognizes the invalid number in the input.")
    void testInvalidMoveWithInvalidNumber() {
        String[] move = {"d", "24"};
        boolean result = match.isWithinBounds(move);

        assertFalse(result, "Method shouldn't accept a move"
        + "with a number outside the bounds of the map.");
    }
}
