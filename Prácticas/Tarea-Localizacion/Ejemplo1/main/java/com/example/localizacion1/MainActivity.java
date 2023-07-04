package com.example.localizacion1;

import android.Manifest;
import android.app.*;
import android.content.*;
import android.content.pm.PackageManager;
import android.location.*;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

public class MainActivity extends Activity {
    private TextView lblLatitud, lblLongitud, lblPrecision;
    private TextView lblEstado;
    private LocationManager locManager;
    private LocationListener locListener;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        Button btnActualizar = (Button) findViewById(R.id.xbnAct);
        Button btnDesactivar = (Button) findViewById(R.id.xbnDes);
        lblLatitud = (TextView) findViewById(R.id.xtvLat);
        lblLongitud = (TextView) findViewById(R.id.xtvLon);
        lblPrecision = (TextView) findViewById(R.id.xtvPre);
        lblEstado = (TextView) findViewById(R.id.xtvEdo);
        btnActualizar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                comenzarLocalizacion();
            }
        });
        btnDesactivar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                locManager.removeUpdates(locListener);
            }
        });
    }

    public void comenzarLocalizacion() {
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        mostrarPosicion(loc);
        locListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                mostrarPosicion(location);
            }

            public void onProviderDisabled(String provider) {
                lblEstado.setText("Provider OFF");
            }

            public void onProviderEnabled(String provider) {
                lblEstado.setText("Provider ON ");
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.i("", "Provider Status: " + status);
                lblEstado.setText("Provider Status: " + status);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, locListener);
    }
    private void mostrarPosicion(Location loc) {
        if(loc != null){
            lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
            lblLongitud.setText("Longitud: " + String.valueOf(loc.getLongitude()));
            lblPrecision.setText("Precision: " + String.valueOf(loc.getAccuracy()));
            Log.i("", String.valueOf(loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
        }else{
            lblLatitud.setText("Latitud: (sin_datos)");
            lblLongitud.setText("Longitud: (sin_datos)");
            lblPrecision.setText("Precision: (sin_datos)");
        }
    }
}