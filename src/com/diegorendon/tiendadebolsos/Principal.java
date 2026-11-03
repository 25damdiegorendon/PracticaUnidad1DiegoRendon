package com.diegorendon.tiendadebolsos;

import com.diegorendon.tiendadebolsos.gui.BolsosModelo;
import com.diegorendon.tiendadebolsos.gui.BolsosControlador;
import com.diegorendon.tiendadebolsos.gui.Ventana;

public class Principal {

    /**
     * Inicia el programa y conecta el modelo,
     * la vista y el controlador.
     */

    public static void main(String[] args) {
        BolsosModelo modelo = new BolsosModelo();
        Ventana vista = new Ventana();
        new BolsosControlador(vista, modelo);
    }
}
