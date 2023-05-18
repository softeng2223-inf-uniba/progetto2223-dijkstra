package it.uniba.app;
/**
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
    }
    /**
     * Cell Constructor.
     * @param inShip - specifies the ship to put in the cell.
     */
    public Cell(final Ship inShip) {
        hit = false;
        this.ship = inShip;
    }
    public void setShip(final Ship inShip) {
        this.ship = inShip;
    }
    public Ship getShip() {
        return ship;
    }
    public boolean isFree() {
        return ship == null;
    }
    /**
     * Hit the cell.
     */
    public void hit() {
        hit = true;
    }
    public boolean isHit() {
        return hit;
    }
}
