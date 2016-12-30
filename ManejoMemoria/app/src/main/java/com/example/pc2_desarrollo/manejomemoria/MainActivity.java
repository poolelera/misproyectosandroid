package com.example.pc2_desarrollo.manejomemoria;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void generarArchivo(View v){
        try {

            EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
            String nombre = edtNombre.getText().toString();

            FileOutputStream outputStream = null;
            outputStream = openFileOutput("MiArchivo.txt", Context.MODE_PRIVATE);
            outputStream.write(nombre.getBytes());
            outputStream.close();
            Toast.makeText(MainActivity.this, "El archivo se ha creado", Toast.LENGTH_SHORT).show();
            edtNombre.setText("");
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Hubo un error en la escritura del archivo", Toast.LENGTH_SHORT).show();
        }
    }
}
