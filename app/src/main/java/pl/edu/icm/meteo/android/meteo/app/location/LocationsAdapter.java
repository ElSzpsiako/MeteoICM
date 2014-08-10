package pl.edu.icm.meteo.android.meteo.app.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import pl.edu.icm.meteo.android.meteo.app.R;

import java.util.List;

public class LocationsAdapter extends ArrayAdapter<Location> {

    private LayoutInflater layoutInflater;
    private LocationsAdapterDelegate locationsAdapterDelegate;

    public static interface LocationsAdapterDelegate {
        void selectLocation(Location location);

        void bookmarkLocation(Location location);

        void removeLocationsFromBookmarks(Location location);
    }

    public LocationsAdapter(Context context, List<Location> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    public void setDelegate(LocationsAdapterDelegate delegate) {
        locationsAdapterDelegate = delegate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.location_line_layout, parent, false);
        final Location location = getItem(position);
        setListenerOnTextView(convertView, location);
        setListenerOnBookmarkCheckbox(convertView, location);
        return convertView;
    }

    private void setListenerOnTextView(View convertView, final Location location) {
        TextView textView = (TextView) convertView.findViewById(R.id.location_line_view);
        textView.setText(location.name);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationsAdapterDelegate != null)
                    locationsAdapterDelegate.selectLocation(location);
            }
        });
    }

    private void setListenerOnBookmarkCheckbox(View convertView, final Location location) {
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.bookmark_location_checkBox);
        checkBox.setChecked(location.isBookmarked);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (locationsAdapterDelegate != null) {
                    if (isChecked)
                        locationsAdapterDelegate.bookmarkLocation(location);
                    else
                        locationsAdapterDelegate.removeLocationsFromBookmarks(location);
                }
            }
        });
    }
}
