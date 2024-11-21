package com.example.koreanmakeup.Controlador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.koreanmakeup.Modelo.Productos;
import com.example.koreanmakeup.R;
import com.example.koreanmakeup.Vista.VentanaDetalle;

import java.util.ArrayList;
import java.util.List;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ProductoViewHolder> {

    private Context context;
    private List<Productos> productosList;
    private OnItemClickListener onItemClickListener;

    // Constructor para inicializar el contexto y la lista de productos
    public ListaAdapter(Context context, List<Productos> productosList) {
        this.context = context;
        this.productosList = productosList;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla el layout de cada ítem
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        // Obtén el producto actual
        Productos producto = productosList.get(position);
        holder.nombreTextView.setText(producto.getNombre());
        holder.descripcionTextView.setText(producto.getDescripcion());
        holder.imagenImageView.setImageResource(producto.getImagen());

        // Establecer la acción de clic corto
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, position);
            }
        });

        // Establecer la acción de clic largo
        holder.itemView.setOnLongClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onLongItemClick(v, position);
            }
            return true; // Para que se reconozca el clic largo
        });
    }

    @Override
    public int getItemCount() {
        return productosList.size();
    }

    // ViewHolder para cada ítem de la lista
    public static class ProductoViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreTextView, descripcionTextView;
        public ImageView imagenImageView;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreProducto);
            descripcionTextView = itemView.findViewById(R.id.descripcionProducto);
            imagenImageView = itemView.findViewById(R.id.imagenProducto);
        }
    }

    // Interfaz para manejar los clics
    public interface OnItemClickListener {
        void onItemClick(View view, int position);  // Clic corto
        void onLongItemClick(View view, int position);  // Clic largo
    }

    // Método para establecer el listener de clic
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    // Método para agregar un producto a la lista y notificar al adaptador
    public void addProducto(Productos producto) {
        this.productosList.add(producto);
        notifyItemInserted(productosList.size() - 1);  // Notifica que un nuevo item se ha agregado
    }
}