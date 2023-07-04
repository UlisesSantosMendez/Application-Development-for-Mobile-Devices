package com.example.canvas2;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    EditText edt1,edt2,edt3,edt4;
    Button bt1;

    float periodosin,periodocos,amplitudsin,amplitudcos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1=findViewById(R.id.editTextNumberDecimal);
        edt2=findViewById(R.id.editTextNumberDecimal2);
        edt3=findViewById(R.id.editTextNumberDecimal3);
        edt4=findViewById(R.id.editTextNumberDecimal4);
        bt1=findViewById(R.id.button);



        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amplitudsin=Float.parseFloat(edt1.getText().toString());
                periodosin=Float.parseFloat(edt2.getText().toString());
                amplitudcos=Float.parseFloat(edt3.getText().toString());
                periodocos=Float.parseFloat(edt4.getText().toString());

                Lienzo l = new Lienzo(MainActivity.this);
                l.setAsin(amplitudsin);
                l.setPersin(periodosin);
                l.setAcos(amplitudcos);
                l.setPercos(periodocos);
                setContentView(l);
            }
        });
    }
}
