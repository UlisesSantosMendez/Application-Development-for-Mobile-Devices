package com.example.dibujaactivity;

import android.app.*;
import android.content.*;
import android.os.*;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        SpecialView myView = new SpecialView(this);
        setContentView(myView);
    }
    class SpecialView extends View{
        float x = 50;
        float y = 50;
        String accion = "accion";
        Path path =new Path();
        public SpecialView(Context context){
            super(context);
        }
        protected void onDraw(Canvas canvas){
            canvas.drawColor(Color.rgb(140, 210, 180));
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(8);
            paint.setColor(Color.DKGRAY);
            if(accion=="down"){
                path.moveTo(x, y);
            }
            if(accion=="move"){
                path.lineTo(x, y);
            }
            canvas.drawPath(path, paint);
        }
        public boolean onTouchEvent(MotionEvent evento){
            x = evento.getX();
            y = evento.getY();
            if(evento.getAction() == MotionEvent.ACTION_DOWN){
                accion = "down";
            }
            if(evento.getAction() == MotionEvent.ACTION_MOVE){
                accion = "move";
            }
            invalidate();
            return true;
        }
    }
}