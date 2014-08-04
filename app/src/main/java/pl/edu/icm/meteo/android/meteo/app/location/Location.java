package pl.edu.icm.meteo.android.meteo.app.location;

import java.text.Collator;
import java.util.Locale;

public class Location implements Comparable<Location> {
    Integer id;
    String name;

    public Location(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Location another) {
        Collator plCollator = Collator.getInstance(new Locale("pl", "PL"));
        return plCollator.compare(name, another.name);
    }
}
