package pl.edu.icm.meteo.android.meteo.app.location;

public class Location implements Comparable<Location> {
    Integer id;
    String name;

    public Location(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    //TODO - Polish letters
    @Override
    public int compareTo(Location another) {
        return name.compareToIgnoreCase(another.name);
    }
}
