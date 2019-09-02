package com.example.trackingtransport.View;

import com.example.trackingtransport.Model.Route;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public interface MapsActivityInt {
    Marker setUpMarkerPositions(MarkerOptions markerOptions);
    void addPolyline(PolylineOptions polylineOptions);
    void setMarkersAndRoute(Route route);
}
