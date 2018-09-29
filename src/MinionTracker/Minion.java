package MinionTracker;

/**
 * Class to hold Minion information
 */
public class Minion {

    // Minion attributes
    private String name;
    private double height;
    private int evilDeeds;

    /**
     * Constructs a Minion who's done zero evil deeds
     *
     * @param name name of this Minion
     * @param height height of this Minion
     */
    Minion(String name, double height) {
        this.name = name;
        this.height = height;
        this.evilDeeds = 0;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public int getEvilDeeds() {
        return evilDeeds;
    }

    public void incEvilDeeds() {
        evilDeeds += 1;
    }

    public String toString() {
        return String.format("\"%s\" with height \"%s\" has done \"%s\" evil deeds", name, height, evilDeeds);
    }


}
