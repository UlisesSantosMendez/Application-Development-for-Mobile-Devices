package com.example.ejemplo_1;

import android.os.*;
import android.app.*;
import android.view.View;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity implements OnClickListener {
    Button jbn1,jbn2,jbn3;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jbn1=(Button)findViewById(R.id.xbn1);
        jbn1.setOnClickListener(bn1Listener);
        jbn2=(Button)findViewById(R.id.xbn2);
        jbn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastMessage("Boton digitado: xbn2\nUtiliza: new OnClickListener()");
            }
        });
        jbn3=(Button)findViewById(R.id.xbn3);
        jbn3.setOnClickListener(this);
    }
    private OnClickListener bn1Listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            showToastMessage("Boton digitado: xbn1\nUtiliza: clase btn1Listener");
        }
    };
    public void onClick(View v){
        showToastMessage("Boton digitado: xbn3\nUtiliza: implements OnClickListener");
    }
   public void xbn4DesdeXML(View v){
       showToastMessage("Boton digitado: xbn4\nInvoca al metodo desde el XML");
   }
   private void showToastMessage(String msg){
        Toast toast=Toast.makeText(this,msg,Toast.LENGTH_SHORT);
        toast.show();
   }
}