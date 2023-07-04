package com.example.cubo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

import java.util.List;
import static android.content.Context.SENSOR_SERVICE;

public class Cubo extends View implements SensorEventListener,Runnable {
    Paint p;
    Path r;
    int centerX=0, centerY=0, maxX=0, maxY=0, minMaxXY=0;
    float x,y;
    Obj obj = new Obj();
    SensorManager sm;
    Sensor s;
    WindowManager manager;

    public Cubo(Context c, WindowManager m){
        super(c);
        manager = m;
        x=100;
        y=100;
        centerX = maxX/2;
        centerY = maxY/2;
        sm=(SensorManager) c.getSystemService(SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        //TYPE_ROTATION_VECTOR
    }

    void line(Canvas g, int i, int j){
        Point2D pa = obj.vScr[i],-- q = obj.vScr[j];
        p.setColor(Color.BLUE);77
        g.drawLine(iX(pa.x), iY(pa.y), iX(q.x), iY(q.y), p);
    }

    public void onDraw(Canvas c) {
        super.onDraw(c);
        p = new Paint();
        p.setColor(Color.WHITE);
        //setOnTouchListener(this);
        sm.registerListener(this, s, SensorManager.SENSOR_STATUS_ACCURACY_LOW);
        c.drawPaint(p);
        Rect rectangulo = new Rect(0,0, getWidth(), getHeight());
        p.setColor(Color.argb(50, 50, 50, 50));
        c.drawRect(rectangulo, p);
        maxX=getWidth()-1;
        maxY=getHeight()-1;
        minMaxXY=Math.min(maxX,maxY);
        centerX = maxX/2;
        centerY = maxY/2;
        obj.d = obj.rho*minMaxXY/obj.objSize;
        obj.eyeAndScreen();
        line(c, 0, 1); line(c, 1, 2); line(c, 2, 3); line(c, 3, 0); // aristas horizontales inferiores
        line(c, 4, 5); line(c, 5, 6); line(c, 6, 7); line(c, 7, 4); // aristas horizontales superiores
        line(c, 0, 4); line(c, 1, 5); line(c, 2, 6); line(c, 3, 7); // aristas verticales
    }

    int iX(float x){
        return centerX + (int)x;
    }
    int iY(float y){
        return centerY - (int)y;
    }

   @Override
    public void onSensorChanged(SensorEvent event) {
        float[] rotation = new float [9];
        SensorManager.getRotationMatrixFromVector(rotation, event.values);
       final int worldAxisForDeviceAxisX;
       final int worldAxisForDeviceAxisY;

       // Remap the axes as if the device screen was the instrument panel,
       // and adjust the rotation matrix for the device orientation.
       switch (manager.getDefaultDisplay().getRotation()) {
           case Surface.ROTATION_0:
           default:
               worldAxisForDeviceAxisX = SensorManager.AXIS_X;
               worldAxisForDeviceAxisY = SensorManager.AXIS_Z;
               break;
           case Surface.ROTATION_90:
               worldAxisForDeviceAxisX = SensorManager.AXIS_Z;
               worldAxisForDeviceAxisY = SensorManager.AXIS_MINUS_X;
               break;
           case Surface.ROTATION_180:
               worldAxisForDeviceAxisX = SensorManager.AXIS_MINUS_X;
               worldAxisForDeviceAxisY = SensorManager.AXIS_MINUS_Z;
               break;
           case Surface.ROTATION_270:
               worldAxisForDeviceAxisX = SensorManager.AXIS_MINUS_Z;
               worldAxisForDeviceAxisY = SensorManager.AXIS_X;
               break;
       }

       float[] adjustedRotationMatrix = new float[9];
       SensorManager.remapCoordinateSystem(rotation, worldAxisForDeviceAxisX,
               worldAxisForDeviceAxisY, adjustedRotationMatrix);

       // Transform rotation matrix into azimuth/pitch/roll
       float[] orientation = new float[3];
       SensorManager.getOrientation(adjustedRotationMatrix, orientation);

       x = getWidth()*(orientation[0]+1)/2;
       x = getHeight()*(1-((orientation[1]+1)/2));

        obj.theta   = (float) getWidth() / x;
        obj.phi     = (float) getHeight() / y;
        obj.rho     = (obj.phi/obj.theta) * getHeight();
        centerX     = (int) x;
        centerY     = (int) y;
        this.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void run() {

    }
}
