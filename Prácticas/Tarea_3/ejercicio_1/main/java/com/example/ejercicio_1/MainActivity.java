package com.example.ejercicio_1;

import android.os.*;
import android.app.*;
import android.view.View;
import android.widget.*;
import android.view.View.*;
import android.content.*;


public class MainActivity extends Activity {
    EditText jet1, jet2;
    Button jbn1;
    Bundle bd1;
    Intent itn;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jet1=(EditText)findViewById(R.id.xet1);
        jet2=(EditText)findViewById(R.id.xet2);
        jbn1=(Button)findViewById(R.id.xbtn1);
        jbn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                itn=new Intent(MainActivity.this, SegundaActivity.class);
                bd1=new Bundle();
                bd1.putString("DATO_NOMBRE", jet1.getText().toString());
                bd1.putString("DATO_APELLIDO",jet2.getText().toString());
                itn.putExtras(bd1);
                startActivity(itn);
            }
        });
    }
}