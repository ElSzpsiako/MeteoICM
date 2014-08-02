package pl.edu.icm.meteo.android.meteo.app.location;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import pl.edu.icm.meteo.android.meteo.app.R;

public class LocationsActivity extends ActionBarActivity implements LocationsAdapter.LocationsAdapterDelegate {

    private ListView locationsListView;
    private LocationsAdapter locationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locations_activity);
        locationsListView = (ListView) findViewById(R.id.locations_list_view);
        locationsAdapter = new LocationsAdapter(this, LocationsSingleton.locationsSortedByName());
        locationsListView.setAdapter(locationsAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationsAdapter.setDelegate(null);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        locationsAdapter.setDelegate(this);
    }

    @Override
    public void bookmarkItem(Location location) {
        System.out.println("Bookmark item =========================== " + location.name);
    }
}
