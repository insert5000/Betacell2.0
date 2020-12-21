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
import com.example.betacell.BaseDatos.Presion;
import com.example.betacell.R;

import java.util.ArrayList;

public class PresionAdapter extends RecyclerView.Adapter<PresionAdapter.PresionViewHolder> {

    ArrayList<Presion> listaPresion;
   
    public PresionAdapter(ArrayList<Presion> listaPresion){
        this.listaPresion = listaPresion;
    }


    @Override
    public PresionViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
    View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_presion,null,false);
    return new PresionViewHolder(view);
    }

    @Override
    public void onBindViewHolder( PresionViewHolder holder, int position) {

        holder.sistola.setText(listaPresion.get(position).getSistola());
        holder.pulsos.setText(listaPresion.get(position).getPulsos());
        holder.fecha.setText(listaPresion.get(position).getFecha());
        holder.diastola.setText(listaPresion.get(position).getDiastola());


    }

    @Override
    public int getItemCount() {
        return listaPresion.size();
    }

    public class PresionViewHolder extends RecyclerView.ViewHolder {

        CardView card;
        TextView diastola;
        TextView sistola;
        TextView pulsos;
        TextView fecha;


        public PresionViewHolder(@NonNull View itemView) {
            super(itemView);

            card=(CardView)itemView.findViewById(R.id.cardPresion);
            diastola=(TextView)itemView.findViewById(R.id.diastolica_db);
            sistola=(TextView)itemView.findViewById(R.id.sistolica_db);
            pulsos=(TextView)itemView.findViewById(R.id.pulsos_db);
            fecha=(TextView)itemView.findViewById(R.id.fecha_db);

        }
    }
}