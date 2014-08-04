package pl.edu.icm.meteo.android.meteo.app.location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationsRepository {
    private static List<Location> locations;

    public LocationsRepository() {
        initLocations();
    }

    private void initLocations() {
        locations = new ArrayList<Location>();
        locations.add(new Location(3, "Kraków"));
        locations.add(new Location(2, "Warszawa"));
        locations.add(new Location(1, "Malbork"));
        locations.add(new Location(4, "Bielsko-Biała"));
        locations.add(new Location(5, "Świebodzin"));
        locations.add(new Location(6, "Szczecin"));
        locations.add(new Location(7, "Suwałki"));
    }

    public static List<Location> locationsSortedByName() {
        Collections.sort(locations);
        return locations;
    }
}
