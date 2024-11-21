package com.example.koreanmakeup.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.koreanmakeup.R;

public class VentanaDetalle extends AppCompatActivity {

    private ImageView imagenProducto;
    private TextView nombreProducto, descripcionProducto, precioProducto;
    private ImageButton btnVolverDetalle; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_detalle);  // el xml

        // Referencias a las vistas
        imagenProducto = findViewById(R.id.imagenProducto);
        nombreProducto = findViewById(R.id.nombreProducto);
        descripcionProducto = findViewById(R.id.descripcionProducto);
        precioProducto = findViewById(R.id.precioProducto);
        btnVolverDetalle = findViewById(R.id.btnVolver);  // esta es la flecha <-

        // recibimos los datos del producto a través de un Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String descripcion = intent.getStringExtra("descripcion");
        double precio = intent.getDoubleExtra("precio", 0.0);
        int imagenResId = intent.getIntExtra("imagen", 0);  // 0 es el valor predeterminado en caso de que no haya imagen

        // le damos los valores
        nombreProducto.setText(nombre);
        descripcionProducto.setText(descripcion);
        precioProducto.setText(precio + "€" );
        imagenProducto.setImageResource(imagenResId);

        // este es el boton de la flecha
        btnVolverDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();  //Vuelve atras
            }
        });
    }
}