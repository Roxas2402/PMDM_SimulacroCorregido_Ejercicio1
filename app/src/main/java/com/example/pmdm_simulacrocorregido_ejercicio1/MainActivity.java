package com.example.pmdm_simulacrocorregido_ejercicio1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //Buenas prácticas de programación
    private final int PRECIO_TABACO = 5;
    private final int CANTIDAD_CIGARROS = 20;

    //Contadores
    private int dineroAhorrado = 0;
    private int cantidadCigarros = CANTIDAD_CIGARROS;

    //Views
    private TextView lblDinero;
    private TextView lblCigarros;
    private ImageButton btnFumar;

    //Extras
    private NumberFormat nf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nf = NumberFormat.getCurrencyInstance();
        inicia();

        btnFumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantidadCigarros --;
                if (cantidadCigarros == 0) {
                    dineroAhorrado += PRECIO_TABACO;
                    cantidadCigarros = CANTIDAD_CIGARROS;
                }
                actualizaVista();
            }
        });
    }

    private void inicia() {
        lblCigarros = findViewById(R.id.lblCigarrosMain);
        lblDinero = findViewById(R.id.lblDineroMain);
        btnFumar = findViewById(R.id.btnFumar);


        actualizaVista();
    }

    private void actualizaVista() {
        lblCigarros.setText(String.valueOf(cantidadCigarros));
        lblDinero.setText(nf.format(dineroAhorrado));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("DINERO", dineroAhorrado);
        outState.putInt("CIGARROS", cantidadCigarros);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        dineroAhorrado = savedInstanceState.getInt("DINERO");
        cantidadCigarros = savedInstanceState.getInt("CIGARROS");

        actualizaVista();
    }
}