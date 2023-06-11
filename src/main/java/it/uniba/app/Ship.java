package it.uniba.app;
/**
 * << Entity >>:
 * Class for ship in Battleship.
 * This class describes the behaviour of a ship during the game.
 */
public final class Ship {
    private ShipType type;
    private int healthPoints;

    /**
     * Ship constructor.
     * @param inType - specifies the ship type to set the health points.
     */
    public Ship(final ShipType inType) {
        this.type = inType;
        this.healthPoints = type.getSize();
    }

    /**
     * Ship copy constructor.
     * @param inShip - the ship that needs to be copied to the current object
     */
    public Ship(final Ship inShip) {
        setType(inShip.getType());
        this.healthPoints = type.getSize();
    }

    public boolean isHit() {
        return healthPoints < type.getSize();
    }

    public boolean isSunken() {
        return healthPoints == 0;
    }

    public ShipType getType() {
        return type;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * Method used to hit the cell.
     */
    public void hit() {
        if (!isSunken()) {
            healthPoints--;
        }
    }

    public void setType(final ShipType inType) {
        this.type = inType;
    }

    /**
     * Checks that the Ship objects have the same attributes.
     * @param inShip
     * @return
     */
    public boolean isEquals(final Ship inShip) {
        return type == inShip.getType() && healthPoints == inShip.getHealthPoints();
    }
}
