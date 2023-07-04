package com.example.servicios;

import android.app.*;
import android.app.Activity;
import android.content.Intent;
import android.os.*;
import android.view.View;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity {
    private TextView jtv;
    private Button jbn,jbp,jbc;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jtv = (TextView)findViewById(R.id.xtvT);
        jbn = (Button)findViewById(R.id.xbnI);
        jbp = (Button)findViewById(R.id.xbnP);
        jbc = (Button)findViewById(R.id.xbnC);
        jbn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                initCrono();
            }
        });
        Button stopButton = (Button) findViewById(R.id.xbnT);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                stopCrono();
            }
        });
        jbp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                pauseCrono();
            }
        });
        jbc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                continueCrono();
            }
        });
        MiCrono.setUpdateListener(this);
    }
    @Override
    protected void onDestroy() {
        stopCrono();
        super.onDestroy();
    }
    private void initCrono() {
        Intent in = new Intent(this, MiCrono.class);
        startService(in);
    }
    private void stopCrono() {
        Intent in = new Intent(this, MiCrono.class);
        stopService(in);
    }
    public void refreshCrono(double t) {

        jtv.setText(String.format("%.2f", t) + " segs");
    }
    private void pauseCrono() {
        Intent in = new Intent(this, MiCrono.class);

    }
    private void continueCrono(){

        Intent in = new Intent(this, MiCrono.class);
    }
}