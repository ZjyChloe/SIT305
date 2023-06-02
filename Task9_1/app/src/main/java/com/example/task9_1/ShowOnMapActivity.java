package com.example.task9_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.task9_1.data.DatabaseClient;
import com.example.task9_1.data.Item;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


import java.util.List;

public class ShowOnMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    List<Item> tempList;


    double[] latitude,longitude;
    String[] locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_on_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        getLatLng(mapFragment);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        latitude = new double[tempList.size()];
        longitude = new double[tempList.size()];

        for(int i = 0 ; i < tempList.size(); i++){

            Item item = tempList.get(i);
            latitude[i] = item.getLatitude();
            longitude[i] = item.getLongitude();


            googleMap.addMarker(new MarkerOptions()
                    .title(item.getLocation())
                    .position(new LatLng(latitude[i],longitude[i])));
        }

        LatLng sydney = new LatLng(-37.813629, 144.963058);

        googleMap.addMarker(new MarkerOptions()
                .title("Melbourne Marker")
                .position(sydney)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,8));

        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

    public void getLatLng(SupportMapFragment mapFragment) {
        class GetLatLng extends AsyncTask<Void, Void, List<Item>> {

            @Override
            protected List<Item> doInBackground(Void... voids) {
                List<Item> itemList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getItemDatabase()
                        .itemDAO()
                        .getAll();

                return itemList;
            }

            @Override
            protected void onPostExecute(List<Item> items) {
                super.onPostExecute(items);
                tempList = items;
                mapFragment.getMapAsync(ShowOnMapActivity.this);
            }
        }

        GetLatLng gt = new GetLatLng();
        gt.execute();
    }




}