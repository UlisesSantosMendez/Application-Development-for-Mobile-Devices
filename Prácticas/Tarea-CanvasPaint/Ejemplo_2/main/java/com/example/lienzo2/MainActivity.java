package com.example.lienzo2;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.view.View;
import android.view.View.*;
import android.os.*;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lienzo l = new Lienzo(this);
        setContentView(l);
    }
}