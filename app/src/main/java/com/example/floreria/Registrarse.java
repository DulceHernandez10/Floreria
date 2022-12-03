package com.example.floreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registrarse extends AppCompatActivity {
    private EditText nombre, direccion, correo, telefono, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        nombre=findViewById(R.id.nombre);
        direccion=findViewById(R.id.direccion);
        correo=findViewById(R.id.correo);
        telefono=findViewById(R.id.telefono);
        contraseña=findViewById(R.id.contraseña);
    }
    public void altas(View view){
        AdminSQLiteOpenHelper flor =new AdminSQLiteOpenHelper(this, "floreria", null, 1);
        SQLiteDatabase bf=flor.getWritableDatabase();
        String nom=nombre.getText().toString();
        String dir=direccion.getText().toString();
        String cor=correo.getText().toString();
        String tel=telefono.getText().toString();
        String cont=contraseña.getText().toString();

        ContentValues pila=new ContentValues();
        pila.put("nombre_c",nom);
        pila.put("direccion_c",dir);
        pila.put("correo_c",cor);
        pila.put("telefono_c",tel);
        pila.put("contraseña_c",cont);

        bf.insert("clientes",null, pila);
        bf.close();


        Toast.makeText(this, "Te registraste con éxito", Toast.LENGTH_SHORT).show();
        limpiar();

    }
    public void limpiar(){
        nombre.setText("");
        direccion.setText("");
        correo.setText("");
        telefono.setText("");
        contraseña.setText("");
    }

    public void login(View view){
        Intent log=new Intent(Registrarse.this, MainActivity.class);
        startActivity(log);
    }
}