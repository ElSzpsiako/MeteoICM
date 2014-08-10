package pl.edu.icm.meteo.android.meteo.app.location;

import java.text.Collator;
import java.util.Locale;

public class Location implements Comparable<Location> {
    String name;
    Boolean isBookmarked;

    public Location(String name) {
        this.name = name;
        this.isBookmarked = false;
    }

    @Override
    public int compareTo(Location another) {
        Collator plCollator = Collator.getInstance(new Locale("pl", "PL"));
        return plCollator.compare(name, another.name);
    }

    public void setIsBookmarked(Boolean isBookmarked) {
        this.isBookmarked = isBookmarked;
    }
}
