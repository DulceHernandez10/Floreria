package com.example.floreria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registrar_fire extends AppCompatActivity {
    private EditText nombre, direccion, correo, telefono, password;
    private Button btn_registrar;

    private String name = "";
    private String dire = "";
    private String em = "";
    private String tel = "";
    private String pass = "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_registrar_fire);
        nombre = findViewById(R.id.nombre);
        direccion = findViewById(R.id.direccion);
        correo = findViewById(R.id.correo);
        telefono = findViewById(R.id.telefono);
        password = findViewById(R.id.contraseña);
        btn_registrar = findViewById(R.id.btn_registrar);

        btn_registrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                name = nombre.getText().toString();
                dire = direccion.getText().toString();
                em = correo.getText().toString();
                tel = telefono.getText().toString();
                pass = password.getText().toString();

                if (!name.isEmpty() && !dire.isEmpty() && !em.isEmpty() && !tel.isEmpty() && !pass.isEmpty()) {
                    if (pass.length() >= 6) {
                        registerUser();
                    } else {
                        Toast.makeText(Registrar_fire.this, "El password debe de tener almenos 6 caracteres", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(Registrar_fire.this, "Debe de completar todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("dire", dire);
                    map.put("em", em);
                    map.put("tel", tel);
                    map.put("pass", pass);

                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                //startActivity(new Intent(Registrar_fire.this, Menu_cliente.class));
                                finish();

                            } else {
                                Toast.makeText(Registrar_fire.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                } else {
                    Toast.makeText(Registrar_fire.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void Regresar(View view) {
        finish();

    }
}

