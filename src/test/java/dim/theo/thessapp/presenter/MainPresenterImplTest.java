package dim.theo.thessapp.presenter;

import android.content.res.TypedArray;
import android.util.TypedValue;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import dim.theo.thessapp.model.MarkerItem;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by css on 12/11/15.
 */
public class MainPresenterImplTest {

    private MainPresenterImpl mainPresenter;

    private ArrayList<LatLng> mockPositions = new ArrayList<LatLng>() {{
        add(new LatLng(40.397323, 22.98612));
        add(new LatLng(40.497545, 22.87643));
    }};

    private MarkerItem markerItem;
    private String[] names = new String[]{"mars 1", "mars 2"};
    private TypedArray markerIcons = mockTypedArray(mockPositions.size(), true);

    @Mock
    private ArrayList<MarkerItem> markerItemArrayList = new ArrayList<>(mockPositions.size());

    @Mock
    public ArrayList<Marker> mockMarkers = new ArrayList<>();
    private LatLng itemToRemove = new LatLng(40.937888, 22.12687);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mainPresenter = new MainPresenterImpl();

        markerItem = new MarkerItem();
    }

    @Test
    public void testRemoveMarkers() throws Exception {
        int sizeBeforeRemoveMarker =  mockMarkers.size();
        mainPresenter.removeMarkers(mockMarkers, itemToRemove);
        int sizeAfterRemoveMarker =  mockMarkers.size();
        System.out.println("testRemoveMarkers:   sizeBefore = " + sizeBeforeRemoveMarker
                + " sizeAfter = " + sizeAfterRemoveMarker);
        assertTrue("True", sizeBeforeRemoveMarker == sizeAfterRemoveMarker + 1);
    }

    @Test
    public void testPopulateMarkerItemsArrayList() throws Exception {
        mainPresenter.populateMarkerItemsArrayList(markerItem, mockPositions, names, markerIcons, markerItemArrayList);
        System.out.println("testPopulateMarkerItemsArrayList: " + " markerItemArrayList.SIZE ==  " + markerItemArrayList.size()
                + "  mockPositions.SIZE == " + mockPositions.size());
        assertTrue("True", markerItemArrayList.size() == mockPositions.size());
    }

    private TypedArray mockTypedArray(int length, boolean valueToReturn) {
        TypedArray typedArray = mock(TypedArray.class);
        when(typedArray.length()).thenReturn(length);
        when(typedArray.hasValue(0)).thenReturn(valueToReturn);
        when(typedArray.getValue(eq(0), any(TypedValue.class))).thenReturn(valueToReturn);
        doNothing().when(typedArray).recycle();
        return typedArray;
    }
}