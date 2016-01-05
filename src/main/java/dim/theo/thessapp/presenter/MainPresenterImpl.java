package dim.theo.thessapp.presenter;

import android.content.res.TypedArray;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import dim.theo.thessapp.model.MarkerItem;

/**
 * Created by css on 12/10/15.
 */
public class MainPresenterImpl implements MainPresenter {


    private static final String TAG = "mainPresenter.Impl";

    @Override
    public int removeMarkers(final ArrayList<Marker> markerArrayList, final LatLng pos) {
        int i;
        for (i = 0; i < markerArrayList.size(); i++) {
            if (markerArrayList.get(i).getPosition().equals(pos)) {
                markerArrayList.get(i).remove();    //remove marker from map
                break;
            }
        }
        return i;
    }

    @Override
    public void populateMarkerItemsArrayList(MarkerItem markerItem, ArrayList<LatLng> latLngArrayList, String[] names,
                                             TypedArray markerIcons, ArrayList<MarkerItem> markerItemArrayList) {
        for (int i = 0; i < latLngArrayList.size(); i++) {
            markerItem.setName(names[i]);
            markerItem.setCoordinates(latLngArrayList.get(i));
            markerItem.setIcon(markerIcons.getDrawable(i));
            markerItemArrayList.add(i, markerItem);
        }

    }

}

