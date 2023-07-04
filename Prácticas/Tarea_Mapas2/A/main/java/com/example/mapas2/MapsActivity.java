package com.example.mapas2;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.graphics.Point;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mapas2.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Button jbtnsatelite, jbtnCentrar, jbnAnimar, jbnMover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        jbtnsatelite=(Button)findViewById(R.id.btnSatelite);
        jbtnsatelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarOpciones();
            }
        });

        jbnMover = (Button)findViewById(R.id.btnMover);
        jbnMover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moverSevilla();
            }
        });

        jbnAnimar = (Button)findViewById(R.id.btnAnimar);
        jbnAnimar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animarSevilla();
            }
        });

        jbtnCentrar = (Button)findViewById(R.id.btnCentrar);
        jbtnCentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerPosicion();
            }
        });
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
        mMap = googleMap;

         mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng point) {
                Projection proj = mMap.getProjection();
                Point coord = proj.toScreenLocation(point);

                Toast.makeText(
                        MapsActivity.this,
                        "Click\n" +
                                "Lat: " + point.latitude + "\n" +
                                "Lng: " + point.longitude + "\n" +
                                "X: " + coord.x + " - Y: " + coord.y,
                        Toast.LENGTH_SHORT).show();
            }
        });

            mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            public void onCameraChange(CameraPosition position) {
                Toast.makeText(
                        MapsActivity.this,
                        "Cambio Cámara\n" +
                                "Lat: " + position.target.latitude + "\n" +
                                "Lng: " + position.target.longitude + "\n" +
                                "Zoom: " + position.zoom + "\n" +
                                "Orientación: " + position.bearing + "\n" +
                                "Ángulo: " + position.tilt,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cambiarOpciones(){
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    private void moverSevilla()
    {
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(19.5051195, -99.1487746), 10);

        mMap.moveCamera(camUpd1);
    }

    private void animarSevilla() {
        LatLng madrid = new LatLng(19.5051195, -99.1487746);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(madrid)   //Centramos el mapa en Madrid
                .zoom(10)         //Establecemos el zoom en 19
                .bearing(45)      //Establecemos la orientación con el noreste arriba
                .tilt(70)         //Bajamos el punto de vista de la cámara 70 grados
                .build();
        mMap.addMarker(new MarkerOptions().position(madrid).title("Estas en ESCOM"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(madrid));
        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);

        mMap.animateCamera(camUpd3);

    }

    private void obtenerPosicion(){
        CameraPosition camPos = mMap.getCameraPosition();

        LatLng coordenadas = camPos.target;
        double latitud = coordenadas.latitude;
        double longitud = coordenadas.longitude;

        Toast.makeText(this, "Lat: " + latitud + " | Long: " + longitud, Toast.LENGTH_SHORT).show();
    }
}