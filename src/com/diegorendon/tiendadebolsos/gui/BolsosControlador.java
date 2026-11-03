package com.diegorendon.tiendadebolsos.gui;
import com.diegorendon.tiendadebolsos.base.Bandolera;
import com.diegorendon.tiendadebolsos.base.BolsoDeMano;
import com.diegorendon.tiendadebolsos.base.Bolsos;
import com.diegorendon.tiendadebolsos.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

    /**
    * Controla las acciones de la ventana.
    */

public class BolsosControlador implements ActionListener, ListSelectionListener, WindowListener {

    private Ventana vista;
    private BolsosModelo modelo;
    private File ultimaRutaExportada;

        /**
         * Creación del constructor añado y añado los listener.
         * vista ventana del programa.
         * modelo modelo de los bolsos.
         */

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

        /**
         * Método hayCamposVacios.
         * Comprueba si hay campos vacíos.
         * true si hay algún campo vacío.
         */

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

        /**
         * Metodo limpiarCampos.
         * Limpia los campos del formulario.
         */

    private void limpiarCampos() {
        vista.nombreTxt.setText(null);
        vista.precioTxt.setText(null);
        vista.colorTxt.setText(null);
        vista.fechaAdquisicionDPicker.setText(null);
        vista.descripcionTxt.setText(null);
        vista.nombreTxt.requestFocus();
    }

        /**
         * Metodo refrescar.
         * Actualiza la lista de bolsos de la ventana.
         */

    public void refrescar() {
        vista.dlmBolsos.clear();

        for (Bolsos unBolso : modelo.obtenerBolsos()) {
            vista.dlmBolsos.addElement(unBolso);
        }
    }

        /**
         * Añade los listeners a los botones.
         * listener listener de las acciones.
         */

    private void addActionListener(ActionListener listener) {
        vista.nuevoButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.radioButton1.addActionListener(listener);
        vista.radioButton2.addActionListener(listener);
    }

        /**
         * Añade el listener a la lista de bolsos.
         * listener listener de selección.
         */

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.listaBolsos.addListSelectionListener(listener);
    }

        /**
         * Añade el listener a la ventana.
         * listener listener de la ventana.
         */

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

        /**
         * Carga la última ruta guardada.
         * IOException si ocurre un error al leer el fichero.
         */

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("bolsos.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
    }

        /**
         * Actualiza la última ruta exportada.
         * ultimaRutaExportada ruta del fichero.
         */

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

        /**
         * Guarda la configuración en un fichero.
         * IOException si ocurre un error al guardar.
         */

    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("bolsos.conf"), "Datos configuracion bolsos");
    }

        /**
         * Gestiona las acciones de los botones.
         */

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Los siguientes campos no pueden estar vacíos:\n"
                            + "Nombre\nPrecio\nColor\nFecha de adquisición\nDescripción");
                    break;
                }

                if (modelo.existeBolso(vista.nombreTxt.getText())) {
                    Util.mensajeError("Ya existe un bolso con ese nombre:\n"
                            + vista.nombreTxt.getText());
                    break;
                }

                if (vista.radioButton1.isSelected()) {
                    modelo.altaBolsoDeMano(
                            vista.nombreTxt.getText(),
                            Double.parseDouble(vista.precioTxt.getText()),
                            vista.colorTxt.getText(),
                            vista.fechaAdquisicionDPicker.getDate(),
                            vista.descripcionTxt.getText());
                } else if (vista.radioButton2.isSelected()) {
                    modelo.altaBandolera(
                            vista.nombreTxt.getText(),
                            Double.parseDouble(vista.precioTxt.getText()),
                            vista.colorTxt.getText(),
                            vista.fechaAdquisicionDPicker.getDate(),
                            vista.descripcionTxt.getText());
                } else {
                    Util.mensajeError("Debes seleccionar un tipo de bolso.");
                    break;
                }

                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(
                        ultimaRutaExportada, "Archivos XML", "xml");
                int opcion = selectorFichero.showOpenDialog(null);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(
                        ultimaRutaExportada, "Archivos XML", "xml");
                int opcion2 = selectorFichero2.showSaveDialog(null);
                if (opcion2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportar(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
          }
    }

        /**
         * Muestra los datos del bolso seleccionado.
         */

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            Bolsos bolsoSeleccionado = (Bolsos) vista.listaBolsos.getSelectedValue();

            if (bolsoSeleccionado != null) {
                vista.nombreTxt.setText(bolsoSeleccionado.getNombre());
                vista.precioTxt.setText(String.valueOf(bolsoSeleccionado.getPrecio()));
                vista.colorTxt.setText(bolsoSeleccionado.getColor());
                vista.fechaAdquisicionDPicker.setDate(bolsoSeleccionado.getFechaAdquisicion());
                vista.descripcionTxt.setText(bolsoSeleccionado.getDescripcion());

                if (bolsoSeleccionado instanceof BolsoDeMano) {
                    vista.radioButton1.doClick();
                } else if (bolsoSeleccionado instanceof Bandolera) {
                    vista.radioButton2.doClick();
                }
            }
        }
    }

        /**
         * Pregunta si se desea cerrar la ventana y guarda la configuración.
         */

    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("¿Desea cerrar la ventana?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

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


}
