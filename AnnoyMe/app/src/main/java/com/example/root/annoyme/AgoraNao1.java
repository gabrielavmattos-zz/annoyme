package com.example.root.annoyme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


/**
 * Created by gabriela on 21/01/16.
 */
public class AgoraNao1 extends AppCompatActivity implements OnMapReadyCallback{

    private Double latitude, longitude;
    private ArrayList<String> listaRespostas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agoranao1);

        listaRespostas = getIntent().getStringArrayListExtra("respostas");

        latitude = Double.parseDouble(listaRespostas.get(1));
        longitude = Double.parseDouble(listaRespostas.get(2));

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Seu lugar"));

    }
}
