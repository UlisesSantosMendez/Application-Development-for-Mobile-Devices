package com.example.examen2;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;

public class FragmentB extends Fragment {
    int filasa,filasb,columnasa,columnasb;
    String nombre;
    int [][] matriza;
    int [][] matrizb;
    int [][] resultado;

    public FragmentB() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ArrayList<Matriz> mat = (ArrayList<Matriz>) getArguments().getSerializable("matrices");
            Matriz a = mat.get(0);
            Matriz b = mat.get(1);
            filasa=a.getX();
            columnasa=a.getY();
            matriza=a.getElementos();
            filasb=b.getX();
            columnasb=b.getY();
            matrizb=b.getElementos();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.b_fragment, vg, false);
        TableLayout tabla = (TableLayout) v.findViewById(R.id.saTabla2);
        tabla.removeAllViews();

        resultado=new int[filasa][columnasb];

        for (int a = 0; a < columnasb; a++) {
            // Dentro recorremos las filas de la primera (A)
            for (int i = 0; i < filasa; i++) {
                int suma = 0;
                // Y cada columna de la primera (A)
                for (int j = 0; j < columnasa; j++) {
                    // Multiplicamos y sumamos resultado
                    suma += matriza[i][j] * matrizb[j][a];
                }
                // Lo acomodamos dentro del producto
                resultado[i][a] = suma;
            }
        }

        // Recorrer producto
        System.out.print("Imprimiendo producto\n");
        for (int i = 0; i < filasa; i++) {
            TableRow tr = new TableRow(getActivity());
            for (int j = 0; j < columnasb; j++) {
                TextView tv = new TextView(getActivity());
                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tv.setText(String.valueOf(resultado[i][j]));
                tv.setTextSize(17);
                tv.setTextColor(getResources().getColor(R.color.white));
                tr.addView(tv);
            }
            tabla.addView(tr);
        }
        return v;
    }
}
