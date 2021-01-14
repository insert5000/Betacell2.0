package com.example.betacell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.betacell.BaseDatos.DatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Glucosa extends FragmentActivity {
    private Spinner sppiner;
    Button guardar;
    Dialog pAceptar;
    Button bPaceptar;
    Button bPerror;
    EditText r_glucosa;
    NumberPicker piker_1_g;
    NumberPicker piker_2_g;
    ImageView helpG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucosa);
        pAceptar = new Dialog(this);
        sppiner = (Spinner)findViewById(R.id.sppiner);
        guardar=(Button)findViewById(R.id.guardar_glucosa);
        helpG = (ImageView)findViewById(R.id.help_glucosa);

        piker_1_g=(NumberPicker)findViewById(R.id.piker_1_g);
        piker_2_g=(NumberPicker)findViewById(R.id.piker_2_g);

        piker_1_g.setMinValue(1);
        piker_1_g.setMaxValue(40);

        piker_2_g.setMinValue(0);
        piker_2_g.setMaxValue(9);

        helpG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop();
            }
        });

        String[] opciones = {"Al menos 90 minutos después de la comida", "Ayuno", "Antes de las comidas"};
        ArrayAdapter<String> sppinerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opciones);
        sppiner.setAdapter(sppinerAdapter);

            guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        registrarGlucosa();

                    }
                });

    }

    private void registrarGlucosa() {
        DatabaseHelper conn=new DatabaseHelper(this,"betacellDB",null,1);
        Date d = new Date();
        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(d);
        String muestra = piker_1_g.getValue()+"."+piker_2_g.getValue();
        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(DatabaseHelper.MEDICION_GLUCOSA, muestra);
        values.put(DatabaseHelper.CONDICION,sppiner.getSelectedItem().toString());
        values.put(DatabaseHelper.USUARIO_GLUCOSA,"DERIAN");
        values.put(DatabaseHelper.FECHA_GLUCOSA, fDate);


        Long idResultante=db.insert(DatabaseHelper.TABLE_GLUCOSA,DatabaseHelper.ID_GLUCOSA,values);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
        Intent i = new Intent(this, Principal.class);
        startActivity(i);
    }


    public void showPop(){
        pAceptar.setContentView(R.layout.pop_up_boton);
        bPaceptar = (Button) pAceptar.findViewById(R.id.btn_correcto2);

        bPaceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pAceptar.dismiss();
                }
            });
        pAceptar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pAceptar.show();
    }

}