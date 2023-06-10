package it.uniba.app.battleship;

import org.junit.jupiter.api.Test;

import it.uniba.app.Ship;
import it.uniba.app.ShipType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/* ShipTest - Test class used to check basic ship properties */
class ShipTest {

  private static ShipType type;
  private static Ship ship;

  /**
   * Initialize the ShipType and Ship objects before all tests.
   */
  @BeforeAll
  static void setUp() {
    type = ShipType.CRUISER;
    ship = new Ship(type);
  }

  /**
   * After each test, it defines a new ship type
   * and assigns it to the Ship object.
   */
  @AfterEach
  void reset() {
    ship = new Ship(type);
  }

  /**
   * Tests the method {@link Ship#isHit()}
   * to check if a ship was hit or not.
   */
  @Test
  @DisplayName("Check whether the ship is hit or not.")
  void testIsHit() {
    assertFalse(ship.isHit(), "A new ship should not be hit.");
  }

  /**
   * Tests the method {@link Ship#isSunken()}
   * to check whether a ship sank or not.
   */
  @Test
  @DisplayName("Check whether the ship sank or not.")
  void testIsSunken() {
    assertFalse(ship.isSunken(), "A new ship should not be sunken.");
  }

  /**
   * Tests the method {@link Ship#getType()}
   * to check that the type of the ship is
   * equal to the type used in initialization.
   */
  @Test
  @DisplayName("Test whether the type of the ship is assigned correctly.")
  void testGetType() {
    assertEquals(type, ship.getType(), "Ship type should match the provided type.");
  }

  /**
   * Tests the method {@link Ship#hit()} and {@link Ship#isHit()}
   * to check if a ship was hit or not, after it is hit.
   */
  @Test
  @DisplayName("Check whether the ship is hit as expected.")
  void testIsHitAfterOneHit() {
    ship.hit();
    assertTrue(ship.isHit(), "The ship should be hit after receiving one hit.");
  }

  /**
   * Tests the method {@link Ship#hit()} and {@link Ship#isSunken()}
   * to check if a ship is sunk, after it has been hit entirely.
   */
  @Test
  @DisplayName("Check whether the ship sank after it has been hit.")
  void testIsSunkenAfterSink() {
    for (int i = 0; i < type.getSize(); i++) {
      ship.hit();
    }

    assertTrue(ship.isSunken(), "The ship should be sunken after receiving enough hits.");
  }
}
