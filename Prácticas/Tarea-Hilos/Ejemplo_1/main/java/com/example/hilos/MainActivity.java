package com.example.hilos;

import android.app.*;
import android.view.View.*;
import android.view.*;
import android.widget.*;
import android.os.*;

public class MainActivity extends Activity implements OnClickListener{
    private EditText jet1;
    private Button jbn1;
    private TextView jtv2;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jet1 = (EditText) findViewById(R.id.xet1);
        jbn1 = (Button) findViewById(R.id.xbn1);
        jtv2 = (TextView) findViewById(R.id.xtv2);
        jbn1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        try{
            int num = Integer.parseInt(jet1.getText().toString());
            Thread.sleep(num*1000);
        }
        catch (NumberFormatException e){
            Toast.makeText(this,"Ingresar los segundos.", Toast.LENGTH_SHORT).show();
        }
        catch (InterruptedException e) { }

        /*
        try{
            final int num = Integer.parseInt(jet1.getText().toString());
            new Thread(new Runnable(){
                public void run() {
                    try {
                        //jtv2.setText("El hilo se bloqueó " + num + " segundos...");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                jtv2.setText("El hilo se bloquea durante "+ num + " segundos");
                            }
                        });
                        Thread.sleep(num*1000);
                        onPause();

                    }
                    catch (InterruptedException ie) {}
                }
            }).start();
        }
        catch (NumberFormatException e){
            Toast.makeText(this,"ingresar segundos...", Toast.LENGTH_SHORT).show();
        }
         */
    }
}