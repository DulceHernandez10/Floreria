package com.example.floreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Generar_qr extends AppCompatActivity {
    EditText datos;
    Button scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_qr);

        datos=findViewById(R.id.datos);
        scan=findViewById(R.id.scan);

        scan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                IntentIntegrator integrador =new IntentIntegrator(Generar_qr.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("Lector - CDP");
                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();
            }


      });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
            IntentResult result=IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if(result !=null){
                if(result.getContents()==null){
                    Toast.makeText(this, "Lector cancelada", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    datos.setText(result.getContents());

                }
            }else {
                super.onActivityResult(requestCode, resultCode, data);
            }

    }
}