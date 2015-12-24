package dim.theo.thessapp.presenter;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

/**
 * Created by css on 12/10/15.
 */
public interface MainPresenter {

    public int removeMarkers(ArrayList<Marker> mockMarkers, LatLng pos);
}
