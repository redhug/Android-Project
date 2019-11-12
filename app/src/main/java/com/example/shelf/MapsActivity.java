package com.example.shelf;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
       // mMap.setMyLocationEnabled(true);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String bookaddress=UserActivity.bookuseraddress;
        LatLng booklocation = getLocationFromAddress(this,bookaddress);
        mMap.addMarker(new MarkerOptions().position(booklocation).title("Book Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(booklocation));
        //CameraUpdate location = CameraUpdateFactory.newLatLngZoom(booklocation, 4.0f);
       // mMap.animateCamera(location);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
    }
    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context, Locale.getDefault());
        List<Address> address;
        LatLng geo = null;

        try {
            address = coder.getFromLocationName(strAddress, 10);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            geo = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {
            Toast.makeText(this,"Address not found on Maps", Toast.LENGTH_LONG).show();
            Log.d("Address Not found",ex.toString());
            ex.printStackTrace();

        }

        return geo;
    }
}
