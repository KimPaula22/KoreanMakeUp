package com.example.koreanmakeup.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.koreanmakeup.Modelo.Productos;
import com.example.koreanmakeup.Modelo.Utilidades;
import com.example.koreanmakeup.R;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VentanaRegistro extends AppCompatActivity {

    private EditText etNombre, etDescripcion, etPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_registro);

        // Referencias a las vistas
        etNombre = findViewById(R.id.etNombreProducto);
        etDescripcion = findViewById(R.id.etDescripcionProducto);
        etPrecio = findViewById(R.id.etPrecioProducto);
        Button btnRegistrar = findViewById(R.id.btnRegistrarProducto);
        ImageButton btnVolver = findViewById(R.id.btnVolver);

        // Acción del botón Registrar
        btnRegistrar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String descripcion = etDescripcion.getText().toString();
            String precioStr = etPrecio.getText().toString();

            if (nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
                Toast.makeText(VentanaRegistro.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    double precio = Double.parseDouble(precioStr);
                    if (precio <= 0) {
                        Toast.makeText(VentanaRegistro.this, "El precio debe ser mayor a 0.", Toast.LENGTH_SHORT).show();
                    } else {
                        Productos nuevoProducto = new Productos(nombre, descripcion, R.drawable.default_image, precio);

                        // Añadir el producto a la lista compartida
                        Utilidades.agregarProducto(nuevoProducto);

                        // Mostrar mensaje de confirmación
                        Toast.makeText(VentanaRegistro.this, "Producto agregado correctamente.", Toast.LENGTH_SHORT).show();

                        // Regresar automáticamente a MainActivity
                        finish();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(VentanaRegistro.this, "Por favor ingresa un precio válido.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Acción del botón Volver
        btnVolver.setOnClickListener(v -> {
            // Regresar a MainActivity
            Intent intent = new Intent(VentanaRegistro.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finaliza la actividad actual
        });
    }
    }

