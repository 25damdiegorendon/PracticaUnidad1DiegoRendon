package com.diegorendon.tiendadebolsos.gui;

import com.diegorendon.tiendadebolsos.base.Bolsos;
import com.diegorendon.tiendadebolsos.util.Util;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

public class BolsosControlador implements ActionListener, ListSelectionListener, WindowListener {

    private Ventana vista;
    private BolsosModelo modelo;
    private File ultimaRutaExportada;

    public BolsosControlador(Ventana vista, BolsosModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No existe fichero de configuracion " + e.getMessage());
        }

        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
    }



    private boolean hayCamposVacios() {
        if (vista.nombreTxt.getText().isEmpty()
                || vista.precioTxt.getText().isEmpty()
                || vista.colorTxt.getText().isEmpty()
                || vista.fechaAdquisicionDPicker.getText().isEmpty()
                || vista.descripcionTxt.getText().trim().isEmpty()) {
            return true;
        }

        return false;
    }

    private void limpiarCampos() {
        vista.nombreTxt.setText(null);
        vista.precioTxt.setText(null);
        vista.colorTxt.setText(null);
        vista.fechaAdquisicionDPicker.setText(null);
        vista.descripcionTxt.setText(null);
        vista.nombreTxt.requestFocus();
    }

    public void refrescar() {
        vista.dlmBolsos.clear();

        for (Bolsos unBolso : modelo.obtenerBolsos()) {
            vista.dlmBolsos.addElement(unBolso);
        }
    }

    private void addActionListener(BolsosControlador bolsosControlador) {
        vista.nuevoButton.addActionListener(this);
        vista.exportarButton.addActionListener(this);
        vista.importarButton.addActionListener(this);
    }

    private void addListSelectionListener(BolsosControlador bolsosControlador) {
        vista.listaBolsos.addListSelectionListener(this);
    }

    private void addWindowListener(BolsosControlador bolsosControlador) {
        vista.frame.addWindowListener(this);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("bolsos.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("bolsos.conf"), "Datos configuracion bolsos");
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
