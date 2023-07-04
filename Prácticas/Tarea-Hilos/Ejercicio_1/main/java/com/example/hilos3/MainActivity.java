package com.example.hilos3;

import android.app.*;

import android.os.*;
import android.widget.TextView;

public class MainActivity extends Activity {
    Handler h = new Control();
    TextView jtv1;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jtv1=(TextView) findViewById(R.id.xtv1);
        Hilo h1 = new Hilo(10, 2000); // 1seg = 1000ms
        Hilo h2 = new Hilo( 5, 2000);
        h1.setPriority(10);
        h2.setPriority(1);
        h1.setName("HILO 1");
        h2.setName("HILO 2");
        h1.start();
        h2.start();
    }
    class Hilo extends Thread{
        int n, t;
        Message m;
        Bundle b;

        Hilo(int n, int t){
            this.n = n;
            this.t = t;
        }
        public void run(){
            for(int i=0; i<n; i++){
                try{
                    Thread.sleep(t);
                }catch(InterruptedException ie){}
                m = h.obtainMessage();
                b = new Bundle();
                b.putInt("cuenta", i); //cuenta=i
                b.putString("hilo_name", currentThread().toString());//hilo=currentThread()
                b.putString("hilo_solo", currentThread().getName().toString());//hilo=currentThread() con nombre
                b.putLong("hilo_id", currentThread().getId());//hilo=currentThread() con nombre
                m.setData(b);
                h.sendMessage(m);
            }
        }
    }
    class Control extends Handler{
        public void handleMessage(Message m){
            int n;
            String s,a;
            Long b;
            n = m.getData().getInt("cuenta"); //cuenta=i
            s = m.getData().getString("hilo_name"); //hilo=currentThread()
            a = m.getData().getString("hilo_solo");
            b = m.getData().getLong("hilo_id");
            jtv1.append("\n" + n + " " + s + " " + a + " " + "ID:" + b);
        }
    }
}