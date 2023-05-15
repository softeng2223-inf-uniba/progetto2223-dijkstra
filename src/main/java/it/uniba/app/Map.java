package it.uniba.app;
import java.util.Random;

/**
 * Class for the map of Battleship.
 */
public final class Map {
    public static final int CELLS_NUMBER = 10;
    public static final int DESTROYERS_NUMBER = 4;
    public static final int CRUISERS_NUMBER = 3;
    public static final int ARMOUREDS_NUMBER = 2;
    public static final int AIRCRAFT_CARRIERS_NUMBER = 1;
    private Cell[][] map;

    /**
     * Map Constructor that initializes the grid with random ship positioning.
     */
    public Map() {
        Random rand = new Random();
        int row;
        int col;

        map = new Cell[CELLS_NUMBER][CELLS_NUMBER];

        for (int i = 0; i < CELLS_NUMBER; i++) {
            for (int j = 0; j < CELLS_NUMBER; j++) {
                if (map[i][j] == null) {
                    map[i][j] = new Cell();
                }
            }
        }

        for (int i = 0; i < DESTROYERS_NUMBER; i++) {
            do {
                row = rand.nextInt(CELLS_NUMBER);
                col = rand.nextInt(CELLS_NUMBER);
            } while (!setDirection(row, col, new Ship(ShipType.DESTROYER)));
        }

        for (int i = 0; i < CRUISERS_NUMBER; i++) {
            do {
                row = rand.nextInt(CELLS_NUMBER);
                col = rand.nextInt(CELLS_NUMBER);
            } while (!setDirection(row, col, new Ship(ShipType.CRUISER)));
        }

        for (int i = 0; i < ARMOUREDS_NUMBER; i++) {
            do {
                row = rand.nextInt(CELLS_NUMBER);
                col = rand.nextInt(CELLS_NUMBER);
            } while (!setDirection(row, col, new Ship(ShipType.ARMOURED)));
        }

        for (int i = 0; i < AIRCRAFT_CARRIERS_NUMBER; i++) {
            do {
                row = rand.nextInt(CELLS_NUMBER);
                col = rand.nextInt(CELLS_NUMBER);
            } while
            (!setDirection(row, col, new Ship(ShipType.AIRCRAFT_CARRIER)));
        }
    }

    private boolean checkRight(final int row, final int col, final int size) {
        int i = 0;

        if (col > CELLS_NUMBER - size) {
            return false;
        }
        while (i < size && map[row][col + i].isFree()) {
            // System.out.println("row: "+(row)+"\tcol: "+(col+i));
            i++;
        }

        return i == size;
    }

    private boolean checkBelow(final int row, final int col, final int size) {
        int i = 0;

        if (row > CELLS_NUMBER - size) {
            return false;
        }
        while (i < size && map[row + i][col].isFree()) {
            // System.out.println("row: "+(row+i)+"\ncol: "+(col));

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
            // System.out.println("row: "+(row)+"\ncol: "+(col-i));
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
            // System.out.println("row: "+(row-i)+"\ncol: "+(col));
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
     * Returns the Map grid updated on every attempt.
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
     * Returns a string that describes all alive and dead ships.
     * @return String
     */
    public String shipStats() {
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
