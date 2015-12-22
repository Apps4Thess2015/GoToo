package dim.theo.thessapp.presenter;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

/**
 * Created by css on 12/10/15.
 */
public class MainPresenterImpl implements MainPresenter {


    @Override
    public void removeMarkers(final ArrayList<Marker> markerArrayList, final LatLng pos) {
        for (int i = 0; i < markerArrayList.size(); i++) {
            if (markerArrayList.get(i).getPosition().equals(pos)) {
                markerArrayList.get(i).remove();
            }
        }
    }

}

