package pl.edu.icm.meteo.android.meteo.app.location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationsRepository {
    private List<Location> locations;
    private List<Location> favouriteLocations;
    private Location activeLocation;


    public LocationsRepository() {
        initLocations();
        initFavouriteLocations();
    }

    private void initLocations() {
        locations = new ArrayList<Location>();
        locations.add(new Location("Kraków"));
        locations.add(new Location("Warszawa"));
        locations.add(new Location("Malbork"));
        locations.add(new Location("Bielsko-Biała"));
        locations.add(new Location("Świebodzin"));
        locations.add(new Location("Szczecin"));
        locations.add(new Location("Suwałki"));
        locations.add(new Location("Zielona Góra"));
        locations.add(new Location("Katowice"));
        locations.add(new Location("Sfornegacie"));
        locations.add(new Location("Psia Wólka"));
        locations.add(new Location("Koziegłowy"));
    }

    private void initFavouriteLocations() {
        favouriteLocations = new ArrayList<Location>();
    }

    public List<Location> locationsSortedByName() {
        Collections.sort(locations);
        return locations;
    }

    public List<Location> favouriteLocationsSortedByName() {
        Collections.sort(favouriteLocations);
        return favouriteLocations;
    }

    public void addFavouriteLocation(Location location) {
        favouriteLocations.add(location);
        Collections.sort(favouriteLocations);
    }

    public void removeFavouriteLocation(Location location) {
        favouriteLocations.remove(location);
    }

    public Location getActiveLocation() {
        return activeLocation;
    }

    public void setActiveLocation(Location activeLocation) {
        this.activeLocation = activeLocation;
    }
}
