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

public class FavouriteLocationsAdapter extends ArrayAdapter<Location> {

    private LayoutInflater layoutInflater;
    private FavouriteLocationsAdapterDelegate favouriteLocationsAdapterDelegate;

    public static interface FavouriteLocationsAdapterDelegate {
        void selectFavouriteItem(Location location);

        void removeFavouriteItemFromBookmarks(Location location);
    }

    public FavouriteLocationsAdapter(Context context, List<Location> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    public void setDelegate(FavouriteLocationsAdapterDelegate delegate) {
        favouriteLocationsAdapterDelegate = delegate;
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
                if (favouriteLocationsAdapterDelegate != null)
                    favouriteLocationsAdapterDelegate.selectFavouriteItem(location);
            }
        });
    }

    private void setListenerOnBookmarkCheckbox(View convertView, final Location location) {
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.bookmark_location_checkBox);
        checkBox.setChecked(location.isBookmarked);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (favouriteLocationsAdapterDelegate != null) {
                    if (!isChecked)
                        favouriteLocationsAdapterDelegate.removeFavouriteItemFromBookmarks(location);
                }
            }
        });
    }
}
