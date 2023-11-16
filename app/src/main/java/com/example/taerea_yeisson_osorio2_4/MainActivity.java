package com.example.taerea_yeisson_osorio2_4;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.taerea_yeisson_osorio2_4.Conecxion.Conecxion;
import com.example.taerea_yeisson_osorio2_4.Conecxion.signatures;
import com.github.gcacace.signaturepad.views.SignaturePad;


import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    SignaturePad firma;

    EditText Descripcion;

    Bitmap imagenb;

    Button salvar,blista,clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firma = (SignaturePad) findViewById(R.id.firma);
        Descripcion = (EditText) findViewById(R.id.txtdescripcion);


        salvar = (Button) findViewById(R.id.btnsalvar);
        blista= (Button)  findViewById(R.id.Btnlista);
        clear = (Button) findViewById(R.id.btnLimpiar);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Descripcion.getText().toString().replaceAll("\\s","").isEmpty()){
                    Descripcion.setError("Este campo es obligatorio");
                }else {
                    imagenb= firma.getSignatureBitmap();
                    addFirma(imagenb);
                }
            }
        });

        blista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Activitilista.class);
                startActivity(intent);
                finish();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firma.clear();
            }
        });

    }

    private void addFirma(Bitmap bitmap) {
        try {
            Conecxion con = new Conecxion(this, signatures.NameDatabase,null,1);
            SQLiteDatabase db =  con.getWritableDatabase();

            byte[] firmabyte = convertirBitmapABytes(imagenb);

            ContentValues valores = new ContentValues();
            valores.put(signatures.Descripcion, Descripcion.getText().toString());
            valores.put(String.valueOf(signatures.firma),firmabyte);

            Long Result = db.insert(signatures.tablacontactos,signatures.id, valores);

            Toast.makeText(this,"Firma Guardada", Toast.LENGTH_SHORT).show();
            db.close();
        }
        catch (Exception exception)
        {
            Toast.makeText(this,"Algo salio mal", Toast.LENGTH_SHORT).show();
        }
    }
    private byte[] convertirBitmapABytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}