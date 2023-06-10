package it.uniba.app.battleship;

import static it.uniba.app.App.play;

import it.uniba.app.Difficulty;
import it.uniba.app.MapType;
import it.uniba.app.Match;

import static it.uniba.app.App.setMatch;
import static it.uniba.app.utils.OutputHandler.captureOutput;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * PlayTest - Test class used to verify that the
 * "play()" function of class "App" behaves as expected.
 */
class PlayTest {

    private final int cr = 13;
    private static Match match = new Match(MapType.EXTRALARGE, Difficulty.Level.EASY, Integer.MAX_VALUE);

    /**
     * Before all test, the match is initialized
     * to simulate a started match.
     */
    @BeforeAll
    static void setUp() {
        match = new Match(MapType.EXTRALARGE, Difficulty.Level.EASY, Integer.MAX_VALUE);
    }

    /**
     * After all tests, the match is reset.
     */
    @AfterEach
    void reset() {
        setMatch(null);
    }

    /**
      * Tests the method {@link App#play()}
      * to start a new match.
      */
    @Test
    void testGameHasNotStarted() {
        String expectedOutput = "Partita avviata!\n" + match.getMap().getMapGrid();
        expectedOutput.replace((char) (cr) + "\n", "\n");
        String output = captureOutput(() -> play(match.getMap().getMapType(),
                                            match.getCurrentDifficulty(), Integer.MAX_VALUE))
                                            .replace((char) (cr) + "\n", "\n");

        assertTrue(expectedOutput.contains(output), "The match should start.");
    }

    /**
      * Tests the method {@link App#play()} to check if
      * the match has already started.
      */
    @Test
    @DisplayName("Check if the current match has already started.")
    void testGameHasAlreadyStarted() {
        setMatch(match);

        String expectedOutput = "E' gia' in corso un'altra partita!\nDigita /abbandona per terminare la partita!";
        String output = captureOutput(() -> play(match.getMap().getMapType(),
                                            match.getCurrentDifficulty(), Integer.MAX_VALUE))
                        .replace((char) (cr) + "\n", "\n");

        assertTrue(expectedOutput.toString().compareTo(output) == 0,
                    "A new match should not start. A match already started.");
    }
}
