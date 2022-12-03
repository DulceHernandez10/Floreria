package com.example.floreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
    }

    public void entrar(View view){
        AdminSQLiteOpenHelper flor =new AdminSQLiteOpenHelper(this, "floreria", null, 1);
        SQLiteDatabase bf=flor.getWritableDatabase();
        String em=email.getText().toString();
        String pass=password.getText().toString();
        Cursor fila=bf.rawQuery("SELECT id_c, nombre_c, direccion_c, correo_c, telefono_c, contraseña_c FROM clientes WHERE id_c="+em, null);
        if(fila.moveToFirst()){
            Intent ini=new Intent(MainActivity.this, Menu_cliente.class);
            startActivity(ini);
        }else{
            Toast.makeText(this, "Lo lamento, sus datos no son correctos", Toast.LENGTH_SHORT).show();

        }
    }

    public void regis(View view){
        Intent re=new Intent(MainActivity.this, Registrar_fire.class);
        startActivity(re);
    }

}