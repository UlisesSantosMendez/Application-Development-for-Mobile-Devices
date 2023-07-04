package com.example.fragmentos2;

import android.os.*;
import android.view.*;
import androidx.fragment.app.Fragment;
import android.widget.TextView;

public class FragmentDetalle extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }
    public void mostrarDetalle(String texto) {
        TextView tv = (TextView)getView().findViewById(R.id.xtvDetalle);
        tv.setText(texto);
    }
}
