package com.example.betacell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Bitacora extends FragmentActivity implements View.OnClickListener {

    CardView presionCard;
    CardView glucosaCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora);

        presionCard = (CardView)findViewById((R.id.presion_card));
        glucosaCard = (CardView)findViewById((R.id.glucosa_card));


        presionCard.setOnClickListener(this);
        glucosaCard.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.presion_card:
                i = new Intent(this, BitacoraPresion.class);
                startActivity(i);
                break;
            case R.id.glucosa_card:
                i = new Intent(this, BitacoraGlucosa.class);
                startActivity(i);
                break;

        }
    }
}