package pl.edu.icm.meteo.android.meteo.app.location;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import pl.edu.icm.meteo.android.meteo.app.MainActivity;
import pl.edu.icm.meteo.android.meteo.app.MeteoApplication;
import pl.edu.icm.meteo.android.meteo.app.R;

import static pl.edu.icm.meteo.android.meteo.app.location.FavouriteLocationsAdapter.FavouriteLocationsAdapterDelegate;
import static pl.edu.icm.meteo.android.meteo.app.location.LocationsAdapter.LocationsAdapterDelegate;

public class LocationsActivity extends ActionBarActivity implements LocationsAdapterDelegate, FavouriteLocationsAdapterDelegate {

    private LocationsRepository locationsRepository;
    private ListView locationsListView;
    private LocationsAdapter locationsAdapter;

    private ListView favouriteLocationsListView;
    private FavouriteLocationsAdapter favouriteLocationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locations_activity);
        MeteoApplication meteoApplication = ((MeteoApplication) getApplicationContext());
        locationsRepository = meteoApplication.getLocationsRepository();

        locationsListView = (ListView) findViewById(R.id.locations_list_view);
        locationsAdapter = new LocationsAdapter(this, locationsRepository.locationsSortedByName());
        locationsListView.setAdapter(locationsAdapter);

        favouriteLocationsListView = (ListView) findViewById(R.id.favourite_locations_list_view);
        favouriteLocationsAdapter = new FavouriteLocationsAdapter(this, locationsRepository.favouriteLocationsSortedByName());
        favouriteLocationsListView.setAdapter(favouriteLocationsAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationsAdapter.setDelegate(null);
        favouriteLocationsAdapter.setDelegate(null);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        locationsAdapter.setDelegate(this);
        favouriteLocationsAdapter.setDelegate(this);
    }

    @Override
    public void selectLocation(Location location) {
        selectAcitveLocation(location);
        System.out.println("ALL LOCATIONS - active location =========== " + locationsRepository.getActiveLocation());
    }

    @Override
    public void bookmarkLocation(Location location) {
        location.setIsBookmarked(true);
        locationsRepository.addFavouriteLocation(location);
        favouriteLocationsAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeLocationsFromBookmarks(Location location) {
        removeLocation(location);
        favouriteLocationsAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectFavouriteLocation(Location location) {
        selectAcitveLocation(location);
        System.out.println("FAVOURITE LOCATIONS - active location =========== " + locationsRepository.getActiveLocation());
    }

    private void selectAcitveLocation(Location location) {
        locationsRepository.setActiveLocation(location);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void removeFavouriteLocation(Location location) {
        removeLocation(location);
        locationsAdapter.notifyDataSetChanged();
    }

    private void removeLocation(Location location) {
        location.setIsBookmarked(false);
        locationsRepository.removeFavouriteLocation(location);
    }

}
