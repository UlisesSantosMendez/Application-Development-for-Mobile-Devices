package com.example.lienzo;
import android.content.*;
import android.graphics.*;
import android.text.Layout;
import android.view.View;
import android.app.*;
import android.os.*;
import java.lang.Object.*;
import android.widget.*;

public class Lienzo extends View {
    Paint p;
    int x, y;
    String s = "CENTER";
    public Lienzo(Context c){
        super(c);
    }
    protected void onDraw(Canvas c){
        super.onDraw(c); // Canvas pinta atributos
        p = new Paint(); // Paint asigna atributos
        x = c.getWidth(); // También: getMeasuredWidth() o getRight(), x=480
        y = c.getHeight(); // También: getMeasuredHeight() o getBottom(), y=762
        p.setColor(Color.WHITE); // Fondo blanco
        c.drawPaint(p);
        p.setColor(Color.BLACK); // Texto negro
        p.setTextSize(20);
        c.drawText("x0=" + x/2 + ", y0=" + y/2, x/2 + 20, y/2-20, p);
        p.setColor(Color.rgb(0, 0, 255)); // Ejes azules
        c.drawLine(x/2, 0, x/2, y, p);//EJE Y COMPLETO
        c.drawLine(0, y/2, x, y/2, p); //EJE X COMPLETO
        p.setTextAlign(Paint.Align.RIGHT);
        p.setTypeface(Typeface.MONOSPACE);
        c.drawText("Eje X", x-5, y/2-10, p);
        p.setTextAlign(Paint.Align.RIGHT);
        p.setTypeface(Typeface.MONOSPACE);
        c.drawText("Eje Y", x/2+30, 20, p);
        p.setColor(Color.argb(37, 200, 100, 100));
        c.drawCircle(x/2-120, y/2+120, 100, p);
        p.setColor(Color.rgb(12,34,22));
        c.drawOval(x/2+120,y/4,y/2-120,y/4 + 120,p);
        p.setColor(Color.rgb(5,24,60));
        c.drawRect(x/2 -100,y/4,90,y/4+120,p);
        p.setColor(Color.rgb(252,245,95));
        c.drawRoundRect(x/2 -100, 3*y/4,90,3*y/4+80,10,10,p);
        p.setColor(Color.rgb(0,255,255));
        c.drawArc(x/2 + 120,3*y/4,y/2 - 120,3*y/4 + 120,0,100,true,p);
    }
}
