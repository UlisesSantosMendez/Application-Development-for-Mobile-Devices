package com.example.figures3d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6;
    String figura;
    Bundle bdl;
    Intent itn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout constraintLayout = findViewById(R.id.cnstrntLyt);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        b4=findViewById(R.id.button4);
        b5=findViewById(R.id.button5);
        b6=findViewById(R.id.button6);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figura="Cubo";
                enviar(figura);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figura="Piramide";
                enviar(figura);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figura="Prisma";
                enviar(figura);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figura="Cono";
                enviar(figura);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figura="Cilindro";
                enviar(figura);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                figura="Esfera";
                enviar(figura);
            }
        });



    }

    public void enviar(String figura){
        itn = new Intent(MainActivity.this, VistaActivity.class);
        bdl = new Bundle();
        bdl.putString("figura", figura);
        itn.putExtras(bdl);
        startActivity(itn);
    }
}