package com.diegorendon.tiendadebolsos.base;

import java.time.LocalDate;

public class BolsoDeMano extends Bolsos {

    public BolsoDeMano() {
        super();
    }

    public BolsoDeMano(String nombre, double precio, String color,
                       LocalDate fechaAdquisicion, String tipo,
                       String descripcion) {

        super(nombre, precio, color, fechaAdquisicion, tipo, descripcion);
    }

    @Override
    public String toString() {
        return "Bolso de mano: " + getNombre() + " "
                + getPrecio() + " " + getColor();
    }
}
