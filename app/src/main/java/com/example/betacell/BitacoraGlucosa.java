package com.example.betacell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.betacell.Adaptadores.GlucosaAdapter;
import com.example.betacell.Adaptadores.PresionAdapter;
import com.example.betacell.BaseDatos.DatabaseHelper;
import com.example.betacell.BaseDatos.GlucosaDB;
import com.example.betacell.BaseDatos.Presion;

import java.util.ArrayList;

public class BitacoraGlucosa extends AppCompatActivity {


    ArrayList<GlucosaDB> listaGlucosa;
    RecyclerView recycleGlucosa;
    DatabaseHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora_glucosa);

        conn = new DatabaseHelper(getApplicationContext() ,"betacellDB", null, 1);

        listaGlucosa = new ArrayList<>();

        recycleGlucosa = (RecyclerView) findViewById(R.id.recycleGlucosa);
        recycleGlucosa.setLayoutManager(new LinearLayoutManager(this));

        consultarListaGlucosa();
        GlucosaAdapter adapter = new GlucosaAdapter(listaGlucosa);
        recycleGlucosa.setAdapter(adapter);
    }

    private void consultarListaGlucosa(){
        SQLiteDatabase db =  conn.getReadableDatabase();

        GlucosaDB glucosa =null;
        Cursor cursor = db.rawQuery("SELECT * FROM glucosa",null);

        while (cursor.moveToNext()){
            glucosa =new GlucosaDB();
            glucosa.setMedicionGlucosa(cursor.getString(1));
            glucosa.setCondicion(cursor.getString(2));
            glucosa.setFecha(cursor.getString(3));

            listaGlucosa.add(glucosa);

        }
    }
}