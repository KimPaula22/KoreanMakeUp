package com.example.koreanmakeup.Modelo;

import java.io.Serializable;

public class Productos implements Serializable {

    private String nombre;
    private String descripcion;
    private int imagen;
    private double precio;

    public Productos(String nombre, String descripcion, int imagen, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio= precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagen() {
        return imagen;
    }
    public double getPrecio() {
        return precio;
    }
}