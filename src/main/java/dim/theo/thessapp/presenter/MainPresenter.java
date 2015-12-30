package dim.theo.thessapp.presenter;

import android.content.res.TypedArray;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import dim.theo.thessapp.model.MarkerItem;

/**
 * Created by css on 12/10/15.
 */
public interface MainPresenter {

    int removeMarkers(ArrayList<Marker> mockMarkers, LatLng pos);

    void populateMarkerItemsArrayList(MarkerItem markerItem, ArrayList<LatLng> latLngArrayList, String[] names,
                                      TypedArray markerIcons, ArrayList<MarkerItem> markerItemArrayList);
}
