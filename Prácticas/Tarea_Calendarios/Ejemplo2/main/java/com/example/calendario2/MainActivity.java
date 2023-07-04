package com.example.calendario2;

import android.app.*;
import android.os.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.CalendarView.*;

public class MainActivity extends AppCompatActivity {
    CalendarView cv;
    TextView tv;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        cv = (CalendarView) findViewById(R.id.calendarView);
        tv = (TextView) findViewById(R.id.date_display);
        tv.setText("Fecha: ");
        cv.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                i1=i1+1;
                tv.setText("Fecha: " + i2 + " / " + i1 + " / " + i);
                Toast.makeText(getApplicationContext(), "Selección:\n" + "Day = " + i2 + "\n"
                        + "Mes = " + i1 + "\n" + "Año = " + i, Toast.LENGTH_LONG).show();
            }
        });
    }
}