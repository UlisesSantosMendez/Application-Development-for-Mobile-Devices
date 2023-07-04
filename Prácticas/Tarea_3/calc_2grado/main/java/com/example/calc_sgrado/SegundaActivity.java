package com.example.calc_sgrado;

import android.os.*;
import android.app.*;
import android.widget.*;

public class SegundaActivity extends Activity {
    TextView jtv1,jtv2,jtv3;
    Bundle bd1;

    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_segunda);
        jtv1=(TextView)findViewById(R.id.x1);
        jtv2=(TextView)findViewById(R.id.x2);
        jtv3=(TextView)findViewById(R.id.sout);
        bd1=getIntent().getExtras();
        jtv1.setText(bd1.getString("DATO_A"));
        jtv2.setText(bd1.getString("DATO_B"));
        jtv3.append("" + bd1.getString("DATO_C"));

    }
}
