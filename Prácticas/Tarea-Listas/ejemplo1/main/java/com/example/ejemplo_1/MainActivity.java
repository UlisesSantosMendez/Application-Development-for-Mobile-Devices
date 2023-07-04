package com.example.ejemplo_1;

import android.app.*;
import android.os.*;
import android.widget.*;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        //setContentView(R.layout.activity_main);
        ListAdapter la = crearAdapter();
        setListAdapter(la);
    }
    protected ListAdapter crearAdapter(){
        String[] s = new String[]{
                "Elemento1",
                "Elemento2",
                "Elemento3"
        };
        ListAdapter la2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,s);
        return la2;
    }
}