package it.uniba.app.battleship;

import it.uniba.app.Shell;
import it.uniba.app.MapType;
import it.uniba.app.Match;
import it.uniba.app.Difficulty;

import static it.uniba.app.App.quit;
import static it.uniba.app.App.setMatch;
import static it.uniba.app.App.getMatch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 * QuitTest - Test class used to verify that the
 * "quit()"" function of class "App" behaves as expected.
 */
class QuitTest {

    private static Match match;

    /**
     * Before all test, the match is initialized
     * to simulate a started match.
     */
    @BeforeAll
    static void setUp() {
        match = new Match(MapType.EXTRALARGE, Difficulty.Level.EASY, Integer.MAX_VALUE);
        setMatch(match);
    }

    /**
     * After each test, the state of
     * the match is set as "active".
     */
    @AfterEach
    void reset() {
        setMatch(match);
    }

    /**
     * Tests the method {@link App#getMatch()}
     * to check if, after inserting "s", the
     * match terminates as expected.
     */
    @Test
    @DisplayName("Check if the match is terminated after successful input.")
    void testQuitYes() {
        Shell.setInput("s");
        quit();

        assertNull(getMatch(), "The match must terminate.");
    }

    /**
     * Tests the method {@link App#getMatch()}
     * to check if, after inserting "n", the
     * match continues to work as expected.
     */
    @Test
    @DisplayName("Check if the match stays active after successful input.")
    void testQuitNo() {
        Shell.setInput("n");
        quit();

        assertNotNull(getMatch(), "The match must not terminate.");
    }

}
