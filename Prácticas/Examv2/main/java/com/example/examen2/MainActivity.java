package com.example.examen2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentA.FragmentoListener{

    TextView tv1,tv2,tv4,tv5,tv6,tv7,tv8;
    EditText jet1,jet2,jet3,jet4;
    Button bt3;
    int numfilasa=0,numcoluma=0,numfilasb=0,numcolumb=0;
    int [][] matriza;
    int [][] matrizb;
    boolean llenoa=false,llenob=false;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        jet1=(EditText) findViewById(R.id.xel1);
        jet2=(EditText) findViewById(R.id.xel2);
        jet3=(EditText) findViewById(R.id.xel3);
        jet4=(EditText) findViewById(R.id.xel4);

        tv1= findViewById(R.id.tv1);
        tv2= findViewById(R.id.tv2);
        tv4= findViewById(R.id.tv4);
        tv5= findViewById(R.id.tv5);
        tv6= findViewById(R.id.tv6);
        tv7= findViewById(R.id.tv7);
        tv8= findViewById(R.id.tv8);

        bt3=(Button)findViewById(R.id.xbnt3);

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                androidx.fragment.app.FragmentManager fm = getSupportFragmentManager();
                androidx.fragment.app.FragmentTransaction ft = fm.beginTransaction();

                String filasa = String.valueOf(jet1.getText());
                String columnasa = String.valueOf(jet2.getText());
                String filasb = String.valueOf(jet3.getText());
                String columnasb = String.valueOf(jet4.getText());

                numcoluma=Integer.parseInt(columnasa);
                numfilasa=Integer.parseInt(filasa);
                numfilasb=Integer.parseInt(filasb);
                numcolumb=Integer.parseInt(columnasb);

                Bundle args = new Bundle();
                args.putInt("AF", numfilasa);
                args.putInt("AC",numcoluma);
                args.putInt("BF",numfilasb);
                args.putInt("BC",numcolumb);
                args.putBoolean("LA",llenoa);
                args.putBoolean("LB",llenob);

                if(Integer.parseInt(columnasa) > 3 || Integer.parseInt(filasa) > 3 || Integer.parseInt(columnasb) > 3 || Integer.parseInt(filasb) > 3){
                    Toast.makeText(MainActivity.this, "Solo dimensiones menores o iguales a 3", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(Integer.parseInt(columnasa) != Integer.parseInt(filasb)){
                        finishAffinity();
                        //Toast.makeText(MainActivity.this, "Se cierra "+columnasa+" "+filasb, Toast.LENGTH_SHORT).show();
                    }
                    else{

                        if(llenob == true && llenoa == true){

                            cambiarIntent();
                        }else{
                            FragmentA mf = new FragmentA();
                            mf.setArguments(args);
                            ft.replace(R.id.xfl,mf, "editor");
                            ft.commit();
                        }
                    }

                }
            }
        });
    }

    public void cambiarIntent(){
        Intent itn = new Intent(MainActivity.this, Resultado.class);
        ArrayList<Matriz> matrices = new ArrayList<>();
        matrices.add(new Matriz("MatA",matriza,numfilasa,numcoluma));
        matrices.add(new Matriz("MatB",matrizb,numfilasb,numcolumb));
        itn.putExtra("matrices",matrices);
        startActivity(itn);
    }

    @Override
    public void digitado(int r, int[][] matriz,int dest) {


        if (r == FragmentA.OK) {
            if(dest==0){
                matriza=new int[numfilasa][numcoluma];
                matriza=matriz;
                llenoa=true;
            }
            else{
                matrizb=new int[numfilasb][numcolumb];
                matrizb=matriz;
                llenob=true;
            }

        }
        androidx.fragment.app.FragmentManager fm = getSupportFragmentManager();
        androidx.fragment.app.Fragment fe = fm.findFragmentByTag("editor");
        androidx.fragment.app.FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fe);
        ft.detach(fe);
        ft.commit();
    }
}