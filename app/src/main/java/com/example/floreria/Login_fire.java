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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_fire extends AppCompatActivity {
    Button btn_iniciar;
    EditText email, password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fire);
        mAuth= FirebaseAuth.getInstance();

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btn_iniciar=findViewById(R.id.btn_iniciar);

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser=email.getText().toString().trim();
                String passUser=password.getText().toString().trim();

                if(emailUser.isEmpty() && passUser.isEmpty()){
                    Toast.makeText(Login_fire.this, "Ingresa los datos correctamente", Toast.LENGTH_SHORT).show();

                }else{
                    loginUser(emailUser, passUser);
                }
            }

            private void loginUser(String emailUser, String passUser) {
                mAuth.signInWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(Login_fire.this, Menu_cliente.class));
                            Toast.makeText(Login_fire.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(Login_fire.this, "Error", Toast.LENGTH_SHORT).show();

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login_fire.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();

                    }
                });

            }

        });


    }

    public void Registrarse(View view){
        startActivity(new Intent(Login_fire.this, Registrar_fire.class));

    }

}