package com.example.calc_sgrado;

import android.os.*;
import android.app.*;
import android.view.View;
import android.view.View.*;
import android.widget.*;
import android.content.*;

public class MainActivity extends Activity {
    EditText jet1,jet2,jet3;
    Button jbn1;
    Bundle bd1;
    Intent itn;
    double va,vb,vc,x1,x2,D;
    String s,res,res1;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jet1=(EditText)findViewById(R.id.xa);
        jet2=(EditText)findViewById(R.id.xb);
        jet3=(EditText)findViewById(R.id.xc);
        jbn1=(Button)findViewById(R.id.xbtn1);
        jbn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                va=Double.parseDouble(jet1.getText().toString());
                vb=Double.parseDouble(jet2.getText().toString());
                vc=Double.parseDouble(jet3.getText().toString());
                //APLICACION DE LA FORMULA GENERAL
                x1=((-(vb)+(Math.sqrt(Math.pow(vb,2)-(4*va*vc))))/2*va);
                x2=((-(vb)-(Math.sqrt(Math.pow(vb,2)-(4*va*vc))))/2*va);
                D=Math.pow(vb,2)-(4*va*vc);
                if(D>=0){
                   s="Tiene solución real";
                }
                else{
                    s="No tiene solución real";
                    x1=0;
                    x2=0;
                }
                itn=new Intent(MainActivity.this, SegundaActivity.class);
                bd1=new Bundle();
                res = String.format("%f",x1);
                res1 = String.format("%f", x2);
                bd1.putString("DATO_A",res);
                bd1.putString("DATO_B",res1);
                bd1.putString("DATO_C",s);
                itn.putExtras(bd1);
                startActivity(itn);
            }
        });
    }
}