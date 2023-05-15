package it.uniba.app;
/**
 * Enum class for ship types of Battleship.
 */
public enum ShipType {
    DESTROYER(2),           // cacciatorpediniere
    CRUISER(3),             // incrociatore
    ARMOURED(4),            // corazzato
    AIRCRAFT_CARRIER(5);    // portaerei

    private final int size;

    ShipType(final int inSize) {
        this.size = inSize;
    }

    public int getSize() {
        return size;
    }
}
