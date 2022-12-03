package com.example.floreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Menu_cliente extends AppCompatActivity {
    private EditText correo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);
        correo2=findViewById(R.id.correo2);
    }
    public void modificar(View view){
        Intent mod=new Intent(Menu_cliente.this, Modificar_datosC.class);
        startActivity(mod);
    }
    public void eliminarC(View view){
        AdminSQLiteOpenHelper flor =new AdminSQLiteOpenHelper(this, "floreria", null, 1);
        SQLiteDatabase bf=flor.getWritableDatabase();
        String em=correo2.getText().toString();
        int cant=bf.delete("clientes", "id_c="+em, null);
        if(cant==1){
            Toast.makeText(this, "Tu cuenta fue eliminada con exito, al salir ya no estas registrado con nosotros", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();


        }
        bf.close();

    }
    public void generarQR(View view){
        Intent qr=new Intent(Menu_cliente.this, GenerarFact.class);
        startActivity(qr);
    }
    public void menuR(View view){
        Intent mR=new Intent(Menu_cliente.this, Menu_entrega.class);
        startActivity(mR);
    }
    public void cerrar(View view){
        finish();
        Intent v1=new Intent(Menu_cliente.this, MainActivity.class);
        startActivity(v1);
    }

}