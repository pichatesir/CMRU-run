package cmru.siriratanapaisalkul.pichate.cmrurun;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double cmruLatADouble = 18.8071096, cmruLngADouble=98.9844154;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Setup to CMRU
        LatLng latLng = new LatLng(cmruLatADouble, cmruLngADouble);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

        createStationMarker();


    }   //Main Method

    private void createStationMarker() {

        MyData myData = new MyData();
        double[] latDoubles = myData.getLatStationDoubles();
        double[] lngDoubles = myData.getLngStationDoubles();
        for (int i=0;i<latDoubles.length;i++) {
            LatLng latLng = new LatLng(latDoubles[i], lngDoubles[i]);
            mMap.addMarker(new MarkerOptions().position(latLng));
        }

    }   //CreateStationMarker
}   //Main Class
