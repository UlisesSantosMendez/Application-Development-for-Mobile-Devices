package com.example.ejemplo_1;

import android.os.*;
import android.app.*;
import android.view.View;
import android.view.View.*;
import android.content.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText jet;
    Button jbn;
    Bundle bd1;
    Intent itn;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jet=(EditText)findViewById(R.id.xet);
        jbn=(Button)findViewById(R.id.xbn);
        jbn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                itn=new Intent(MainActivity.this,SegundaActivity.class);
                bd1=new Bundle();
                bd1.putString("NOMBRE", jet.getText().toString());
                itn.putExtras(bd1);
                startActivity(itn);
            }
        });
    }
}