package com.example.trackingtransport.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.example.trackingtransport.Controller.DirectionController;
import com.example.trackingtransport.Controller.MapController;
import com.example.trackingtransport.Controller.MapsFactory;
import com.example.trackingtransport.Model.Route;
import com.example.trackingtransport.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, MapsActivityInt, LocationSource.OnLocationChangedListener {

    private GoogleMap mMap;
    private DirectionController controller;
    private GeoApiContext geoApiContext;
    private Location lastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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
    public void onMapReady(GoogleMap googleMap) {
        if(geoApiContext == null){
            geoApiContext = new GeoApiContext.Builder()
                    .apiKey(getString(R.string.google_maps_key))
                    .build();
        }

        mMap = googleMap;

        controller = new DirectionController(this, mMap, geoApiContext, this);
        controller.getRoute();

    }

    @Override
    public Marker setUpMarkerPositions(MarkerOptions markerOptions){
        return mMap.addMarker(markerOptions);
    }

    @Override
    public void addPolyline(PolylineOptions polylineOptions){
        mMap.addPolyline(polylineOptions);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setMarkersAndRoute(Route route){

        LatLng startLatLng = new LatLng(route.getStartLat(), route.getStartLng());
        MarkerOptions startMarkerOptions  = new MarkerOptions().position(startLatLng).
                title(route.getStartName()).icon(BitmapDescriptorFactory.fromBitmap(MapsFactory.drawMarker(this, "S")));
        LatLng endLatLng = new LatLng(route.getEndLat(), route.getEndLng());
        MarkerOptions endMarkerOptions = new MarkerOptions().position(endLatLng).
                title(route.getEndName()).icon(BitmapDescriptorFactory.fromBitmap(MapsFactory.drawMarker(this, "E")));

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setUpMarkerPositions(startMarkerOptions);
                setUpMarkerPositions(endMarkerOptions);

                PolylineOptions polylineOptions = MapsFactory.drawRoute(getApplicationContext());
                List<LatLng> pointsList = PolyUtil.decode(route.getOverviewPolyline());
                for (LatLng point : pointsList) {
                    polylineOptions.add(point);
                }
                addPolyline(polylineOptions);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(startLatLng, 15f));
            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        this.lastKnownLocation = location;
    }
}
