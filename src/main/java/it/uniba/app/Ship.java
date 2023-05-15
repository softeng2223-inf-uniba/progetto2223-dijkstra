package it.uniba.app;
/**
 * Class for ship in Battleship.
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
    public boolean isHit() {
        return healthPoints < type.getSize();
    }
    public boolean isSunken() {
        return healthPoints == 0;
    }
    public ShipType getType() {
        return type;
    }
}
