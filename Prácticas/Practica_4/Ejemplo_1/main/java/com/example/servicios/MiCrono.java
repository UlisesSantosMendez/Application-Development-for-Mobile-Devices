package com.example.servicios;

import java.lang.ref.WeakReference;
import java.util.*;
import android.app.*;
import android.os.*;
import android.content.Intent;
import android.app.Service;
public class MiCrono extends Service{
    private Timer t = new Timer();
    private static final long INTERVALO_ACTUALIZACION = 10; // En milisegundos
    public static MainActivity UPDATE_LISTENER;
    private double n=0;
    private static Handler h;
    public static void setUpdateListener(MainActivity sta) {

        UPDATE_LISTENER = sta;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        iniciarCrono();
        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                UPDATE_LISTENER.refreshCrono(n);

            }
        };
        try {
            pauseCrono();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onDestroy() {
        pararCrono();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {

        return null;
    }
    private void iniciarCrono() {
        t.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                n += 0.01;
                h.sendEmptyMessage(0);
            }
        }, 0, INTERVALO_ACTUALIZACION);
    }
    private void pararCrono() {
        if (t != null)
            t.cancel();
    }
    private void pauseCrono() throws InterruptedException {
        if (t != null)
            t.wait();
    }
    private void continueCrono() {
        if (t != null)
            t.cancel();
    }
}
