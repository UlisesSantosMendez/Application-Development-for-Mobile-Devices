import android.Manifest;
import android.app.*;
import android.content.*;
import android.location.*;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
public class MainActivity extends Activity {
    private Button jbnAct, jbnDes;
    private TextView jtvLat, jtvLon, jtvPre, jtvEdo;
    private LocationManager locManager;
    private LocationListener locListener;
    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jbnAct = (Button)findViewById(R.id.xbnAct);
        jbnDes = (Button)findViewById(R.id.xbnDes);
        jtvLat = (TextView)findViewById(R.id.xtvLat);
        jtvLon = (TextView)findViewById(R.id.xtvLon);
        jtvPre = (TextView)findViewById(R.id.xtvPre);
        jtvEdo = (TextView)findViewById(R.id.xtvEdo);
        jbnAct.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                comenzarLocalizacion();
            }
        });
        jbnDes.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                locManager.removeUpdates(locListener);
            }
        });
        ActivityResultLauncher<String[]> locationPermissionRequest;
        locationPermissionRequest = registerForActivityResult(new ActivityResultContracts
                        .RequestMultiplePermissions(), result -> {
                    Boolean fineLocationGranted = result.getOrDefault(
                            Manifest.permission.ACCESS_FINE_LOCATION, false);
                    Boolean coarseLocationGranted = result.getOrDefault(
                            Manifest.permission.ACCESS_COARSE_LOCATION,false);
                    if (fineLocationGranted != null && fineLocationGranted) {
                        // Precise location access granted.
                    } else if (coarseLocationGranted != null && coarseLocationGranted) {
                        // Only approximate location access granted.
                    } else {
                        // No location access granted.
                    }
                }
        );
        locationPermissionRequest.launch(new String[] { //locationPermissionRequest
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }
    private void comenzarLocalizacion()    {

        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        mostrarPosicion(loc);
        locListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                mostrarPosicion(location);
            }
            public void onProviderDisabled(String provider){
                jtvEdo.setText("Provider OFF");
            }
            public void onProviderEnabled(String provider){
                jtvEdo.setText("Provider ON ");
            }
            public void onStatusChanged(String provider, int status, Bundle extras){
                Log.i("", "Provider Status: " + status);
                jtvEdo.setText("Provider Status: " + status);
            }
        };
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, locListener);
    }
    private void mostrarPosicion(Location loc) {
        if(loc != null){
            jtvLat.setText("Latitud: " + String.valueOf(loc.getLatitude()));
            jtvLon.setText("Longitud: " + String.valueOf(loc.getLongitude()));
            jtvPre.setText("Precision: " + String.valueOf(loc.getAccuracy()));
            Log.i("", String.valueOf(loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
        }else{
            jtvLat.setText("Latitud: (sin_datos)");
            jtvLon.setText("Longitud: (sin_datos)");
            jtvPre.setText("Precision: (sin_datos)");
        }
    }
}


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Localizacion GPS" />
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        android:id = "@+id/xbnAct" />
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        android:id = "@+id/xbnDes" />
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Latitud"
        android:id="@+id/xtvLat"/>
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Longitud"
        android:id="@+id/xtvLon"/>
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="tvPre"
        android:id="@+id/xtvPre"/>
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="xtvEdo"
        android:id="@+id/xtvEdo"/>
</LinearLayout>

    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

    <!-- Include only if your app benefits from precise location access. -->
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
