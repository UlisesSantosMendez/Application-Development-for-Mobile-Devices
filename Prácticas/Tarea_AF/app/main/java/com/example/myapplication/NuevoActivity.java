package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtCorreoElectronico;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtCorreoElectronico.getText().toString());

                if (id > 0){
                    Toast.makeText(NuevoActivity.this, "Registro exitoso!", Toast.LENGTH_SHORT).show();
                    limpiar();
                }
                else {
                    Toast.makeText(NuevoActivity.this, "Error al guardar el registro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void limpiar(){
        txtNombre.setText("");
        txtCorreoElectronico.setText("");
    }
}