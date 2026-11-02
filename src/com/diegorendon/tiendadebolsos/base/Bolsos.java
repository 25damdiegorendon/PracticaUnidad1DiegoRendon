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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
