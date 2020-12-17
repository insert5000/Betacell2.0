package com.example.betacell;

import androidx.fragment.app.FragmentActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betacell.BaseDatos.DatabaseHelper;
import com.example.betacell.BaseDatos.Presion;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Press extends FragmentActivity {

    EditText registro,pulsos;
    Button registrar;
    DatabaseHelper databaseHelper;
    Dialog pAceptar;
    Button bPaceptar;

    Dialog pAerror;
    Button bPerror;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presion2);
        databaseHelper = new DatabaseHelper(this);

        registrar = (Button)findViewById(R.id.guardar_Presion);
        pulsos = (EditText)findViewById(R.id.pulsos_txt);
        registro =(EditText)findViewById(R.id.regitroPresion_txt);

            pAceptar = new Dialog(this);
           // pAerror = new Dialog(this);

      /* registrar.setOnClickListener(new View.OnClickListener() {
           String reg = registro.getText().toString();
           String pul = pulsos.getText().toString();
           String user = MainActivity.ID_USUARIO;
           String fec="ddd";

           @Override
           public void onClick(View v) {


                   databaseHelper.addPresion(new Presion(null, reg, pul, fec, user));
                   showPop();

           }
       });*/


    registrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            registrarPresion();
        }
    });

    }




    private void registrarPresion() {
        DatabaseHelper conn=new DatabaseHelper(this,"betacellDB",null,1);
        Date d = new Date();
        String fDate = new SimpleDateFormat("yyyy-MM-dd").format(d);
        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(DatabaseHelper.REGISTRO,registro.getText().toString());
        values.put(DatabaseHelper.PULSOS,pulsos.getText().toString());
        values.put(DatabaseHelper.USUARIO,"DERIAN");
        values.put(DatabaseHelper.FECHA,fDate);


        Long idResultante=db.insert(DatabaseHelper.TABLE_PRESION,DatabaseHelper.ID,values);

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
        bPerror = (Button) pAceptar.findViewById(R.id.btn_error);

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