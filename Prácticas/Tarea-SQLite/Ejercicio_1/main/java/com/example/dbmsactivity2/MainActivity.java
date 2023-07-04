package com.example.dbmsactivity2;

import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import java.lang.*;
import android.view.Window.Callback;
import android.view.KeyEvent.*;
import android.widget.*;
import android.os.*;
import android.app.Dialog;
import android.accessibilityservice.*;


public class MainActivity extends Activity implements OnClickListener{
    EditText jetI, jetN, jetC;
    Button jbnA, jbnB, jbnC, jbnV, jbnL, jbnI;
    SQLiteDatabase db;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jetI=(EditText)findViewById(R.id.xetI);
        jetN=(EditText)findViewById(R.id.xetN);
        jetC=(EditText)findViewById(R.id.xetC);
        jbnA=(Button)findViewById(R.id.xbnA);
        jbnA.setOnClickListener(this);
        jbnB=(Button)findViewById(R.id.xbnB);
        jbnB.setOnClickListener(this);
        jbnC=(Button)findViewById(R.id.xbnC);
        jbnC.setOnClickListener(this);
        jbnV=(Button)findViewById(R.id.xbnV);
        jbnV.setOnClickListener(this);
        jbnL=(Button)findViewById(R.id.xbnL);
        jbnL.setOnClickListener(this);
        jbnI=(Button)findViewById(R.id.xbnI);
        jbnI.setOnClickListener(this);
        db=openOrCreateDatabase("DBEstudiantes", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS estudiantes (ID VARCHAR, nombre VARCHAR, calificacion VARCHAR);");
    }
    public void onClick(View v){
        if(v==jbnA){
            if(jetI.getText().toString().trim().length() ==0 || jetN.getText().toString().trim().length()==0 || jetC.getText().toString().trim().length()==0){
                mensaje("Error", "Ingresar todos los datos");
                return;
            }
            db.execSQL("INSERT INTO estudiantes VALUES('" + jetI.getText() + "','" + jetN.getText() + "','" + jetC.getText() + "');");
            mensaje("Alta", "Registro agregado");
            limpiar();
        }
        if(v==jbnB){
            if(jetI.getText().toString().trim().length()==0){
                mensaje("Error", "Ingresar el ID");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM estudiantes WHERE ID='" + jetI.getText() + "'", null);
            if(c.moveToFirst()) {
                db.execSQL("DELETE FROM estudiantes WHERE ID='" + jetI.getText() + "'");
                mensaje("Baja", "Registro eliminado");
            }
            else {
                mensaje("Error", "ID inválido");
            }
            limpiar();
        }
        if(v==jbnC){
            if(jetI.getText().toString().trim().length()==0){
                mensaje("Error", "Ingresar el ID");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM estudiantes WHERE ID='" + jetI.getText() + "'", null);
            if(c.moveToFirst()){
                db.execSQL("UPDATE estudiantes SET nombre='" + jetN.getText() + "', calificacion='" + jetC.getText() + "' WHERE ID='" + jetI.getText() + "'");
                mensaje("Cambio", "Registro modificado");
            }
            else{
                mensaje("Error", "ID inválido");
            }
            limpiar();
        }
        if(v==jbnV){
            if(jetI.getText().toString().trim().length()==0) {
                mensaje("Error", "Ingresar el ID");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM estudiantes WHERE ID='" + jetI.getText() + "'", null);
            if(c.moveToFirst()) {
                jetN.setText(c.getString(1));
                jetC.setText(c.getString(2));
            }
            else{
                mensaje("Error", "ID inválido");
                limpiar();
            }
        }
        if(v==jbnL){
            Cursor c=db.rawQuery("SELECT * FROM estudiantes", null);
            if(c.getCount()==0){
                mensaje("Error", "No hay registros");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext()) {
                buffer.append("ID: " + c.getString(0)+"\n");
                buffer.append("Nombre: " + c.getString(1)+"\n");
                buffer.append("Calificacion: " + c.getString(2)+"\n\n");
            }
            mensaje("Lista", buffer.toString());
        }
        if(v==jbnI){
            mensaje("Control de Calificaciones", "ESCOM");
        }
    }
    public void mensaje(String s, String m){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setCancelable(true);
        b.setTitle(s);
        b.setMessage(m);
        b.show();
    }
    public void limpiar() {
        jetI.setText("");
        jetN.setText("");
        jetC.setText("");
        jetI.requestFocus();
    }
}