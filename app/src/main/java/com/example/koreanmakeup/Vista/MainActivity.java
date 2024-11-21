package com.example.koreanmakeup.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.koreanmakeup.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // El layout principal

        // Referencias a las vistas
        ImageView logo = findViewById(R.id.logoImageView); // ImageView para el logo
        Button btnRegistro = findViewById(R.id.btnRegistro); // Botón para registro
        Button btnLista = findViewById(R.id.btnLista); // Botón para lista
        Button btnRegistroUsuario = findViewById(R.id.btnRegistroUsuario);

        // Acción del botón Registro: abre la actividad VentanaRegistro
        btnRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VentanaRegistro.class);
            startActivity(intent);
        });

        // Acción del botón Lista: abre la actividad VentanaLista
        btnLista.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VentanaLista.class);
            startActivity(intent);
        });

        // Acción del botón Registro Usuario: abre la actividad VentanaRegistroUsuario
        btnRegistroUsuario.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistroUsuario.class);
            startActivity(intent);
        });
    }
}