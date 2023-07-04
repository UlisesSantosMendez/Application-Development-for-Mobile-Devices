package com.example.examen2;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
public class FragmentA extends Fragment{
    public final static int OK = 0;
    public final static int CANCEL = 1;
    TextView tv1f;
    int filasa=0,columnasa=0,filasb=0,columnasb=0,filas,columnas;
    boolean llenoa,llenob;

    public FragmentA() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filasa = getArguments().getInt("AF");
            columnasa = getArguments().getInt("AC");
            filasb = getArguments().getInt("BF");
            columnasb = getArguments().getInt("BC");
            llenoa=getArguments().getBoolean("LA");
            llenob=getArguments().getBoolean("LB");
        }
    }


    public interface FragmentoListener {
        public void digitado(int resultado, int[][] matriz, int dest);
    }

    FragmentoListener dataPasser;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentoListener){
            dataPasser=(FragmentoListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {

        View v = li.inflate(R.layout.a_fragment, vg, false);
        TableLayout tabla = (TableLayout) v.findViewById(R.id.Tabla2);
        tabla.removeAllViews();

        tv1f=(TextView) v.findViewById(R.id.tv1f);


        if(llenoa==false){
            filas=filasa;
            columnas=columnasa;
            tv1f.setText("Matriz A");
        }else{
            filas=filasb;
            columnas=columnasb;
            tv1f.setText("Matriz B");
        }

        for (int j=1;j<=filas;j++) {
            TableRow tr = new TableRow(getActivity());

            for (int i = 1; i <= columnas; i++) {
                EditText et = new EditText(getActivity());
                et.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                et.setTextColor(getResources().getColor(R.color.white));
                String id= String.valueOf(j)+String.valueOf(i);
                et.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
                et.setTag(id);
                tr.addView(et);
            }

            tabla.addView(tr);
        }

        ((Button) v.findViewById(R.id.xbnA)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonDigitado(v);
            }
        });

        return v;
    }

    public void botonDigitado(View v) {
        int[][] matriz;
        EditText et;

        if(llenoa==false)
            matriz = new int[filasa][columnasa];
        else
            matriz = new int[filasb][columnasb];

        for (int i = 1; i <= filas; i++) {
            for (int j = 1; j <= columnas; j++) {
                et=(EditText)getActivity().findViewById(R.id.Tabla2).findViewWithTag(String.valueOf(i)+String.valueOf(j));
                et.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
                matriz[i - 1][j - 1] = Integer.parseInt(String.valueOf(et.getText()));
            }

            if(llenoa==false){
                dataPasser.digitado(OK,matriz,0);
                getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
            }
            else{
                dataPasser.digitado(OK,matriz,1);
                getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
            }


        }
    }
}

