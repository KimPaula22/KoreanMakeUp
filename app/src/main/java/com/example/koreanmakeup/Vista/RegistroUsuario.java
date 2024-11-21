package com.example.koreanmakeup.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.koreanmakeup.R;

public class RegistroUsuario extends AppCompatActivity {

    private EditText etNombre, etEmail, etEdad;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_registro_usuario);

        //  elementos del layout
        etNombre = findViewById(R.id.etNombreUsuario);
        etEmail = findViewById(R.id.etGmailUsuario);
        etEdad = findViewById(R.id.etEdadUsuario);
        btnRegistrar = findViewById(R.id.btnRegistroUsuario);
        ImageButton btnVolver = findViewById(R.id.btnVolver);

        // Listener del botón Registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        btnVolver.setOnClickListener(v -> {
            // Regresar a MainActivity
            Intent intent = new Intent(RegistroUsuario.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }


    private void registrarUsuario() {
        String nombre = etNombre.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String edadStr = etEdad.getText().toString().trim();

        // Validación de campos
        if (nombre.isEmpty() || email.isEmpty() || edadStr.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validación de la edad
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "La edad debe ser un número válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (edad < 18) {
            Toast.makeText(this, "Debes ser mayor de 18 años para registrarte", Toast.LENGTH_SHORT).show();
            return;
        }

        // Usuario registrado correctamente
        Toast.makeText(this, "Registrado exitosamente", Toast.LENGTH_SHORT).show();

        // Cierra la ventana de registro
        finish();
    }
}