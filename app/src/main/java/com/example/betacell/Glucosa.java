package com.example.betacell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    Dialog pAerror;
    Button bPerror;
    EditText r_glucosa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucosa);
        pAceptar = new Dialog(this);
        //pAerror = new Dialog(this);
        r_glucosa=(EditText)findViewById(R.id.mol);
        sppiner = (Spinner)findViewById(R.id.sppiner);
        guardar=(Button)findViewById(R.id.guardar_glucosa);



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
        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(DatabaseHelper.MEDICION_GLUCOSA,r_glucosa.getText().toString());
        values.put(DatabaseHelper.CONDICION,sppiner.getSelectedItem().toString());
        values.put(DatabaseHelper.USUARIO_GLUCOSA,"DERIAN");
        values.put(DatabaseHelper.FECHA_GLUCOSA, fDate);


        Long idResultante=db.insert(DatabaseHelper.TABLE_GLUCOSA,DatabaseHelper.ID_GLUCOSA,values);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }


    public void showPop(){
        pAceptar.setContentView(R.layout.popup_aceptar);
        bPaceptar = (Button) pAceptar.findViewById(R.id.btn_correcto);

            bPaceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pAceptar.dismiss();
                }
            });
        pAceptar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pAceptar.show();
    }
    public void badShow(){
        pAerror.setContentView(R.layout.error);
        bPerror = (Button) bPerror.findViewById(R.id.btn_error);

        bPerror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pAerror.dismiss();
            }
        });
        pAerror.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pAerror.show();
    }
}