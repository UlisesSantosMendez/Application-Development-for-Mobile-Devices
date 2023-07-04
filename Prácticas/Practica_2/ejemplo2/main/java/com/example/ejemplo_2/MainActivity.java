package com.example.ejemplo_2;

import android.os.*;
import android.app.*;
import android.view.View;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity implements OnClickListener {
    ImageButton jib1;
    ToggleButton jtb1;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jib1=(ImageButton)findViewById(R.id.xib1);
        jtb1=(ToggleButton)findViewById(R.id.xtb1);
        jib1.setOnClickListener(this);
        jtb1.setOnClickListener(this);
    }
    private void showToastMessage(String s){
        Toast t= Toast.makeText(this,s,Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.xib1){
            showToastMessage("Boton: ImageButton");
        }
        else{
            if(jtb1.isChecked()){
                showToastMessage("Boton: ToggleButton en ON");
            }
            else{
                showToastMessage("Boton: ToggleButton en OFF");
            }
        }
    }
}