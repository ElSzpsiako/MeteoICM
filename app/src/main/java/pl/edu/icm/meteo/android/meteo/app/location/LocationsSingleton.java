package pl.edu.icm.meteo.android.meteo.app.location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationsSingleton {
    private static LocationsSingleton instance;
    private static List<Location> locations;

    private LocationsSingleton() {
        initLocations();
    }

    private void initLocations() {
        locations = new ArrayList<Location>();
        locations.add(new Location(3, "Kraków"));
        locations.add(new Location(2, "Warszawa"));
        locations.add(new Location(1, "Malbork"));
    }

    public static List<Location> getLocations() {
        if (instance == null)
            instance = new LocationsSingleton();
        return locations;
    }

    public static List<Location> locationsSortedByName() {
        return sortLocationsByName(getLocations());
    }


    private static List<Location> sortLocationsByName(List<Location> locations) {
        Collections.sort(locations);
        return locations;
    }
}
