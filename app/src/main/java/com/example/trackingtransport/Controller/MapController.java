package com.example.trackingtransport.Controller;

import android.content.Context;

import com.example.trackingtransport.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapController {
    private Context context;
    private GoogleMap mMap;
    private LatLng mTimeSquare;

    public MapController(Context context, GoogleMap googleMap){
        this.context = context;
        this.mMap = googleMap;
        mTimeSquare = new LatLng(40.758895, -73.985131);
    }

    public void setCustomerMarker(){
        BitmapDescriptor blackMarkerIcon = BitmapDescriptorFactory.fromResource(R.drawable.ic_car_marker);
        MarkerOptions markerOptions = new MarkerOptions().position(mTimeSquare).title(context.getString(R.string.time_square)).
                snippet(context.getString(R.string.i_am_snippet)).icon(blackMarkerIcon);
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mTimeSquare));
    }

    public void animateCamera(){
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mTimeSquare, 15f));
//    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mTimeSquare, 15f))
    }

}
