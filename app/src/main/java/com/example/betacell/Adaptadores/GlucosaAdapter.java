package com.example.betacell.Adaptadores;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.betacell.BaseDatos.DatabaseHelper;
import com.example.betacell.BaseDatos.GlucosaDB;
import com.example.betacell.BaseDatos.Presion;
import com.example.betacell.Glucosa;
import com.example.betacell.R;

import java.util.ArrayList;

public class GlucosaAdapter extends RecyclerView.Adapter<GlucosaAdapter.GlucosaViewHolder> {

    ArrayList<GlucosaDB> listaGlucosa;

    public GlucosaAdapter(ArrayList<GlucosaDB> listaGlucosa){
        this.listaGlucosa = listaGlucosa;
    }


    @Override
    public GlucosaViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_glucosa,null,false);
        return new GlucosaViewHolder(view);
    }

    @Override
    public void onBindViewHolder( GlucosaViewHolder holder, int position) {

        holder.medicion.setText(listaGlucosa.get(position).getMedicionGlucosa());
        holder.condicion.setText(listaGlucosa.get(position).getCondicion());
        holder.fecha.setText(listaGlucosa.get(position).getFecha());

    }

    @Override
    public int getItemCount() {
        return listaGlucosa.size();
    }

    public class GlucosaViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        TextView medicion;
        TextView condicion;
        TextView fecha;

        public GlucosaViewHolder(@NonNull View itemView) {
            super(itemView);

            card=(CardView)itemView.findViewById(R.id.cardGlucosa);
            medicion=(TextView)itemView.findViewById(R.id.medicionGlucosa_db);
            condicion=(TextView)itemView.findViewById(R.id.condicion_db);
            fecha=(TextView)itemView.findViewById(R.id.fechaGlucosa_db);

        }
    }
}