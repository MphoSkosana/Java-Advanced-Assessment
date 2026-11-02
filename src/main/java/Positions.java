/**
 * Possible employee positions, ordered from lowest to highest.
 */
public enum Positions {
    JUNIOR("Junior"),
    INTERMEDIATE("Intermediate"),
    SENIOR("Senior"),
    DIRECTOR("Director"),
    EXECUTIVE("Executive");

    private final String displayName;

    Positions(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}