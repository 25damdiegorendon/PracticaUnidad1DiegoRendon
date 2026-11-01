package com.diegorendon.tiendadebolsos.base;

import java.time.LocalDate;

public abstract class Bolsos {
    private String nombre;
    private double precio;
    private String color;
    private LocalDate fechaAdquisicion;
    private String tipo;
    private String descripcion;

    public Bolsos() {

    }

    public Bolsos(String nombre, double precio, String color, LocalDate fechaAdquisicion, String tipo, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.color = color;
        this.fechaAdquisicion = fechaAdquisicion;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getColor() {
        return color;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
