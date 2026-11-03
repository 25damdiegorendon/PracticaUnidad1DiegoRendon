package com.diegorendon.tiendadebolsos.gui;

import com.diegorendon.tiendadebolsos.base.Bolsos;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Ventana {
    private JPanel panel1;
    public  JTextField nombreTxt;
    public JTextField precioTxt;
    public JRadioButton radioButton2;
    public JRadioButton radioButton1;
    public JTextField colorTxt;
    public JButton nuevoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public DatePicker fechaAdquisicionDPicker;
    public JTextArea descripcionTxt;
    public JList listaBolsos;

    public JFrame frame;
    public DefaultListModel<Bolsos> dlmBolsos;

    /**
     * Creo la ventana y la muestro.
     */

    public Ventana() {
        frame = new JFrame("BOLSOS MVC");
        frame.setIconImage(new ImageIcon(getClass().getResource("/com/diegorendon/tiendadebolsos/resources/louis-vuitton.png")).getImage());
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
        initComponents();
        }

    /**
     * Inicializa los componentes de la ventana.
     */

    private void initComponents() {
        dlmBolsos = new DefaultListModel<Bolsos>();
        listaBolsos.setModel(dlmBolsos);


    }
}