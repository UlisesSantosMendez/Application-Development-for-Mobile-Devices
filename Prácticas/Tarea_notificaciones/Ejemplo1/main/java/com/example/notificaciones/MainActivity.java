package com.example.notificaciones;

import android.app.*;
import android.os.*;
import android.content.*;
import android.graphics.drawable.BitmapDrawable;
//import android.support.v4.app.NotificationCompat;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.graphics.BitmapFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentTabHost;

public class MainActivity extends AppCompatActivity {
    int t=200, i=0;
    boolean c=true;
    TextView jtv;
    Button jbnN;
    private static final int NOTIF_ALERTA_ID = 1;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jtv = (TextView) findViewById(R.id.xtv);
        jbnN = (Button) findViewById(R.id.xbnN);

    }

    public void lanzarNotificacion(View v){
        Intent in = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, in, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder ncb = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle("Alerta de Notificación")
                .setContentText("Uso de la notificación." + "i=" + ++i)
                .setContentInfo("Un valor")
                .setTicker("Mensaje de Alerta!")
                .setContentIntent(pi);

        NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0, ncb.build());
        jtv.setText("Cuenta: i=" + i);


    }
}