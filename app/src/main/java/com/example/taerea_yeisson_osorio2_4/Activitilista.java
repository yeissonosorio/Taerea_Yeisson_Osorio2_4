package com.example.taerea_yeisson_osorio2_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.taerea_yeisson_osorio2_4.Conecxion.Conecxion;
import com.example.taerea_yeisson_osorio2_4.Conecxion.signatures;
import com.example.taerea_yeisson_osorio2_4.Modelo.Firma;
import com.example.taerea_yeisson_osorio2_4.Modelo.Firmas_class;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class Activitilista extends AppCompatActivity {

    Conecxion conecxion;

    Button atras;
    ArrayList<Firmas_class> listfoto;
    List<Firma> data = new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitilista);

        conecxion = new Conecxion(this,signatures.NameDatabase,null,1);

        recyclerView = findViewById(R.id.listr);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        GetFoto();

        MyAdapter adp = new MyAdapter(data);
        recyclerView.setAdapter(adp);

        atras = (Button) findViewById(R.id.btnAtras);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void GetFoto()
    {
        SQLiteDatabase db = conecxion.getReadableDatabase();
        Firmas_class fir = null;
        listfoto = new ArrayList<Firmas_class>();

        Cursor cursor = db.rawQuery(signatures.Selectfirmas,null);

        while(cursor.moveToNext())
        {
            fir = new Firmas_class();
            fir.setId(cursor.getInt(0));
            fir.setDescripcion(cursor.getString(1));
            byte[] blob = cursor.getBlob(2);
            fir.setImage(blob);
            listfoto.add(fir);
        }

        cursor.close();
        FillList();
    }
    private void FillList()
    {

        for(int i = 0; i < listfoto.size(); i++)
        {
            data.add(new Firma(listfoto.get(i).getId(),listfoto.get(i).getDescripcion(),revelar(listfoto.get(i).getImage())));
        }
    }

    public Bitmap revelar(byte[] blob){
        Bitmap bitmap = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(blob);
        bitmap = BitmapFactory.decodeStream(bais);
        return bitmap;
    }
}