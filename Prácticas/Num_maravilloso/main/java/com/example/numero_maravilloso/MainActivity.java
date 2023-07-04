package com.example.numero_maravilloso;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.view.View.*;

public class MainActivity extends Activity implements OnClickListener{

    int n,m;
    TextView jtv2;
    EditText jet1;
    Button jbn1;
    String s="";

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jtv2=(TextView)findViewById(R.id.xtv2);
        jet1=(EditText)findViewById(R.id.xet1);
        jbn1=(Button)findViewById(R.id.xbn1);
        jbn1.setOnClickListener(this);
        /*
        jtv1.append("\n"+naux);
        while(n != 1) {
            if(n%2 == 0) {
                n=n/2;
                jtv1.append("\n"+n);
            } else {
                n=(n*3)+1;
                jtv1.append("\n"+n);
            }
        }
        jtv1.append("\nEs maravilloso el numero " + naux);

         */
    }
    public void onClick(View u){
        m=n=Integer.parseInt(jet1.getText()+"");
        while(n != 1) {
            if(n%2 == 0) {
                n=n/2;
                s=s + "\n" + n;
            } else {
                n=(n*3)+1;
                s=s + "\n" + n;
            }
        }
        jtv2.setText(m+s);
        jtv2.append("\nEs maravilloso el numero " + m);

    }
}