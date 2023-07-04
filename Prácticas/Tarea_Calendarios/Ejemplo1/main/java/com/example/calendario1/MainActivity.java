package com.example.calendario1;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.widget.CalendarView.*;

import androidx.annotation.NonNull;

public class MainActivity extends Activity {
    CalendarView cv;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        cv = (CalendarView) findViewById(R.id.xcv);
        cv.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView cv, int y, int m, int d) {
                Toast.makeText(getBaseContext(), "Fecha seleccionada:\n\n" + d + " / " + (m+1)
                        + " / " + y, Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}