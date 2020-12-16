package com.example.betacell;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.text.LineBreaker;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterC
extends RecyclerView.Adapter<AdapterC.ConsejosViewHolder>
{



    List<ConsejosLista> consejos;
    Context context;

    AdapterC(List<ConsejosLista> consejos, Context context){
        this.consejos = consejos;
        this.context = context;
    }



    @NonNull
    @Override
    public ConsejosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.lista, parent, false);
        ConsejosViewHolder cvh= new ConsejosViewHolder(view);

        return cvh;
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ConsejosViewHolder holder, final int position) {
        final int pos = position;
        holder.nombreConsejo.setText(consejos.get(position).nombre);
        holder.descripcion.setText(consejos.get(position).descripcion);


        Drawable original = context
                .getResources()
                .getDrawable(consejos.get(position).imagen
                        ,null);

        Bitmap originalBitmap = ((BitmapDrawable) original).getBitmap();

        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(context.getResources()
                        , originalBitmap);

        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        holder.imagen.setImageDrawable(roundedDrawable);
        holder.setOnClickListeners();
    }

    @Override
    public int getItemCount() {
        return consejos.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }



    public static class ConsejosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            CardView cv;
            TextView nombreConsejo;
            TextView descripcion;
            ImageView imagen;
            Button ver;
            Dialog dialog;

            ConsejosViewHolder(View itemView){
            super(itemView);
            cv =(CardView)itemView.findViewById(R.id.cv);
            nombreConsejo = (TextView)itemView.findViewById(R.id.nombreConsejo);
            descripcion = (TextView)itemView.findViewById(R.id.descripcion);
            imagen = (ImageView)itemView.findViewById(R.id.imagen);
            ver=(Button)itemView.findViewById(R.id.btn_mas);

}

        public void setOnClickListeners() {
            ver.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_mas:
                    crearPopUpDesdeBoton(itemView,nombreConsejo,descripcion);
                    break;

            }
        }

        private void crearPopUpDesdeBoton(final View itemView, TextView nombreFutbolista, TextView infoFutbolista) {
            //creando dialog
            dialog = new Dialog(itemView.getContext());
           dialog.setContentView(R.layout.pop_up_boton);
            dialog.setTitle(nombreFutbolista.getText().toString());
            //creando objetos
            TextView titulo = (TextView) dialog.findViewById(R.id.nombreConsejo);
            TextView descripcion = (TextView) dialog.findViewById(R.id.descripcion);
            //dando valor a los objetos
            titulo.setText(nombreFutbolista.getText().toString());
            descripcion.setText(infoFutbolista.getText().toString());
            //evento del boton

            //descripcion.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            //Iniciando pop-up
            dialog.show();
        }
    }
}
