package it.uniba.app;
import java.util.Random;

/**
 * Class for the map of Battleship.
 * This class describe the behaviour of the map during the game.
 */
public final class Map {
    private MapType type;
    private Cell[][] map;

    /**
     * Map Constructor that initializes the grid with random ship positioning.
     */
    public Map(final MapType inType) {
        Random rand = new Random();
        int row;
        int col;

        final int destroyersNumber = ShipType.DESTROYER.getSize();
        final int cruisersNumber = ShipType.DESTROYER.getSize();
        final int armouredsNumber = ShipType.ARMOURED.getSize();
        final int aircraftCarriersNumber = ShipType.AIRCRAFT_CARRIER.getSize();

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

        for (int i = 0; i < destroyersNumber; i++) {
            do {
                row = rand.nextInt(size);
                col = rand.nextInt(size);
            } while (!setDirection(row, col, new Ship(ShipType.DESTROYER)));
        }

        for (int i = 0; i < cruisersNumber; i++) {
            do {
                row = rand.nextInt(size);
                col = rand.nextInt(size);
            } while (!setDirection(row, col, new Ship(ShipType.CRUISER)));
        }

        for (int i = 0; i < armouredsNumber; i++) {
            do {
                row = rand.nextInt(size);
                col = rand.nextInt(size);
            } while (!setDirection(row, col, new Ship(ShipType.ARMOURED)));
        }

        for (int i = 0; i < aircraftCarriersNumber; i++) {
            do {
                row = rand.nextInt(size);
                col = rand.nextInt(size);
            } while
            (!setDirection(row, col, new Ship(ShipType.AIRCRAFT_CARRIER)));
        }
    }

    private boolean checkRight(final int row, final int col, final int size) {
        int i = 0;

        if (col > size - size) {
            return false;
        }
        while (i < size && map[row][col + i].isFree()) {
            i++;
        }

        return i == size;
    }

    private boolean checkBelow(final int row, final int col, final int size) {
        int i = 0;

        if (row > size - size) {
            return false;
        }
        while (i < size && map[row + i][col].isFree()) {
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

    /**
     * Returns the revelead grid with all ship positions.
     * @return String - represents the grid in a formatted String.
     */
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        int i = 0;
        s.append("\n\tA B C D E F G H I J\n\n\n");
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
        s.append("\n\tA B C D E F G H I J\n\n\n");
        for (Cell[] cells : map) {
            s.append((i + 1) + "\t");
            for (Cell cell : cells) {
                if (cell.isHit()) {
                    if (cell.isFree()) {
                        s.append(ANSICodes.BBLUE + "o " + ANSICodes.RESET);
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
