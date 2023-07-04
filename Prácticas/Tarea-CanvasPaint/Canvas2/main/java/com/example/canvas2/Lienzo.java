package com.example.canvas2;

import android.content.*;
import android.graphics.*;
import android.view.View;
import android.widget.Toast;

public class Lienzo extends View{
    Paint p;
    Path r;
    float asin,acos,persin,percos;
    int x, y, x0, y0;

    public void setAsin(float asin) {
        this.asin = asin;
    }

    public void setAcos(float acos) {
        this.acos = acos;
    }

    public void setPersin(float persin) {
        this.persin = persin;
    }

    public void setPercos(float percos) {
        this.percos = percos;
    }

    public Lienzo(Context c){
        super(c);
    }
    protected void onDraw(Canvas c){
        super.onDraw(c);
        p = new Paint();
        r = new Path();
        x = c.getWidth();
// Canvas pinta atributos
// Paint asigna atributos
        x0=x/2; // También: getMeasuredWidth(), o getRight(), x=480
        y = c.getHeight(); y0=y/2; // También: getMeasuredHeight(), o getBottom(), y=762
        p.setColor(Color.WHITE); // Fondo blanco
        c.drawPaint(p);
        p.setColor(Color.BLACK); // Texto negro
        p.setTextSize(20);
        c.drawText("0,0", x0 + 5, y0 + 20, p);
        c.drawText(String.valueOf(asin), x0 + 5, (float) y0+(asin*(-80f)), p);
        c.drawText(String.valueOf(acos), x0 + 5,(float) y0+(acos*(-80f)), p);
        p.setColor(Color.rgb(0, 0, 255)); // Ejes azules
        c.drawLine(x0, 0, x0, y, p);
        c.drawLine(0, y0, x, y0, p);
        p.setColor(Color.BLUE); c.drawText(String.valueOf(asin)+"sen("+String.valueOf(persin)+"x)", 20, 20, p);
        p.setColor(Color.RED); c.drawText(String.valueOf(acos)+"cos("+String.valueOf(percos)+"x)", 20, 50, p);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2);
        p.setAntiAlias(true);
        r = new Path();
        x = getMeasuredWidth();
        r.moveTo(0, 0);
        for(int i=1; i<x; i++) {
            p.setColor(Color.BLUE);
            r.lineTo(i, (float) (asin * Math.sin((i / 20f)*persin) * (-80f)));
            /*p.setColor(Color.BLACK); // Texto negro
            p.setTextSize(20);
            c.drawText("0,0", x0 + 5, y0 + 20, p);*/
        }
        r.offset(-10, y0);
        c.drawPath(r, p);
        r = new Path();
        r.moveTo(0, 0);
        for(int i=1; i<x; i++){
            p.setColor(Color.RED);
            r.lineTo(i, (float) (acos * Math.cos((i / 20f)*percos) * (-80f)));
            if(Math.abs(Math.cos((i / 20f)*percos)) == y0){
                p.setColor(Color.BLACK); // Texto negro
                p.setTextSize(20);
                c.drawText(i+",0", x0 + 5, y0 + 20, p);
            }
        }
        r.offset(-10, y0);
        c.drawPath(r, p);
    }
}
