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

    public JFrame frame;
    public DefaultListModel<Bolsos> dlmBolsos;

    public Ventana() {
        frame = new JFrame("BOLSOS MVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
        initComponents();
        }

    private void initComponents() {
        dlmBolsos = new DefaultListModel<Bolsos>();
    }
}