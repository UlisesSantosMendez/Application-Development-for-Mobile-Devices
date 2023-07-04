package com.example.archivos_1;

import android.app.*;
import android.widget.*;
import android.view.View.*;
import android.os.*;
import java.io.*;


public class MainActivity extends Activity {
    TextView tv;
    String s;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        tv =(TextView) findViewById(R.id.xtv);
        tv.append("\nAbriendo: res/raw/misdatos.txt"); // NOTA: Este es su archivo.
        is = getResources().openRawResource(R.raw.misdatos);
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr, 8192);
        try{
            while( null != (s=br.readLine()) )
                tv.append("\n" + s);
            is.close();
            isr.close();
            br.close();
        } catch(Exception e){
            tv.append("\n " + e);
        }
        tv.append("\nEnd of file.");
    }
}