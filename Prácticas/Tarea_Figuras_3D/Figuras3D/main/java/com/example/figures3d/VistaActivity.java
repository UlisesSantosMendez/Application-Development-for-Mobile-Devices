package com.example.figures3d;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Objects;

public class VistaActivity extends AppCompatActivity {
    Bundle bdl;
    String figura;
    Button btregreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista);
        bdl = getIntent().getExtras();
        figura=bdl.getString("figura");
        System.out.println("Figura: "+figura);
        btregreso=(Button)findViewById(R.id.btnret);
        VistaActivity.Perspective perspective = new VistaActivity.Perspective(this);
        ConstraintLayout constraintLayout = findViewById(R.id.cnstrntLyt);
        constraintLayout.addView(perspective);
        btregreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(VistaActivity.this, MainActivity.class);
                startActivity(itn);
            }
        });
    }

    private class Perspective extends View {

        private class Point2D{
            private float x, y;



            Point2D(float x, float y){ this.x = x; this.y = y; }

            public float getX() {
                return x;
            }

            public void setX(float x) {
                this.x = x;
            }

            public float getY() {
                return y;
            }

            public void setY(float y) {
                this.y = y;
            }
        }

        private class Point3D{
            private float x, y, z;


            Point3D(double x, double y, double z){
                this.x = (float) x;
                this.y = (float) y;
                this.z = (float) z;
            }

            public float getX() {
                return x;
            }

            public void setX(float x) {
                this.x = x;
            }

            public float getY() {
                return y;
            }

            public void setY(float y) {
                this.y = y;
            }

            public float getZ() {
                return z;
            }

            public void setZ(float z) {
                this.z = z;
            }
        }

        private class MyFigure{

            float rho, theta=0.3F, phi=1.3F, d, figureSize, v11, v12, v13, v21, v22, v23, v32, v33, v43; //elementos de la matriz V
            VistaActivity.Perspective.Point3D[] figureCoordinates; // coordenadas universales
            VistaActivity.Perspective.Point2D[] screenCoordenates; // coordenadas de la pantalla

            MyFigure(){ // CAMBIAR LAS COORDENADAS X,Y,Z CON 0,1 PARA CONSTRUIR PRISMA, CILINDRO, PIRAMIDE, CONO Y ESFERA.
                if(figura.equals("Cubo")){
                    figureCoordinates = new VistaActivity.Perspective.Point3D[8];
                    screenCoordenates = new VistaActivity.Perspective.Point2D[8];

                    figureCoordinates[0] = new Point3D(1, -1, -1); // desde la base
                    figureCoordinates[1] = new Point3D(1, 1, -1);
                    figureCoordinates[2] = new Point3D(-1, 1, -1);
                    figureCoordinates[3] = new Point3D(-1, -1, -1);
                    figureCoordinates[4] = new Point3D(1, -1, 1);
                    figureCoordinates[5] = new Point3D(1, 1, 1);
                    figureCoordinates[6] = new Point3D(-1, 1, 1);
                    figureCoordinates[7] = new Point3D(-1, -1, 1);
                }
                else
                if(figura.equals("Piramide")){
                    figureCoordinates = new VistaActivity.Perspective.Point3D[8];
                    screenCoordenates = new VistaActivity.Perspective.Point2D[8];
                    figureCoordinates[0] = new Point3D(1, -1, -1); // base
                    figureCoordinates[1] = new Point3D(1, 1, -1);
                    figureCoordinates[2] = new Point3D(-1, 1, -1);
                    figureCoordinates[3] = new Point3D(-1, -1, -1);
                    figureCoordinates[4] = new Point3D(0, 0, 1); // top
                    figureCoordinates[5] = new Point3D(0, 0, 1);
                    figureCoordinates[6] = new Point3D(0, 0, 1);
                    figureCoordinates[7] = new Point3D(0, 0, 1);
                }
                else
                if(figura.equals("Prisma")){
                    figureCoordinates = new VistaActivity.Perspective.Point3D[10];
                    screenCoordenates = new VistaActivity.Perspective.Point2D[10];

                    figureCoordinates[0]	= new Point3D(-0.5, -1, 0); // desde la base
                    figureCoordinates[1]	= new Point3D(0.5, -1, 0);
                    figureCoordinates[2]	= new Point3D(1, 0, 0);
                    figureCoordinates[3]	= new Point3D(0, 1, 0);
                    figureCoordinates[4]	= new Point3D(-1, 0, 0);
                    figureCoordinates[5]	= new Point3D(-0.5, -1, 1);

                    figureCoordinates[6]	= new Point3D(0.5, -1, 1);
                    figureCoordinates[7]	= new Point3D(1,0, 1);
                    figureCoordinates[8]	= new Point3D(0, 1, 1);
                    figureCoordinates[9]	= new Point3D(-1, 0, 1);
                }
                else
                if(figura.equals("Cono")){
                    int numeroLados = 30;
                    figureCoordinates = new VistaActivity.Perspective.Point3D[numeroLados+1];
                    screenCoordenates = new VistaActivity.Perspective.Point2D[numeroLados+1];
                    int signos[] = new int[2];
                    double anguloSeparacion, posicionX, posicionY, aux, auxRadianes, x;
                    anguloSeparacion = 360/numeroLados;
                    aux = anguloSeparacion;

                    for(int i = 0; i < numeroLados; i++){
                        auxRadianes = verificarCuadrante(aux);
                        signos = signosCuadrante(aux);
                        posicionX = Math.cos(auxRadianes)*(signos[0]);
                        posicionY = Math.sin(auxRadianes)*(signos[1]);
                        figureCoordinates[i] = new Point3D(posicionX, posicionY, -1);
                        System.out.print("teta: " + aux + "\nposicionX: " + posicionX + "\nposicionY: " + posicionY + "\n\n");
                        aux = aux + anguloSeparacion;
                    }
                    figureCoordinates[numeroLados] = new Point3D(0, 0, 1);
                }
                else
                if(figura.equals("Cilindro")){
                    int numeroLados = 30;
                    figureCoordinates = new VistaActivity.Perspective.Point3D[numeroLados*2];
                    screenCoordenates = new VistaActivity.Perspective.Point2D[numeroLados*2];
                    int signos[] = new int[2];
                    double anguloSeparacion, posicionX, posicionY, aux, auxRadianes, x;
                    anguloSeparacion = 360/numeroLados;
                    aux = anguloSeparacion;


                    //llenar las posiciones de los puntos de la base
                    for(int i = 0; i < numeroLados; i++){
                        auxRadianes = verificarCuadrante(aux);
                        signos = signosCuadrante(aux);
                        posicionX = Math.cos(auxRadianes)*(signos[0]);
                        posicionY = Math.sin(auxRadianes)*(signos[1]);
                        figureCoordinates[i] = new Point3D(posicionX, posicionY, -1);
                        aux = aux + anguloSeparacion;
                    }

                    for(int i = numeroLados; i < 2*numeroLados; i++){
                        auxRadianes = verificarCuadrante(aux);
                        signos = signosCuadrante(aux);
                        posicionX = Math.cos(auxRadianes)*(signos[0]);
                        posicionY = Math.sin(auxRadianes)*(signos[1]);
                        figureCoordinates[i] = new Point3D(posicionX, posicionY, 1);
                        aux = aux + anguloSeparacion;
                    }

                }
                else
                if(figura.equals("Esfera")){
                    int numeroLados = 15;
                    figureCoordinates = new VistaActivity.Perspective.Point3D[numeroLados*9];
                    screenCoordenates = new VistaActivity.Perspective.Point2D[numeroLados*9];
                    int signos[] = new int[2];
                    double anguloSeparacion, posicionX, posicionY, aux, auxRadianes,x, altura=-1,radio=1;
                    anguloSeparacion = 360/numeroLados;
                    aux = anguloSeparacion;

                    for(int j=0; j<9; j++){
                        if(j == 0 || j == 8)
                            radio=7;
                        if(j == 1 || j == 7)
                            radio=2;
                        if(j == 2 || j == 6)
                            radio=1.4;
                        if(j == 3 || j == 5)
                            radio=1.1;
                        if(j == 4)
                            radio=1;
                        for(int i = j*numeroLados; i < (j+1)*numeroLados; i++){
                            auxRadianes = verificarCuadrante(aux);
                            signos = signosCuadrante(aux);
                            posicionX = Math.cos(auxRadianes)*(signos[0]);
                            posicionY = Math.sin(auxRadianes)*(signos[1]);
                            figureCoordinates[i] = new Point3D(posicionX/radio, posicionY/radio, altura);
                            System.out.print("teta: " + aux + "\nposicionX: " + posicionX + "\nposicionY: " + posicionY + "\n\n");
                            aux = aux + anguloSeparacion;
                        }
                        altura+=0.22;
                    }
                }



                figureSize = (float) Math.sqrt(12F); // = sqrt(2*2 + 2*2 + 2*2) es la distancia entre dos vertices opuestos
                rho = 5*figureSize; // para cambiar la perspectiva
            }

            void initPerspective(){
                float costh = (float)Math.cos(theta),sinth=(float)Math.sin(theta),
                        cosph=(float)Math.cos(phi), sinph=(float)Math.sin(phi);
                v11 = -sinth;
                v12 = -cosph*costh;
                v13 = sinph*costh;
                v21 = costh;
                v22 = -cosph*sinth;
                v23 = sinph*sinth;
                v32 = sinph;
                v33 = cosph;
                v43 = -rho;
            }
            void eyeAndScreen(){
                initPerspective();
                VistaActivity.Perspective.Point3D p;
                float x, y, z;
                int n=0;
                if(figura.equals("Cubo") || figura.equals("Piramide")){
                    n=8;
                }
                else
                if(figura.equals("Prisma")){
                    n=10;
                }
                else
                if(figura.equals("Cono")){
                    n=31;
                }
                else
                if(figura.equals("Cilindro")){
                    n=60;
                }
                else
                if(figura.equals("Esfera")){
                    n=15*9;
                }

                for(int i=0; i<n; i++){
                    p = figureCoordinates[i];
                    x = v11 * p.x + v21 * p.y;
                    y = v12 * p.x + v22 * p.y + v32 * p.z;
                    z = v13 * p.x + v23 * p.y + v33 * p.z + v43;
                    screenCoordenates[i] = new VistaActivity.Perspective.Point2D(-d * x / z, -d * y / z);
                }

            }

            public int[] signosCuadrante(double angulo){
                int signos[] = new int[2];

                signos[0] = 1; //coseno
                signos[1] = 1; //seno
                //PRIMER CUADRANTE
                if(angulo < 90 && angulo > 0){
                    signos[0] = 1;
                    signos[1] = 1;
                }
                //SEGUNDO CUADRANTE
                else if(angulo < 180 && angulo > 90){
                    signos[0] = -1;
                }
                //TERCER CUADRANTE
                else if(angulo < 270 && angulo > 180){
                    signos[0] = -1;
                    signos[1] = -1;
                }
                //CUARTO CUADRANTE
                else if(angulo < 360 && angulo > 270){
                    signos[1] = -1;
                }else if(angulo == 180){
                    signos[0] = -1;
                }else if (angulo == 270){
                    signos[1] = -1;
                }


                return signos;
            }

            public double verificarCuadrante(double angulo){
                double auxRadianes, aux = angulo;

                //PRIMER CUADRANTE
                if(angulo < 90 && angulo > 0){
                    aux = angulo;
                }
                //SEGUNDO CUADRANTE
                else if(angulo < 180 && angulo > 90){
                    aux = 180 - angulo;
                }
                //TERCER CUADRANTE
                else if(angulo < 270 && angulo > 180){
                    aux = angulo - 180;
                }
                //CUARTO CUADRANTE
                else if(angulo < 360 && angulo > 270){
                    aux = 360 - angulo;
                }
                else if(angulo == 270){
                    aux = 90;
                }
                else if(angulo == 360){
                    aux = 0;
                }
                else if(angulo == 180){
                    aux = 0;
                }
                else if(angulo == 0){
                    aux = 0;
                }

                auxRadianes = Math.toRadians(aux);
                return auxRadianes;
            }
        }

        private Path path;
        private Paint paint;
        private VistaActivity.Perspective.MyFigure figure = new VistaActivity.Perspective.MyFigure();
        private float centerX, centerY, maxX, maxY, minMaxXY, drawingLength = figure.figureCoordinates.length;

        public Perspective(Context context) {
            super(context);
            path = new Path();
            paint = new Paint();
        }

        private float iX(float x){ return centerX + x; }

        private float iY(float y){ return centerY - y; }

        private void makeLine(Canvas canvas, int i, int j){
            VistaActivity.Perspective.Point2D p = figure.screenCoordenates[i], q = figure.screenCoordenates[j];
            canvas.drawLine(iX(p.getX()), iY(p.getY()), iX(q.getX()), iY(q.getY()), paint);
        }


        @Override
        public void onDraw(Canvas canvas){
            paint.reset();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            paint.setColor(Color.BLACK);
            paint.setAntiAlias(true);
            canvas.drawColor(Color.rgb(255, 255, 255));
            maxX = getWidth();
            maxY = getHeight();
            minMaxXY = Math.min(maxX, maxY);
            centerX = maxX / 2f;
            centerY = maxY / 2f;
            figure.d = figure.rho * minMaxXY / figure.figureSize;
            figure.eyeAndScreen();

            if(figura.equals("Cubo") || figura.equals("Piramide")){
                makeLine(canvas, 0,1); makeLine(canvas, 1,2);
                makeLine(canvas, 2,3); makeLine(canvas, 3,0);
                makeLine(canvas, 4,5); makeLine(canvas, 5,6);
                makeLine(canvas, 6,7); makeLine(canvas, 7,4);
                makeLine(canvas, 0,4); makeLine(canvas, 1,5);
                makeLine(canvas, 2,6); makeLine(canvas, 3,7);
            }
            else
            if(figura.equals("Prisma")){
                makeLine(canvas, 0, 1); makeLine(canvas, 1, 2); makeLine(canvas, 2, 3); makeLine(canvas, 3, 4); makeLine(canvas, 4, 0); // aristas horizontales inferiores
                makeLine(canvas, 5,6); makeLine(canvas, 6, 7); makeLine(canvas, 7, 8); makeLine(canvas, 8, 9); makeLine(canvas, 9, 5); // aristas horizontales superiores
                makeLine(canvas, 0, 5); makeLine(canvas, 1, 6); makeLine(canvas, 2, 7); makeLine(canvas, 3, 8); makeLine(canvas, 4, 9); //aristas verticales
            }
            else
            if(figura.equals("Cono")){
                int numeroLados = 30;

                for(int i = 0; i < numeroLados; i++){
                    if(i == numeroLados - 1){
                        makeLine(canvas, i, 0);
                        break;
                    }
                    makeLine(canvas, i, i+1);
                }

                for(int i = 0; i < numeroLados; i++){
                    makeLine(canvas, i, numeroLados);
                }
            }
            else
            if(figura.equals("Cilindro")){
                int numeroLados = 30;

                //BASE circular de n lados (unir aristas)
                for(int i = 0; i < numeroLados; i++){
                    if(i == numeroLados - 1){
                        makeLine(canvas, i, 0);
                        break;
                    }
                    makeLine(canvas, i, i+1);
                }

                for(int i = numeroLados; i < 2*numeroLados; i++){
                    if(i == 2*numeroLados - 1){
                        makeLine(canvas, i, numeroLados);
                        break;
                    }
                    makeLine(canvas, i, i+1);
                }

                for(int i = 0; i < numeroLados; i++){
                    makeLine(canvas, i, numeroLados+i);
                }
            }
            else
            if(figura.equals("Esfera")){
                int numeroLados = 15;

                //BASE circular de n lados (unir aristas)
                for(int j=1;j<=9;j++)
                    for(int i = (j-1)*numeroLados; i < j*numeroLados; i++){
                        if(i == j*numeroLados - 1){
                            makeLine(canvas, i, (j-1)*numeroLados);
                            break;
                        }
                        makeLine(canvas, i, i+1);
                    }

            }


        }

        @Override
        public boolean onTouchEvent(MotionEvent motionEvent){
            if(motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                centerX = motionEvent.getX();
                centerY = motionEvent.getY();
                figure.theta = getWidth() / centerX;
                figure.phi = getHeight() / centerY;
                figure.rho = (figure.phi / figure.theta) * getHeight();
                invalidate();
            }
            return true;
        }
    }
}