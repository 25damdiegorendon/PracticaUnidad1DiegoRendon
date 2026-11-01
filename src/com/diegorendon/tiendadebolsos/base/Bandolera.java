package com.diegorendon.tiendadebolsos.base;

import java.time.LocalDate;

public class Bandolera extends Bolsos{

    public Bandolera() {
        super();
    }

    public Bandolera(String nombre, double precio, String color,
                     LocalDate fechaAdquisicion, String tipo,
                     String descripcion) {

        super(nombre, precio, color, fechaAdquisicion, tipo, descripcion);
    }

    @Override
    public String toString() {
        return "Bandolera: " + getNombre() + " "
                + getPrecio() + " " + getColor();
    }

}
