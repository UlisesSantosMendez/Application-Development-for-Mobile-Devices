package com.example.sensoresejem1;

import android.app.*;
import android.os.Bundle;
import java.util.*;
import android.content.*;
import android.hardware.*;
import android.view.View;
import android.widget.*;
import android.view.View.*;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
    TextView jtv;
    Sensor s;
    SensorManager sm;
    List<Sensor> l;
    String c, v;
    int n, t;
    float p, r, d;

    //private ListView lv;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        //ArrayList<ListaEntrada> al = new ArrayList<ListaEntrada>();
        jtv = (TextView) findViewById(R.id.xtv);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        l = sm.getSensorList(Sensor.TYPE_ALL);
        n = l.size();
        jtv.append("\nSensores detectados: " + n + "\n");
        for (int i = 0; i < n; i++) {
            s = l.get(i);
            t = s.getType();
            c = s.getName();
            v = s.getVendor();
            p = s.getPower();
            r = s.getResolution();
            d = s.getMaximumRange();
            jtv.append("\nTipo de sensor: " + t + ", " + c);
            jtv.append("\nProveedor: " + v);
            jtv.append("\nPotencia (ma): " + p);
            jtv.append("\nMáxima resolución: " + r);
            jtv.append(", rango: " + d + "\n");
            /*
            al.add(new ListaEntrada(R.drawable.accelerometer, c, "Tipo de sensor: "+t+
                    "\nProveedor: "+v+ "\nPotencia(ma): "+p+ "\nMáxima resolución: "+r+
                    "\nRango: "+d+""));
            lv = (ListView)findViewById(R.id.ListView_listado);
        }
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
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                ListaEntrada le = (ListaEntrada) av.getItemAtPosition(i);
                CharSequence cs = "Seleccionado: " + le.get_textoDebajo();
                Toast t = Toast.makeText(MainActivity.this, cs, Toast.LENGTH_SHORT);
                t.show();
            }
        });

             */
        }
    }
}