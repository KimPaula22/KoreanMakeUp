package com.example.koreanmakeup.Modelo;

import com.example.koreanmakeup.Modelo.Productos;
import com.example.koreanmakeup.R;

import java.util.ArrayList;
import java.util.List;

public class Utilidades {

    // Lista estática de productos
    private static ArrayList<Productos> productosList = new ArrayList<>();

    // Método para agregar un producto
    public static void agregarProducto(Productos producto) {
        productosList.add(producto);
    }

    // Método para obtener la lista completa de productos
    public static ArrayList<Productos> obtenerProductos() {
        return productosList;
    }

    // Método para eliminar un producto de la lista por su posición
    public static void eliminarProducto(int position) {
        if (position >= 0 && position < productosList.size()) {
            productosList.remove(position);
        }
    }

    // Método para inicializar productos predefinidos
    public static void inicializarProductosPredefinidos() {
        if (productosList.isEmpty()) { // Si la lista está vacía, añade los productos
            productosList.add(new Productos("Maquillaje A", "Base líquida", R.drawable.imagen_producto1, 15.99));
            productosList.add(new Productos("Maquillaje B", "Sombra de ojos", R.drawable.imagen_producto2, 12.50));
            productosList.add(new Productos("Maquillaje C", "Delineador", R.drawable.imagen_producto3, 10.99));
            productosList.add(new Productos("Maquillaje D", "Pintalabios", R.drawable.imagen_producto4, 8.99));
            productosList.add(new Productos("Maquillaje E", "Máscara de pestañas", R.drawable.imagen_producto5, 9.99));
            productosList.add(new Productos("Maquillaje F", "Polvo compacto", R.drawable.imagen_producto6, 14.99));
            productosList.add(new Productos("Maquillaje G", "Corrector", R.drawable.imagen_producto7, 7.50));
            productosList.add(new Productos("Maquillaje H", "Iluminador", R.drawable.imagen_producto8, 18.99));
            productosList.add(new Productos("Maquillaje I", "Blush", R.drawable.imagen_producto9, 13.99));
            productosList.add(new Productos("Maquillaje J", "Paleta de sombras", R.drawable.imagen_producto10, 22.50));
        }
    }
}