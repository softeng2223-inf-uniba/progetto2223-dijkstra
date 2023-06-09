package it.uniba.app;
/**
 * << Entity >>:
 * Enum class for Map types.
 */
public enum MapType {
    STANDARD(10),
    LARGE(18),
    EXTRALARGE(26);

    private final int size;

    /**
     * MapType constructor.
     * @param inSize - specifies the map size.
     */
    MapType(final int inSize) {
        size = inSize;
    }

    public int getSize() {
        return size;
    }
}
