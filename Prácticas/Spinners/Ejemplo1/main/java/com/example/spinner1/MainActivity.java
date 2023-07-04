package com.example.spinner1;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;


public class MainActivity extends Activity {
    Spinner s;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        s = (Spinner) findViewById(R.id.xsp);
        s.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> av, View v, int i, long l) {
                Toast.makeText(MainActivity.this, s.getSelectedItem().toString(),
                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }
}