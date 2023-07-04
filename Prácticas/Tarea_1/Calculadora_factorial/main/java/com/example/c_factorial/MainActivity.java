package com.example.c_factorial;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.view.View.*;

public class MainActivity extends Activity implements OnClickListener{
    TextView jtv1,jtv2,jtv3;
    Button jbn1,jbn2,jbn3,jbnclear,jbn4,jbn5,jbn6,jbn7,jbn8,jbn9,jbnf,jbn0,jbneq;
    int i,n;
    String s="";
    String saux=" ";
    long factorial=1;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jtv1=(TextView)findViewById(R.id.xtv1);
        jtv2=(TextView)findViewById(R.id.xtv2);
        jtv3=(TextView)findViewById(R.id.xtv3);
        jbn1=(Button)findViewById(R.id.xbn1);
        jbn2=(Button)findViewById(R.id.xbn2);
        jbn3=(Button)findViewById(R.id.xbn3);
        jbn4=(Button)findViewById(R.id.xbn4);
        jbn5=(Button)findViewById(R.id.xbn5);
        jbn6=(Button)findViewById(R.id.xbn6);
        jbn7=(Button)findViewById(R.id.xbn7);
        jbn8=(Button)findViewById(R.id.xbn8);
        jbn9=(Button)findViewById(R.id.xbn9);
        jbn0=(Button)findViewById(R.id.xbn0);
        jbnclear=(Button)findViewById(R.id.xbnclear);
        jbnf=(Button)findViewById(R.id.xbnf);
        jbneq=(Button)findViewById(R.id.xbneq);
        jtv3.setOnClickListener(this);
        jbn1.setOnClickListener(this);
        jbn2.setOnClickListener(this);
        jbn3.setOnClickListener(this);
        jbn4.setOnClickListener(this);
        jbn5.setOnClickListener(this);
        jbn6.setOnClickListener(this);
        jbn7.setOnClickListener(this);
        jbn8.setOnClickListener(this);
        jbn9.setOnClickListener(this);
        jbn0.setOnClickListener(this);
        jbnclear.setOnClickListener(this);
        jbnf.setOnClickListener(this);
        jbneq.setOnClickListener(this);
    }
    public void onClick(View v){
        if(v.getId()== jbn1.getId()){
            if(jtv3.getText().toString().equals("0")){
                jtv3.setText("1");
            }
            else{
                jtv3.setText(jtv3.getText()+"1");
            }
        }
        if(v.getId()== jbn2.getId()){
            if(jtv3.getText().toString().equals("0")){
                jtv3.setText("2");
            }
            else{
                jtv3.setText(jtv3.getText()+"2");
            }
        }
        if(v.getId()== jbn3.getId()){
            if(jtv3.getText().toString().equals("0")){
                jtv3.setText("3");
            }
            else{
                jtv3.setText(jtv3.getText()+"3");
            }
        }
        if(v.getId()== jbn4.getId()){
            if(jtv3.getText().toString().equals("0")){
                jtv3.setText("4");
            }
            else{
                jtv3.setText(jtv3.getText()+"4");
            }
        }
        if(v.getId()== jbn5.getId()){
            if(jtv3.getText().toString().equals("0")){
                jtv3.setText("5");
            }
            else{
                jtv3.setText(jtv3.getText()+"5");
            }
        }
        if(v.getId()== jbn6.getId()){
            if(jtv3.getText().toString().equals("0")){
                jtv3.setText("6");
            }
            else{
                jtv3.setText(jtv3.getText()+"6");
            }
        }
        if(v.getId()== jbn7.getId()){
            if(jtv3.getText().toString().equals("0")){
                jtv3.setText("7");
            }
            else{
                jtv3.setText(jtv3.getText()+"7");
            }
        }
        if(v.getId()== jbn8.getId()){
            if(jtv3.getText().toString().equals("0")){
                jtv3.setText("8");
            }
            else{
                jtv3.setText(jtv3.getText()+"8");
            }
        }
        if(v.getId()== jbn9.getId()){
            if(jtv3.getText().toString().equals("0")){
                jtv3.setText("9");
            }
            else{
                jtv3.setText(jtv3.getText()+"9");
            }
        }
        if(v.getId()== jbn0.getId()){
            if(jtv3.getText().toString().equals("0")){
                jtv3.setText("0");
            }
            else{
                jtv3.setText(jtv3.getText()+"0");
            }
        }
        if(v.getId()== jbnclear.getId()){
            jtv3.setText("0");
            n=0;
            factorial=1;
        }
        n= Integer.parseInt(jtv3.getText().toString());
        if(v.getId()== jbnf.getId()){
            s="  !";
            n= Integer.parseInt(jtv3.getText().toString());
        }
        if(v.getId()== jbneq.getId()){
            s=n+s+"  =";
            for(i=n;i>0;i--) {
                factorial = factorial * i;
            }
            jtv3.setText(s+factorial);
        }
    }
}