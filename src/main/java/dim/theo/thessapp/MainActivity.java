package dim.theo.thessapp;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;

import java.util.ArrayList;

import dim.theo.thessapp.helpers.Helper;
import dim.theo.thessapp.presenter.MainPresenterImpl;

import static dim.theo.thessapp.helpers.Helper.distanceFrom;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnCameraChangeListener {

    private Helper helper;
    private GoogleMap mMap;
    public static final String TAG = "MAINACTIVITY";

    private TypedArray imgs;
    private TypedArray markerIcons;

    private LatLng mapCenter;

    // LatLng sydney = new LatLng(-34, 151);
    private ArrayList<LatLng> latLngArrayList = new ArrayList<LatLng>() {{
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

        add(new LatLng(40.157899, 22.11611));
        add(new LatLng(40.257800, 22.22622));
        add(new LatLng(40.357888, 22.33633));
        add(new LatLng(40.457877, 22.44644));
        add(new LatLng(40.557866, 22.55655));
        add(new LatLng(40.667855, 22.66666));
        add(new LatLng(40.757844, 22.77677));
        add(new LatLng(40.857833, 22.88688));
        add(new LatLng(40.957822, 22.99699));
        add(new LatLng(40.517811, 22.00611));
        add(new LatLng(40.997822, 22.51622));
        add(new LatLng(40.227833, 22.31633));
        add(new LatLng(40.337844, 22.72644));
    }};

    public ArrayList<Marker> markerArrayList = new ArrayList<>(latLngArrayList.size());

    public static final int DISTANCE = 50000;
    private static final LatLng SKG_VIEW = new LatLng(40.6402778, 22.9438889);

    private MainPresenterImpl mainPresenter;

    double visibleWidth;
    double radius1, radius2, radius3, radius4, radius5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenterImpl();
        helper = new Helper(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        imgs = getResources().obtainTypedArray(R.array.array_imgs);
        markerIcons = getResources().obtainTypedArray(R.array.array_marker_icons);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setOnMarkerClickListener(this);
        mMap.setOnCameraChangeListener(this);

        mapCenter = mMap.getCameraPosition().target;
        Log.i(TAG, "mapCENTER Lat == " + mapCenter.latitude + "  Lng ==" + mapCenter.longitude);

        addMarkers();
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Log.i(TAG, "onMarkerClick ");

        double lat = marker.getPosition().latitude;
        double lngt = marker.getPosition().longitude;

        final LatLng place = new LatLng(lat, lngt);

        final Handler handler = new Handler();
        final int[] i = {0};

        handler.post(new Runnable() {
            @Override
            public void run() {
                marker.remove();
                mMap.addMarker(new MarkerOptions()
                        .position(place)
                        .title("Same  Place")
                        .icon(BitmapDescriptorFactory.fromResource(imgs.getResourceId(i[0], -1))));

                i[0]++;

                if (i[0] < 4) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 128);
                }
            }
        });

        return true;
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        // update mapCenter
        mapCenter = mMap.getCameraPosition().target;
//        Log.i("onCameraChange", "mapCENTER Lat == " + mapCenter.latitude + "  Lng ==" + mapCenter.longitude);

        VisibleRegion visibleRegion = mMap.getProjection().getVisibleRegion();
        LatLng nearLeft = visibleRegion.nearLeft;
        LatLng nearRight = visibleRegion.nearRight;
        LatLng farLeft = visibleRegion.farLeft;
        LatLng farRight = visibleRegion.farRight;

        visibleWidth = distanceFrom(nearLeft.latitude, nearLeft.longitude, nearRight.latitude, nearRight.longitude);
        radius1 = visibleWidth / 10;
        radius2 = visibleWidth / 8;
        radius3 = visibleWidth / 6;
        radius4 = visibleWidth / 4;
        radius5 = visibleWidth / 2;
//        double visibleHeight = distanceFrom(farLeft.latitude, farLeft.longitude, farRight.latitude, farRight.longitude);

        Toast.makeText(this, "Width is  " + String.valueOf(visibleWidth), Toast.LENGTH_LONG).show();

        // reCalculate markers distance from Center
        reCalculateDistance();

    }

    private void reCalculateDistance() {
        Location tempLocation = new Location("loc1");
        Location mapCenterLocation = new Location("mapCenter");

        mapCenterLocation.setLatitude(mapCenter.latitude);
        mapCenterLocation.setLongitude(mapCenter.longitude);

        for (LatLng pos : latLngArrayList) {
            tempLocation.setLatitude(pos.latitude);
            tempLocation.setLongitude(pos.longitude);

//            Log.i(TAG, "DISTANCE is :  " + DISTANCE + "    radius1 is : " + radius1);

            if (mapCenterLocation.distanceTo(tempLocation) < radius1) {
                resizeIcon(pos, 8);
            } else if(mapCenterLocation.distanceTo(tempLocation) < radius2) {
                resizeIcon(pos, 7);
            } else if(mapCenterLocation.distanceTo(tempLocation) < radius3) {
                resizeIcon(pos, 6);
            } else if(mapCenterLocation.distanceTo(tempLocation) < radius4) {
                resizeIcon(pos, 5);
//            } else if(mapCenterLocation.distanceTo(tempLocation) < radius5) {
            } else {
                resizeIcon(pos, 4);
            }
        }

    }

    private void resizeIcon(LatLng pos, int scaleFactor) {
        int i = removeMarker(pos);
        Bitmap halfsizeBitmap = helper.scaleBitmap(scaleFactor);
        markerArrayList.add(i, mMap.addMarker(new MarkerOptions()
                .position(pos)
                .title("RESIZE ICON")
                .icon(BitmapDescriptorFactory.fromBitmap(halfsizeBitmap))));
    }



    private int removeMarker(LatLng pos) {
        return mainPresenter.removeMarkers(markerArrayList, pos);
    }

    private void addMarkers() {
        int i = 0;

        for (LatLng pos : latLngArrayList) {
            markerArrayList.add(i, mMap.addMarker(new MarkerOptions()
                    .position(pos)
                    .title("Marker in Thessaloniki")
                    .icon(BitmapDescriptorFactory.fromResource(markerIcons.getResourceId(i, -1)))));
            i++;
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SKG_VIEW, 8));
    }

}
