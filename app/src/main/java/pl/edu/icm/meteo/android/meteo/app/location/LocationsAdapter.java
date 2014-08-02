package pl.edu.icm.meteo.android.meteo.app.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import pl.edu.icm.meteo.android.meteo.app.R;

import java.util.List;

public class LocationsAdapter extends ArrayAdapter<Location> {

    private LayoutInflater layoutInflater;
    private LocationsAdapterDelegate locationsAdapterDelegate;

    public static interface LocationsAdapterDelegate {
        void bookmarkItem(Location location);
    }

    public LocationsAdapter(Context context, List<Location> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.location_line_layout, parent, false);
        final Location location = getItem(position);
        TextView textView = (TextView) convertView.findViewById(R.id.location_line_view);
        textView.setText(location.name);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationsAdapterDelegate != null)
                    locationsAdapterDelegate.bookmarkItem(location);
            }
        });
        return convertView;
    }

    public void setDelegate(LocationsAdapterDelegate delegate) {
        locationsAdapterDelegate = delegate;
    }
}
