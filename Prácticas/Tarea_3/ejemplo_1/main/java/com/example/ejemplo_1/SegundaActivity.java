package com.example.ejemplo_1;

import android.os.*;
import android.app.*;
import android.widget.*;

public class SegundaActivity extends Activity {
    EditText jet;
    Bundle bd1;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_segunda);
        jet=(EditText)findViewById(R.id.xet1);
        bd1=getIntent().getExtras();
        jet.append("Hola "+ bd1.getString("NOMBRE"));
    }
}