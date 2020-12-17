package com.example.betacell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.betacell.ConsejosAtv.Alimentacion;
import com.example.betacell.ConsejosAtv.Alimentos;
import com.example.betacell.ConsejosAtv.Ejercicios;
import com.example.betacell.ConsejosAtv.Hidratacion;
import com.example.betacell.ConsejosAtv.Pies;
import com.example.betacell.ConsejosAtv.Riesgo;

import java.util.ArrayList;
import java.util.List;

public class Consejos extends FragmentActivity implements View.OnClickListener {

    private List<ConsejosLista> lista;

    private void initializeData(){

        lista = new ArrayList<>();
        lista.add(new ConsejosLista("Alimentacion","Comida saludable",R.drawable.alimentacion));
        lista.add(new ConsejosLista("Ejercicio","Llevar una rutina de ejercicios..",R.drawable.ejercicio));
        lista.add(new ConsejosLista("Hidratacion","Es importante tener una buena hidratacion..",R.drawable.hidratacion));
        lista.add(new ConsejosLista("Pies","Cuidar los pies..",R.drawable.pies));
        lista.add(new ConsejosLista("Riesgos","Llevar una rutina de ejercicios..",R.drawable.riesgo));



    }

    Button alimentacion,hidratacion,pies,riesgos,ejercicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);

        alimentacion = (Button) findViewById(R.id.btn_alimentacion);
        hidratacion = (Button)findViewById(R.id.btn_hidratacion);
        pies = (Button)findViewById(R.id.btn_pies);
        riesgos = (Button)findViewById(R.id.btn_riesgos);
        ejercicio = (Button)findViewById(R.id.btn_ejercicios);


        alimentacion.setOnClickListener( this);
        hidratacion.setOnClickListener( this);
        pies.setOnClickListener( this);
        riesgos.setOnClickListener( this);
        ejercicio.setOnClickListener( this);

       /* final RecyclerView rv = (RecyclerView)findViewById(R.id.rview);

        rv.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getApplicationContext());

        rv.setLayoutManager(linearLayoutManager);

        initializeData();
        AdapterC rvAdapter = new AdapterC(lista,this);
        rv.setAdapter(rvAdapter);*/



       // rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.btn_alimentacion:
                i = new Intent(this, Alimentos.class);
                startActivity(i);
                break;
            case R.id.btn_hidratacion:
                i = new Intent(this, Hidratacion.class);
                startActivity(i);
                break;
            case R.id.btn_pies:
                i = new Intent(this, Pies.class);
                startActivity(i);
                break;
            case R.id.btn_riesgos:
                i = new Intent(this, Riesgo.class);
                startActivity(i);
                break;
            case R.id.btn_ejercicios:
                i = new Intent(this, Ejercicios.class);
                startActivity(i);
                break;

        }
    }
}