package com.example.dbmssqlite1;

import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;
import android.os.*;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
    EditText jetI, jetN;
    Button jbnA, jbnL;
    TextView jtvL;
    SQLiteDatabase sqld;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jetI = (EditText) findViewById(R.id.xetI);
        jetN = (EditText) findViewById(R.id.xetN);
        jbnA = (Button) findViewById(R.id.xbnA);
        jbnL = (Button) findViewById(R.id.xbnL);
        jtvL = (TextView) findViewById(R.id.xtvL);
        DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(this, "DBContactos", null, 1);
        sqld = dsqlh.getWritableDatabase();
        jbnA.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = jetI.getText().toString();
                String nombre = jetN.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put("id", id);
                cv.put("nombre", nombre);
                sqld.insert("Contactos", null, cv);
                jetI.setText(""); jetN.setText("");
            }
        });
        jbnL.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String id, nombre;
                Cursor c = sqld.rawQuery("SELECT id,nombre FROM Contactos", null);
                jtvL.setText("");
                if (c.moveToFirst()) {
                    do {
                        id = c.getString(0);
                        nombre = c.getString(1);
                        jtvL.append(" " + id + "\t" + nombre + "\n");
                    } while(c.moveToNext());
                }
            }
        });
    }
}