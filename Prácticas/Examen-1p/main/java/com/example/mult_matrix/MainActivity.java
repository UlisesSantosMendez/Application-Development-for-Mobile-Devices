package com.example.mult_matrix;
/*
///////////////////////////////////////////////////////////////////////////////
Objetivo: Diseñar una aplicación movil que calcule y muestre el               |
producto de dos matrices de tamaño no mayor a tres.                           |
DESARROLLO:                                                                   |
1. Solicitar el tamaño de cada una de las matrices.                           |
2. Si los tamaños de las matrices no son compatibles para el producto,        |
   se terminará la aplicación. (2 puntos)                                     |
3. Si los tamaños de las matrices son compatibles, solicitar los              |
   valores de cada una de las dos matrices.                                   |
*  Utilizar un fragmento para mostrar y capturar los valores                  |
   de la primera matriz, según su tamaño. (2 puntos)                          |
*  Utilizar un fragmento para mostrar y capturar los valores                  |
   de la segunda matriz, según su tamaño. (2 puntos)                          |
4. Una vez terminada la captura de los datos, con un intento                  |
   invocar a otra plantilla que posea un fragmento que incluya                |
   el resultado correcto. (2 puntos)                                          |
   a) Al final, incluir dos botones, un botón que termine la aplicación       |
      y otro que solicite otra vez los tamaños de las matrices. (2 puntos)    |
///////////////////////////////////////////////////////////////////////////////
 */
import android.app.*;
import android.view.View.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText jeta1,jeta2,jetb1,jetb2;
    Button jbn1,jbnin,jbnma,jbnmb;
    Integer a1,a2,b1,b2;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jeta1=(EditText)findViewById(R.id.xeta1);
        jeta2=(EditText)findViewById(R.id.xeta2);
        jetb1=(EditText)findViewById(R.id.xetb1);
        jetb2=(EditText)findViewById(R.id.xetb2);
        jbn1=(Button)findViewById(R.id.xbtn1); //BOTON PARA CALCULAR
        jbnin=(Button)findViewById(R.id.xbtnin); //BOTON DE INGRESAR
        jbnma=(Button)findViewById(R.id.xbtnA);// BOTON MATRIX A
        jbnmb=(Button)findViewById(R.id.xbtnB); //BOTON MATRIX B
        jbnin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                a1=Integer.parseInt(jeta1.getText().toString());
                a2=Integer.parseInt(jeta2.getText().toString());
                b1=Integer.parseInt(jetb1.getText().toString());
                b2=Integer.parseInt(jetb2.getText().toString());
                FragmentManager fragmentManager = getSupportFragmentManager();
                if(a1<= 3 && a2<=3 && b1 <= 3 && b2<=3){
                    if(a2 == b1) {
                        Toast.makeText(MainActivity.this, "Tamaños compatibles", Toast.LENGTH_LONG).show();
                        jbnma.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                fragmentManager.beginTransaction()
                                        .replace(R.id.fragmentContainerView,fragment_A.class, null)
                                        .setReorderingAllowed(true)
                                        .addToBackStack("A")
                                        .commit();

                            }
                        });
                        jbnmb.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                fragmentManager.beginTransaction()
                                        .replace(R.id.fragmentContainerView,fragment_B.class, null)
                                        .setReorderingAllowed(true)
                                        .addToBackStack("B")
                                        .commit();

                            }
                        });
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Tamaños  no compatibles", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Excede tamaño de filas o columnas", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
}