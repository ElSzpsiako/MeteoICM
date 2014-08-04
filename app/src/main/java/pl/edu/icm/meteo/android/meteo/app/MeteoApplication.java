package pl.edu.icm.meteo.android.meteo.app;

import android.app.Application;
import pl.edu.icm.meteo.android.meteo.app.location.LocationsRepository;

public class MeteoApplication extends Application {
    LocationsRepository locationsRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        //TODO - init Locations but with single active or current (gps) Location or empty
        // then in background load all locations
        locationsRepository = new LocationsRepository();
    }

    public LocationsRepository getLocationsRepository() {
        return locationsRepository;
    }
}
