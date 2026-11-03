package com.diegorendon.tiendadebolsos.util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Util {

    /**
     * Muestra un mensaje de error.
     */

    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null,
                mensaje,"Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de confirmación.
     */

    public static int mensajeConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null, mensaje,
                titulo, JOptionPane.YES_NO_OPTION);
    }

    /**
     * Crea un selector de archivos.
     * rutaDefecto carpeta inicial.
     * descripción del tipo de archivo.
     * extension extensión permitida.
     * selector de archivos creado.
     */

    public static JFileChooser crearSelectorFichero(File rutaDefecto, String tipoArchivos, String extension) {
        JFileChooser selectorFichero = new JFileChooser();
        if (rutaDefecto!=null) {
            selectorFichero.setCurrentDirectory(rutaDefecto);
        }
        if (extension!=null) {
            FileNameExtensionFilter filtro = new
                    FileNameExtensionFilter(tipoArchivos,extension);
            selectorFichero.setFileFilter(filtro);
        }
        return selectorFichero;
    }
}
