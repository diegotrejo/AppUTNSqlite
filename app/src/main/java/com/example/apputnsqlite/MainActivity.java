package com.example.apputnsqlite;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText txtApellidos, txtNombres, txtID, txtIsoPais, txtEdad;
    autores lstAutores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //autores listaAutores = new autores(this, "biblioteca.db", 1);
        //Autor r = listaAutores.Create(1, "AMADO", "NERVO", "CL", 100);
        //Autor r = listaAutores.Update(1, "AMAANO EULALIO", "NERVO PAREDES", "EC", 45);
        //Autor r = listaAutores.Read_ById(1);
        //boolean r = listaAutores.Delete(1);
        //Log.println(Log.VERBOSE, "INFO", "" + r);

        txtID = findViewById(R.id.txtID);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtNombres = findViewById(R.id.txtNombres);
        txtIsoPais = findViewById(R.id.txtIsoPais);
        txtEdad = findViewById(R.id.txtEdad);
        lstAutores = new autores(
                this,
                "biblioteca.db",
                1);
    }

    public void cmdCrear_onClick(View v)
    {
        Autor r = lstAutores.Create(
                Integer.parseInt( txtID.getText().toString() ),
                txtNombres.getText().toString(),
                txtApellidos.getText().toString(),
                txtIsoPais.getText().toString(),
                Integer.parseInt( txtEdad.getText().toString() )
        );
        if (r != null)
            Toast.makeText(
                    this,
                    "Autor creado correctamente !!",
                    Toast.LENGTH_LONG
            ).show();
        else
            Toast.makeText(
                    this,
                    "ERROR AL CREAR AUTOR !!",
                    Toast.LENGTH_LONG
            ).show();
    }

    public void cmdActualizar_onClick(View v)
    {
        Autor r = lstAutores.Update(
                Integer.parseInt( txtID.getText().toString() ),
                txtNombres.getText().toString(),
                txtApellidos.getText().toString(),
                txtIsoPais.getText().toString(),
                Integer.parseInt( txtEdad.getText().toString() )
        );
        if (r != null)
            Toast.makeText(
                    this,
                    "Autor actualizado correctamente !!",
                    Toast.LENGTH_LONG
            ).show();
        else
            Toast.makeText(
                    this,
                    "ERROR ACTUALIZANDO DATOS !!",
                    Toast.LENGTH_LONG
            ).show();
    }

    public void cmdLeer_onClick(View v)
    {
        Autor r = lstAutores.Read_ById(
                Integer.parseInt( txtID.getText().toString() )
        );
        if ( r != null)
        {
            txtNombres.setText( r.Nombres );
            txtApellidos.setText( r.Apellidos );
            txtIsoPais.setText( r.IsoPais );
            txtEdad.setText( "" + r.Edad );
        }
        else
        {
            Toast.makeText(
              this,
              "REGISTRO NO ENCONTRADO",
              Toast.LENGTH_LONG
            ).show();
        }
    }

    public void cmdEliminar_onClick(View v){
        boolean r = lstAutores.Delete(
                Integer.parseInt( txtID.getText().toString() )
        );
        if (r)
        {
            txtID.setText("");
            txtNombres.setText("");
            txtApellidos.setText("");
            txtIsoPais.setText("");
            txtEdad.setText("");
            Toast.makeText(this,"Registro BORRADO ok", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this,"ERROR BORRANDO REGISTRO", Toast.LENGTH_LONG).show();
    }
}