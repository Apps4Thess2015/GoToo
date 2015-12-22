package dim.theo.thessapp.presenter;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;


/**
 * Created by css on 12/11/15.
 */
public class MainPresenterImplTest {

    private MainPresenterImpl mainPresenter;

    private ArrayList<LatLng> mockPositions = new ArrayList<LatLng>() {{
        add(new LatLng(40.597525973153253, 22.976585104818607));
        add(new LatLng(40.597525, 22.0097658));
        add(new LatLng(40.397323, 22.98612));
        add(new LatLng(40.497545, 22.87643));
        add(new LatLng(40.119743, 22.76632));
        add(new LatLng(40.327654, 22.54635));
        add(new LatLng(40.437657, 22.656455));
        add(new LatLng(40.547768, 22.546434));
        add(new LatLng(40.657988, 22.216434));
        add(new LatLng(40.767666, 22.416433));
        add(new LatLng(40.877777, 22.32676));
        add(new LatLng(40.937888, 22.12687));
    }};

    @Mock
    public ArrayList<Marker> mockMarkers = new ArrayList<>(mockPositions.size());
    private LatLng itemToRemove = new LatLng(40.937888, 22.12687);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mainPresenter = new MainPresenterImpl();
    }

    @Test
    public void testRemoveMarkers() throws Exception {
        int sizeBeforeRemoveMarker =  mockMarkers.size();
        mainPresenter.removeMarkers(mockMarkers, itemToRemove);
        int sizeAfterRemoveMarker =  mockMarkers.size();

        assertTrue("True", sizeBeforeRemoveMarker == sizeAfterRemoveMarker + 1);
    }
}