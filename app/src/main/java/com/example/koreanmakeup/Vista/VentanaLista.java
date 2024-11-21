package com.example.koreanmakeup.Vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.koreanmakeup.Controlador.ListaAdapter;
import com.example.koreanmakeup.Modelo.Productos;
import com.example.koreanmakeup.Modelo.Utilidades;
import com.example.koreanmakeup.R;

import java.util.ArrayList;
import java.util.List;




public class VentanaLista extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaAdapter listaAdapter;
    private int contextMenuPosition = -1; // Almacenar la posición de la lista
    private ImageButton btnDelete; // El botón de la papelera

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_lista);  // XML para la lista de productos

        // Inicializa los productos predefinidos (asegurando que la lista no esté vacía)
        Utilidades.inicializarProductosPredefinidos();

        // Inicializa el RecyclerView
        recyclerView = findViewById(R.id.recyclerViewProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener la lista de productos desde Utilidades
        List<Productos> productosList = Utilidades.obtenerProductos();

        // Verifica que la lista no esté vacía
        if (productosList.isEmpty()) {
            Log.d("Productos", "No se encontraron productos.");
            Toast.makeText(this, "No hay productos disponibles", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("Productos", "Cantidad de productos: " + productosList.size());
        }

        // Inicializa el adaptador y lo configura con el RecyclerView
        listaAdapter = new ListaAdapter(this, productosList);
        recyclerView.setAdapter(listaAdapter);

        // Configuración para la interacción con los elementos de la lista (clic corto y largo)
        listaAdapter.setOnItemClickListener(new ListaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Obtener el producto seleccionado
                Productos productoSeleccionado = productosList.get(position);

                // crea un intent para ir a la ventana de detalles
                Intent intent = new Intent(VentanaLista.this, VentanaDetalle.class);

                // Pasar los datos del producto seleccionado a la siguiente actividad
                intent.putExtra("nombre", productoSeleccionado.getNombre());
                intent.putExtra("descripcion", productoSeleccionado.getDescripcion());
                intent.putExtra("precio", productoSeleccionado.getPrecio()); // Agregamos el precio
                intent.putExtra("imagen", productoSeleccionado.getImagen());  // Asumiendo que tienes un recurso de imagen o ID de imagen.

                // Iniciar la actividad para mostrar los detalles del producto
                startActivity(intent);

                // Mostrar un mensaje con el nombre del producto seleccionado (opcional)
                Toast.makeText(VentanaLista.this, "Seleccionaste: " + productoSeleccionado.getNombre(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                // Manejo de clic largo para eliminar el producto
                contextMenuPosition = position;  // Guarda la posición

                // Hacer visible el ícono de la papelera
                btnDelete.setVisibility(View.VISIBLE);
            }
        });

        // Configuración del ImageButton para volver a MainActivity
        ImageButton btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a MainActivity
                Intent intent = new Intent(VentanaLista.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Inicializar el botón de la papelera
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setVisibility(View.GONE);  // Inicialmente oculto

        // Configurar el clic sobre la papelera
        btnDelete.setOnClickListener(v -> {
            // Confirmar eliminación con un AlertDialog
            new AlertDialog.Builder(VentanaLista.this)
                    .setTitle("Eliminar producto")
                    .setMessage("¿Estás seguro de que deseas eliminar este producto?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        // Eliminar el producto de la lista usando Utilidades
                        Utilidades.eliminarProducto(contextMenuPosition);
                        listaAdapter.notifyItemRemoved(contextMenuPosition);
                        Toast.makeText(VentanaLista.this, "Producto eliminado.", Toast.LENGTH_SHORT).show();
                        btnDelete.setVisibility(View.GONE);  // oculta la papelera después de eliminar
                        contextMenuPosition = -1;  // limpia la posición global
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        btnDelete.setVisibility(View.GONE);  // oculta la papelera si el usuario cancela
                    })
                    .show();
        });
    }
}