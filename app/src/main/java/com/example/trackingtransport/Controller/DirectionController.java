package com.example.trackingtransport.Controller;

import android.content.Context;
import android.os.Build;
import android.text.Layout;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.trackingtransport.Model.Route;
import com.example.trackingtransport.R;
import com.example.trackingtransport.Util.RetrofitInstance;
import com.example.trackingtransport.Util.Services;
import com.example.trackingtransport.View.MapsActivity;
import com.example.trackingtransport.View.MapsActivityInt;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.android.PolyUtil;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DirectionController {

    private List routeMarkerList;
    private Polyline routePolyline;
    private Context context;
    private GoogleMap map;
    private GeoApiContext geoApiContext;
    private MapsActivityInt view;


    public DirectionController(Context context, GoogleMap map,
                               GeoApiContext geoApiContext, MapsActivity view){
        this.map = map;
        this.routeMarkerList = new ArrayList<Marker>();
        this.context = context;
        this.geoApiContext = geoApiContext;
        this.view = view;
    }

    public void getRoute(){
        Services service = RetrofitInstance.getRetrofitInstance().create(Services.class);
    Call<DirectionsResult> call = service.doRegister("Time Square",
            "Chelsea Market", context.getString(R.string.google_maps_key));

    DirectionsApiRequest directionsApiRequest = new DirectionsApiRequest(geoApiContext);
    directionsApiRequest.alternatives(true);
    directionsApiRequest.origin("Sette Tecnologia para o Negocio").
            destination("Mercado Central de Belo Horizonte").setCallback(new PendingResult.Callback<DirectionsResult>() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onResult(DirectionsResult result) {
            Log.d("Direction", "Rota: " + result.routes[0].toString());
            Log.d("Direction", "Duration: " + result.routes[0].legs[0].duration);
            Log.d("Direction", "Distance: " + result.routes[0].legs[0].distance);

            DirectionsLeg legs = result.routes[0].legs[0];
            Route route = new Route(context.getString(R.string.time_square),
                    context.getString(R.string.chelsea_market), legs.startLocation.lat, legs.startLocation.lng,
                    legs.endLocation.lat, legs.endLocation.lng, result.routes[0].overviewPolyline.getEncodedPath());

            view.setMarkersAndRoute(route);
        }

        @Override
        public void onFailure(Throwable e) {

        }
    });

/*    call.enqueue(new Callback<DirectionsResult>() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onResponse(Call<DirectionsResult> call, Response<DirectionsResult> response) {
            if(response.body() != null){
                DirectionsLeg legs = response.body().routes[0].legs[0];
                Route route = new Route(context.getString(R.string.time_square),
                        context.getString(R.string.chelsea_market), legs.startLocation.lat, legs.startLocation.lng,
                        legs.endLocation.lat, legs.endLocation.lng, response.body().routes[0].overviewPolyline.getEncodedPath());

                setMarkersAndRoute(route);
            }
        }

        @Override
        public void onFailure(Call<DirectionsResult> call, Throwable t) {


        }
    });*/
    }

}
