package com.example.cronos;

import android.app.*;
import android.view.*;
import android.widget.*;
import android.os.*;

public class MainActivity extends Activity{
    private TextView jtv;
    boolean iniciado = false;
    boolean terminado = true;
    float tiempo=0;
    private Button bt1,bt2;
    Handler h= new Control();
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        bt1 = (Button)findViewById(R.id.xbnI);
        bt2 = (Button)findViewById(R.id.xbnP);
        jtv = (TextView)findViewById(R.id.xtvT);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iniciado == false){
                    bt1.setText("Pausar");
                    bt2.setText("Parar");
                    iniciado=true;
                    terminado=false;
                    Hilo h1 = new Hilo(100); // 1seg = 1000ms
                    h1.setName("HILO 1");
                    h1.start();
                }
                else{
                    bt1.setText("Reanudar");
                    bt2.setText("Reiniciar");
                    iniciado=false;
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciado=false;
                terminado=true;
                bt1.setText("Empezar");
                bt2.setText("Reiniciar");
                tiempo=0;
                jtv.setText("0.00");
            }
        });

    }
    class Hilo extends Thread{
        int t;
        Message m;
        Bundle b;
        Hilo(int t){
            this.t = t;
        }
        public void run(){
            while(iniciado == true){
                try{
                    Thread.sleep(t);
                    tiempo+=0.1;
                }catch(InterruptedException ie){}
                m = h.obtainMessage();
                b = new Bundle();
                b.putFloat("tiempo",tiempo);
                m.setData(b);
                h.sendMessage(m);
            }
        }
    }
    class Control extends Handler {
        public void handleMessage(Message m) {
            float n;
            if (terminado == false) {
                n = m.getData().getFloat("tiempo"); //cuenta=i
                String str = String.format("%.02f", n);
                n = Float.valueOf(str);
                jtv.setText(String.valueOf(n));
            } else
                jtv.setText("0.00");
        }
    }
}