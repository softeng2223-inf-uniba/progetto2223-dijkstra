package it.uniba.app.battleship;

import java.util.Random;
import org.junit.jupiter.api.Test;

import it.uniba.app.Map;
import it.uniba.app.MapType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* MapTest - Test class used to check basic map properties */
class MapTest {

  private static MapType type;
  private static Map map;
  private static Random rand = new Random();;

  private static final int LOWERBOUND = 0;
  private static final int UPPERBOUND = 2;

  /**
   * Initialize the MapType and Map objects before all tests.
   * To do so, it generates a random number to define the type of the map.
   */
  @BeforeAll
  static void setUp() {
    type = MapType.values()[rand.nextInt(LOWERBOUND, UPPERBOUND + 1)];
    map = new Map(type);
  }

  /**
   * After each test, it defines a new map type
   * and assigns it to the Map object.
   */
  @AfterEach
  void reset() {
    setTestType(MapType.values()[rand.nextInt(LOWERBOUND, UPPERBOUND + 1)]);
    map.setType(type);
  }

  static void setTestType(final MapType inType) {
    type = inType;
  }

  /**
   * Tests the methods {@link Map#getMapType()}
   * and {@link MapType#getSize()} to check if the map
   * size matches the size of the defined map type size.
   */
  @Test
  @DisplayName("Check that the size of the map is set correctly.")
  void testMapSize() {
    assertEquals(type.getSize(), map.getMapType().getSize(), "The size of the map must"
    + "match the size of the map of the defined type");
  }
}
