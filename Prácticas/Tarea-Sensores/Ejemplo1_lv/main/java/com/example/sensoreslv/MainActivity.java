package com.example.sensoreslv;

import android.app.*;
import android.database.DataSetObserver;
import android.os.*;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.*;

public class MainActivity extends Activity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado);
        ArrayList<ListaEntrada> al = new ArrayList<ListaEntrada>();
        al.add(new ListaEntrada(R.drawable.accelerometer, "ACCELEROMETER", "Proveedor:" +
                "icm4n607_acc"+"\nPotencia (ma):0.001"+"\nMáxima resolución:0.0012"+
                "\nRango:78.453156"));
        al.add(new ListaEntrada(R.drawable.magnetometer, "MAGNETOMETER", "Proveedor:" +
                "mmc5603"+"\nPotencia (ma):0.001"+"\nMáxima resolución:0.15"+
                "\nRango:4911.994"));
        al.add(new ListaEntrada(R.drawable.orientation, "ORIENTATION", "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:0.00390625"+
                "\nRango:360.0"));
        al.add(new ListaEntrada(R.drawable.giroscope, "GYROSCOPE", "Proveedor:" +
                "icm4n607_gyro"+"\nPotencia (ma):0.001"+"\nMáxima resolución:0.0011"+
                "\nRango:34.906574"));
        al.add(new ListaEntrada(R.drawable.light, "LIGHT", "Proveedor:" +
                "tmd2755_l"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:65535.0"));
        al.add(new ListaEntrada(R.drawable.proximity, "PROXIMITY", "Proveedor:" +
                "tmd2755_p"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:5.0"));
        al.add(new ListaEntrada(R.drawable.gravity, "GRAVITY", "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:0.0012"+
                "\nRango:39.22665"));
        al.add(new ListaEntrada(R.drawable.linearaccel, "LINEARACCEL", "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:0.0012"+
                "\nRango:39.22665"));
        al.add(new ListaEntrada(R.drawable.rotation_vector, "ROTATION_VECTOR", "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:5.9604645E-8"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.uncali_mag, "UNCALI_MAG", "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:0.15"+
                "\nRango:4911.994"));
        al.add(new ListaEntrada(R.drawable.game_rot, "GAME_ROTATION_VECTOR", "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:5.9604645E-8"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.ungyro, "UNCALI_GYRO", "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:0.0011"+
                "\nRango:34.906574"));
        al.add(new ListaEntrada(R.drawable.sig_motion, "SIGNIFICANT_MOTION", "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.step_detector, "STEP_DETECTOR", "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.step_counter, "STEP_COUNTER", "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:2.14748365E9"));
        al.add(new ListaEntrada(R.drawable.geomagnetic, "GEOMAGNETIC_ROTATION_VECTOR",
                "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:5.9604645E-8"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.disprot, "Disprot", "Proveedor:" +
                "Motorola"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:3.0"));
        al.add(new ListaEntrada(R.drawable.stationary, "STATIONARY_DETECT",
                "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.motion_detect, "MOTION_DETECT", "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.uncali_acce, "UNCALI_ACC", "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:0.0012"+
                "\nRango:78.453156"));
        al.add(new ListaEntrada(R.drawable.stowed, "Stowed", "Proveedor:" +
                "Motorola"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.flatup, "Flatup", "Proveedor:" +
                "Motorola"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.flatdown, "Flatdown", "Proveedor:" +
                "Motorola"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.camera_act, "Camera Activate", "Proveedor:" +
                "Motorola"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.chop, "ChopChop", "Proveedor:" +
                "Motorola"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.motoglance, "Motoglance", "Proveedor:" +
                "Motorola"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.motorola, "Ltv", "Proveedor:" +
                "Motorola"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.motorola, "FTM", "Proveedor:" +
                "Motorola"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.motorola, "LTS", "Proveedor:" +
                "Motorola"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.step_wake, "STEP_DETECTOR_WAKEUP", "Proveedor:" +
                "MTK"+"\nPotencia (ma):0.001"+"\nMáxima resolución:1.0"+
                "\nRango:1.0"));
        al.add(new ListaEntrada(R.drawable.capsense, "Moto CapSense Ch1", "Proveedor:" +
                "semtech"+"\nPotencia (ma):0.045"+"\nMáxima resolución:5.0"+
                "\nRango:5.0"));
        al.add(new ListaEntrada(R.drawable.capsense, "Moto CapSense Ch2", "Proveedor:" +
                "semtech"+"\nPotencia (ma):0.045"+"\nMáxima resolución:5.0"+
                "\nRango:5.0"));
        al.add(new ListaEntrada(R.drawable.gesture, "dt-gesture", "Proveedor:" +
                "ilitek"+"\nPotencia (ma):1.0"+"\nMáxima resolución:5.0"+
                "\nRango:5.0"));
        al.add(new ListaEntrada(R.drawable.capsense, "Moto CapSense Ch0", "Proveedor:" +
                "semtech"+"\nPotencia (ma):0.045"+"\nMáxima resolución:5.0"+
                "\nRango:5.0"));
        lv = (ListView)findViewById(R.id.ListView_listado);
        lv.setAdapter(new ListaAdapter(this, R.layout.activity_main, al) {
            public void onEntrada(Object o, View v){
                if(o!=null){
                    TextView texto_superior_entrada = (TextView)v.findViewById(R.id.textView_superior);
                    TextView texto_inferior_entrada = (TextView)v.findViewById(R.id.textView_inferior);
                    ImageView imagen_entrada = (ImageView) v.findViewById(R.id.imageView_imagen);
                    if(texto_superior_entrada != null){
                        texto_superior_entrada.setText(((ListaEntrada) o).get_textoEncima());
                    }
                    if(texto_inferior_entrada != null){
                        texto_inferior_entrada.setText(((ListaEntrada) o).get_textoDebajo());
                    }
                    if (imagen_entrada != null) {
                        imagen_entrada.setImageResource(((ListaEntrada) o).get_idImagen());
                    }
                }
            }
        });
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                ListaEntrada le = (ListaEntrada) av.getItemAtPosition(i);
                CharSequence cs = "Seleccionado: " + le.get_textoDebajo();
                Toast t = Toast.makeText(MainActivity.this, cs, Toast.LENGTH_SHORT);
                t.show();
            }
        });
    }
}