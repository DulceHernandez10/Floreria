package com.example.floreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar_datosC extends AppCompatActivity {
    private EditText nombre2;
    private EditText nombre, direccion, correo, telefono, contraseña;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_datos_c);
        nombre2=findViewById(R.id.nombre2);
        nombre=findViewById(R.id.nombre);
        direccion=findViewById(R.id.direccion);
        correo=findViewById(R.id.correo);
        telefono=findViewById(R.id.telefono);
        contraseña=findViewById(R.id.contraseña);


    }
    public void consultar(View view){
        AdminSQLiteOpenHelper flor =new AdminSQLiteOpenHelper(this, "floreria", null, 1);
        SQLiteDatabase bf=flor.getWritableDatabase();
        String nom2=nombre2.getText().toString();
        Cursor fila=bf.rawQuery("SELECT id_c, nombre_c, direccion_c, correo_c, telefono_c, contraseña_c FROM clientes WHERE id_c="+nom2, null);
        if(fila.moveToFirst()){
            nombre.setText(fila.getString(1));
            direccion.setText(fila.getString(2));
            correo.setText(fila.getString(3));
            telefono.setText(fila.getString(4));
            contraseña.setText(fila.getString(5));


        }
        else{
            Toast.makeText(this, "Error al actualizar tus datos", Toast.LENGTH_SHORT).show();

        }
    }

    public void modificar(View view){
        AdminSQLiteOpenHelper flor =new AdminSQLiteOpenHelper(this, "floreria", null, 1);
        SQLiteDatabase bf=flor.getWritableDatabase();
        String i=nombre2.getText().toString();
        String nom=nombre.getText().toString();
        String dir=direccion.getText().toString();
        String cor=correo.getText().toString();
        String tel=telefono.getText().toString();
        String cont=contraseña.getText().toString();

        ContentValues pila=new ContentValues();
        pila.put("id_c", i);
        pila.put("nombre_c",nom);
        pila.put("direccion_c",dir);
        pila.put("correo_c",cor);
        pila.put("telefono_c",tel);
        pila.put("contraseña_c",cont);

        int cant=bf.update("clientes", pila, "id_c="+i, null);
        bf.close();

        if(cant==1){
            Toast.makeText(this, "Tus datos fueron guardados con exito", Toast.LENGTH_SHORT).show();
            nombre.setText("");
            direccion.setText("");
            correo.setText("");
            telefono.setText("");
            contraseña.setText("");
        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

        }
    }

    public void regresar(View view){
        Intent reg=new Intent(Modificar_datosC.this, Menu_cliente.class);
        startActivity(reg);
    }
}