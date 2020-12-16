package com.example.betacell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Consejos extends FragmentActivity {

    private List<ConsejosLista> lista;

    private void initializeData(){

        lista = new ArrayList<>();
        lista.add(new ConsejosLista("Alimentacion","Comida saludable",R.drawable.alimentacion));
        lista.add(new ConsejosLista("Ejercicio","Llevar una rutina de ejercicios..",R.drawable.ejercicio));
        lista.add(new ConsejosLista("Hidratacion","Es importante tener una buena hidratacion..",R.drawable.hidratacion));
        lista.add(new ConsejosLista("Pies","Cuidar los pies..",R.drawable.pies));
        lista.add(new ConsejosLista("Riesgos","Llevar una rutina de ejercicios..",R.drawable.riesgo));



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);

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
}