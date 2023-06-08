package it.uniba.app;
import java.util.Random;

/**
 * Class for the map of Battleship.
 * This class describe the behaviour of the map during the game.
 */
public final class Map {
    public static final int TOTAL_SHIPS_NUMBER = 10;
    public static final int DESTROYERS_NUMBER = 4;
    public static final int CRUISERS_NUMBER = 3;
    public static final int ARMOUREDS_NUMBER = 2;
    public static final int AIRCRAFT_CARRIERS_NUMBER = 1;
    private MapType type;
    private Cell[][] map;

    /**
     * Copy Constructor.
     * @param inMap
     */
    public Map(final Map inMap) {
        int size = inMap.getMapType().getSize();
        map = new Cell[size][size];
        type = inMap.type;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = inMap.getCell(i, j);
            }
        }
    }

    /**
     * Map Constructor that initializes the grid with random ship positioning.
     */
    public Map(final MapType inType) {
        Random rand = new Random();
        int row;
        int col;

        type = inType;
        int size = type.getSize();

        map = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == null) {
                    map[i][j] = new Cell();
                }
            }
        }

        for (int i = 0; i < DESTROYERS_NUMBER; i++) {
            do {
                row = rand.nextInt(size);
                col = rand.nextInt(size);
            } while (!setDirection(row, col, new Ship(ShipType.DESTROYER)));
        }

        for (int i = 0; i < CRUISERS_NUMBER; i++) {
            do {
                row = rand.nextInt(size);
                col = rand.nextInt(size);
            } while (!setDirection(row, col, new Ship(ShipType.CRUISER)));
        }

        for (int i = 0; i < ARMOUREDS_NUMBER; i++) {
            do {
                row = rand.nextInt(size);
                col = rand.nextInt(size);
            } while (!setDirection(row, col, new Ship(ShipType.ARMOURED)));
        }

        for (int i = 0; i < AIRCRAFT_CARRIERS_NUMBER; i++) {
            do {
                row = rand.nextInt(size);
                col = rand.nextInt(size);
            } while
            (!setDirection(row, col, new Ship(ShipType.AIRCRAFT_CARRIER)));
        }
    }

    private boolean checkRight(final int row, final int col, final int shipSize) {
        int i = 0;
        int size = type.getSize();

        if (col > size - shipSize) {
            return false;
        }
        while (i < shipSize && map[row][col + i].isFree()) {
            i++;
        }

        return i == size;
    }

    private boolean checkBelow(final int row, final int col, final int shipSize) {
        int i = 0;
        int size = type.getSize();

        if (row > size - shipSize) {
            return false;
        }
        while (i < shipSize && map[row + i][col].isFree()) {
            i++;
        }

        return i == size;
    }

    private boolean checkLeft(final int row, final int col, final int size) {
        int i = 0;

        if (col - size < 0) {
            return false;
        }
        while (i < size && map[row][col - i].isFree()) {
            i++;
        }

        return i == size;
    }

    private boolean checkUp(final int row, final int col, final int size) {
        int i = 0;

        if (row - size < 0) {
            return false;
        }
        while (i < size && map[row - i][col].isFree()) {
            i++;
        }

        return i == size;
    }

    private boolean setDirection(
        final int row, final int col, final Ship ship) {
        int size = ship.getType().getSize();
        int i;
        boolean set = false;
        if (checkRight(row, col, size)) {
            for (i = 0; i < size; i++) {
                map[row][col + i] = new Cell(ship);
            }
            set = true;
        } else if (checkBelow(row, col, size)) {
            for (i = 0; i < size; i++) {
                map[row + i][col] = new Cell(ship);
            }
            set = true;
        } else if (checkLeft(row, col, size)) {
            for (i = 0; i < size; i++) {
                map[row][col - i] = new Cell(ship);
            }
            set = true;
        } else if (checkUp(row, col, size)) {
            for (i = 0; i < size; i++) {
                map[row - i][col] = new Cell(ship);
            }
            set = true;
        }
        return set;
    }

    public MapType getMapType() {
        return type;
    }

    /**
     * Returns the cell in position row and col in map.
     * @param row - the row position.
     * @param col - the column position.
     * @return
     */
    public Cell getCell(final int row, final int col) {
        return map[row][col];
    }

    /**
     * Returns the revelead grid with all ship positions.
     * @return String - represents the grid in a formatted String.
     */
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        int i = 0;
        int size = type.getSize();
        s.append("\n\t");
        for (int j = 0; j < size; j++) {
            s.append((char) (((int) 'A') + j));
            s.append(" ");
        }
        s.append("\n\n\n");
        for (Cell[] cells : map) {
            s.append((i + 1) + "\t");
            for (Cell cell : cells) {
                if (cell.isFree()) {
                    s.append(ANSICodes.FBLUE + "~ " + ANSICodes.RESET);
                } else if
                (cell.getShip().getType() == ShipType.AIRCRAFT_CARRIER) {
                    s.append(ANSICodes.FRED + "# " + ANSICodes.RESET);
                } else if (cell.getShip().getType() == ShipType.ARMOURED) {
                    s.append(ANSICodes.FYELLOW + "# " + ANSICodes.RESET);
                } else if (cell.getShip().getType() == ShipType.CRUISER) {
                    s.append(ANSICodes.FGREEN + "# " + ANSICodes.RESET);
                } else if (cell.getShip().getType() == ShipType.DESTROYER) {
                    s.append("# ");
                }
            }
            i++;
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Returns the String representation of the Map grid updated on every attempt.
     * @return String
     */
    public String getMapGrid() {
        StringBuffer s = new StringBuffer();
        int i = 0;
        int size = type.getSize();
        s.append("\n\t");
        for (int j = 0; j < size; j++) {
            s.append((char) (((int) 'A') + j));
            s.append(" ");
        }
        s.append("\n\n\n");
        for (Cell[] cells : map) {
            s.append((i + 1) + "\t");
            for (Cell cell : cells) {
                if (cell.isHit()) {
                    if (cell.isFree()) {
                        s.append(ANSICodes.FBLUE + "o " + ANSICodes.RESET);
                    } else if
                    (cell.getShip().getType() == ShipType.AIRCRAFT_CARRIER) {
                        s.append(ANSICodes.FRED + "X " + ANSICodes.RESET);
                    } else if (cell.getShip().getType() == ShipType.ARMOURED) {
                        s.append(ANSICodes.FYELLOW + "X " + ANSICodes.RESET);
                    } else if (cell.getShip().getType() == ShipType.CRUISER) {
                        s.append(ANSICodes.FGREEN + "X " + ANSICodes.RESET);
                    } else if (cell.getShip().getType() == ShipType.DESTROYER) {
                        s.append("X ");
                    }
                } else {
                    s.append("? ");
                }
            }
            i++;
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Returns a string that describes all alive and sunken ships.
     * @return String
     */
    public String getShipStats() {
        StringBuffer s = new StringBuffer();
        int carriers = 0;
        int armoureds = 0;
        int cruisers = 0;
        int destroyers = 0;
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                if (!cell.isFree()) {
                    if (cell.getShip().getType() == ShipType.AIRCRAFT_CARRIER) {
                        carriers++;
                    } else if (cell.getShip().getType() == ShipType.ARMOURED) {
                        armoureds++;
                    } else if (cell.getShip().getType() == ShipType.CRUISER) {
                        cruisers++;
                    } else if (cell.getShip().getType() == ShipType.DESTROYER) {
                        destroyers++;
                    }
                }
            }
        }
        s.append("Cacciatorpediniere\t\t##\t\tesemplari: ");
        s.append((destroyers / ShipType.DESTROYER.getSize()) + "\n");
        s.append("Incrociatore\t\t\t" + ANSICodes.FGREEN + "###" + ANSICodes.RESET + "\t\tesemplari: ");
        s.append((cruisers / ShipType.CRUISER.getSize()) + "\n");
        s.append("Corazzata\t\t\t" + ANSICodes.FYELLOW + "####" + ANSICodes.RESET + "\t\tesemplari: ");
        s.append((armoureds / ShipType.ARMOURED.getSize()) + "\n");
        s.append("Portaerei\t\t\t" + ANSICodes.FRED + "#####" + ANSICodes.RESET + "\t\tesemplari: ");
        s.append((carriers / ShipType.AIRCRAFT_CARRIER.getSize()) + "\n");
        return s.toString();
    }
}
