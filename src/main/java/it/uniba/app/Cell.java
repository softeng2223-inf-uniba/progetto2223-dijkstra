package it.uniba.app;
/**
 * << Entity >>:
 * Cell class for map grid.
 */
public final class Cell {
    private boolean hit;    // if the cell is hit by an user action
    private Ship ship;

    /**
     * Cell empty Constructor.
     */
    public Cell() {
        hit = false;
        ship = null;
    }
    /**
     * Cell Constructor.
     * @param inShip - specifies the ship to put in the cell.
     */
    public Cell(final Ship inShip) {
        hit = false;
        ship = inShip;
    }
    public void setShip(final Ship inShip) {
        ship = new Ship(inShip);
    }
    /**
     * Returns the ship.
     * @return
     */
    public Ship getShip() {
        if (ship != null) {
            return new Ship(ship);
        } else {
            return null;
        }
    }
    public boolean isFree() {
        return ship == null;
    }
    /**
     * Hit the cell.
     */
    public void hit() {
        hit = true;
        if (ship != null) {
            ship.hit();
        }
    }
    public boolean isHit() {
        return hit;
    }
}
