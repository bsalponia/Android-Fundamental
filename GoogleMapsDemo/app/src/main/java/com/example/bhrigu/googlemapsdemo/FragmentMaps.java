package com.example.bhrigu.googlemapsdemo;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class FragmentMaps extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Context context;

    private MapView mapView;

    private GoogleMap googleMap;
    private UiSettings uiSettings;

    private GoogleApiClient googleApiClient;
    private Location currentLocation;

    private static final LatLng NEW_DELHI= new LatLng(28.6139,77.2090);

    Double latitude, longitude;
    String dataId;
    LatLng latLng;

    private TextView textViewLatitude, textViewLongitude;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context= getActivity();

        if(googleApiClient==null){

            googleApiClient= new GoogleApiClient.Builder(context).addApi(LocationServices.API).
                    addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        textViewLatitude= (TextView)view.findViewById(R.id.textViewLatitude);
        textViewLongitude= (TextView)view.findViewById(R.id.textViewLongitude);
        mapView = (MapView) view.findViewById(R.id.mapView);

        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.i("tag","I'm in on Map ready");

        this.googleMap= googleMap;
        googleMap.setMyLocationEnabled(true);

        String jsonData= loadJsonFromAssets();

        try{

            JSONObject mainObject= new JSONObject(jsonData);

            JSONArray mainArray= mainObject.optJSONArray("dataList");

            for(int i=0; i<mainArray.length();i++){

                JSONObject jsonObject= mainArray.getJSONObject(i);

                latitude= Double.parseDouble(jsonObject.optString("latitude"));
                longitude= Double.parseDouble(jsonObject.optString("longitude"));
                String dataId= jsonObject.optString("dataId");

                latLng= new LatLng(latitude, longitude);

                if(i%2==0){

                    googleMap.addMarker(new MarkerOptions().position(latLng).title("Id: "+dataId));
                }else{

                    googleMap.addMarker(new MarkerOptions().position(latLng)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            .title("Id: "+dataId));
                }

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        LatLng latLng1= marker.getPosition();
                        String latLngString= latLng1.toString();

                        String lat= latLngString.substring(10, 22);
                        Log.i("tag", "latitude: "+ lat);
                        String lon= latLngString.substring(28, 40);

                        textViewLatitude.setText("Latitude: " +lat);
                        textViewLongitude.setText("Longitude: "+lon);

                        return false;
                    }
                });


            }

        }catch (Exception e){
            e.printStackTrace();
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NEW_DELHI, 10));

        uiSettings= googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
    }

    private String loadJsonFromAssets(){
        String jsonData= null;

        try {
            InputStream inputStream= getActivity().getAssets().open("data.json");

            int size= inputStream.available();
            byte[] bytes= new byte[size];

            inputStream.read(bytes);
            inputStream.close();

            jsonData= new String(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Log.i("tag","I'm in on onConnected");

        currentLocation= LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if(currentLocation!=null){
            Log.d("tag", "Lattitude: "+currentLocation.getLatitude()+ " Longitude: "+currentLocation.getLongitude());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}

