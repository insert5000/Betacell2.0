package com.example.betacell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.betacell.Adaptadores.PresionAdapter;
import com.example.betacell.BaseDatos.DatabaseHelper;
import com.example.betacell.BaseDatos.Presion;

import java.util.ArrayList;

public class BitacoraPresion extends FragmentActivity {

    ArrayList<Presion> listaPresion;
    RecyclerView recyclePresion;
    DatabaseHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora_presion);

    conn = new DatabaseHelper(getApplicationContext() ,"betacellDB", null, 1);

    listaPresion = new ArrayList<>();

    recyclePresion = (RecyclerView) findViewById(R.id.recyclePresion);
    recyclePresion.setLayoutManager(new LinearLayoutManager(this));

    consultarListaPresion();
        PresionAdapter adapter = new PresionAdapter(listaPresion);
        recyclePresion.setAdapter(adapter);
    }

    private void consultarListaPresion(){
        SQLiteDatabase db =  conn.getReadableDatabase();

        Presion presion =null;
        Cursor cursor = db.rawQuery("SELECT * FROM presion",null);

        while (cursor.moveToNext()){
            presion =new Presion();
            presion.setSistola(cursor.getString(1));
            presion.setDiastola(cursor.getString(2));
            presion.setPulsos(cursor.getString(3));
            presion.setFecha(cursor.getString(4));

            listaPresion.add(presion);

        }


    }


}