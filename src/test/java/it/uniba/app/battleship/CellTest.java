package it.uniba.app.battleship;

import org.junit.jupiter.api.Test;

import it.uniba.app.Cell;
import it.uniba.app.Ship;
import it.uniba.app.ShipType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/* CellTest - Test class used to check basic cell properties */
class CellTest {

    private static Cell cell;
    private static Ship ship;

    /**
     * Initialize the Cell and Ship objects before all tests.
     */
    @BeforeAll
    static void setUp() {
        setCell(new Cell());
        setShip(new Ship(ShipType.DESTROYER));
    }

    /**
     * After each test, deletes all the changes made to
     * the objects used (i.e. the Cell object).
     */
    @AfterEach
    void cleanUp() {
        setCell(new Cell());
    }

    static void setCell(final Cell inCell) {
        cell = inCell;
    }

    static void setShip(final Ship inShip) {
        ship = inShip;
    }

    /**
     * Tests the method {@link Cell#isFree()}
     * to check if a cell is empty or not.
     */
    @Test
    @DisplayName("Check whether the current cell is not taken by a ship.")
    void testEmptyCell() {
        assertTrue(cell.isFree(), "The cell should be without a ship in it.");
    }

    /**
     * Uses the method {@link Cell#getShip()}
     * to check if the cell has a ship on it.
     */
    @Test
    @DisplayName("Check whether the cell has not a ship attached to it.")
    void testFreeCell() {
        assertNull(cell.getShip(), "The cell should not have a ship assigned to it.");
    }

    /**
     * Checks if a ship is correctly set to a cell.
     * Tests the methods {@link Cell#setShip()} and {@link Cell#getShip()}
     */
    @Test
    @DisplayName("Test whether a ship is assigned correctly to a cell.")
    void testSetShip() {
        cell.setShip(ship);
        assertSame(ship, cell.getShip(), "The ship should be set correctly on the cell.");
    }

    /**
     * Checks if the cell has been hit as expected.
     * Tests the methods {@link Cell#hit()} and {@link Cell#isHit()}
     */
    @Test
    @DisplayName("Check whether the cell has been hit.")
    void testCellHit() {
        cell.hit();
        assertTrue(cell.isHit(), "The cell should be hit.");
    }
}
