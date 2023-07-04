package com.example.examen2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Resultado extends AppCompatActivity {
    Button bt1,bt2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        bt1=(Button)findViewById(R.id.saxbnt1);
        bt2=(Button)findViewById(R.id.saxbnt2);

        ArrayList<Matriz> mat = (ArrayList<Matriz>) getIntent().getSerializableExtra("matrices");

        int filasa=mat.get(0).getX();
        int columnasa=mat.get(0).getY();

        int matriz[][]= new int [filasa][columnasa];
        matriz=mat.get(0).getElementos();

        Bundle args = new Bundle();
        args.putSerializable("matrices",mat);

        androidx.fragment.app.FragmentManager fm = getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction ft = fm.beginTransaction();

        FragmentB mf = new FragmentB();
        mf.setArguments(args);
        ft.replace(R.id.saxfl,mf, "editor");
        ft.commit();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(Resultado.this, MainActivity.class);
                startActivity(itn);
            }
        });
    }
}
