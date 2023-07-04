package com.example.ejercicio_1;
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
        jet=(EditText)findViewById(R.id.xetres);
        bd1=getIntent().getExtras();
        jet.append("" + bd1.getString("DATO_NOMBRE") + " " + bd1.getString("DATO_APELLIDO"));
    }
}
