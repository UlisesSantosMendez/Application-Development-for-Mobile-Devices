package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.db.DbContactos;
import com.example.myapplication.entidades.Contactos;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtCorreo;
    Button btnEnviar;

    Contactos contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombreVer);
        txtCorreo = findViewById(R.id.txtCorreoElectronicoVer);
        btnEnviar = findViewById(R.id.btnEnviarCorreo);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id = Integer.parseInt(null);
            }
            else{
                id = extras.getInt("ID");
            }
        }
        else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbContactos dbContactos = new DbContactos(VerActivity.this);
        contacto = dbContactos.verContacto(id);

        if (contacto != null){
            txtNombre.setText(contacto.getNombre());
            txtCorreo.setText(contacto.getCorreo());
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtCorreo.setInputType(InputType.TYPE_NULL);
        }

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EnviarActivity.class);
                startActivity(intent);
            }
        });
    }
}