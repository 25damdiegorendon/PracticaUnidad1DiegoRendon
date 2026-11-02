package com.diegorendon.tiendadebolsos.gui;

import com.diegorendon.tiendadebolsos.base.Bandolera;
import com.diegorendon.tiendadebolsos.base.BolsoDeMano;
import com.diegorendon.tiendadebolsos.base.Bolsos;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class BolsosModelo {

    public ArrayList<Bolsos> listaBolsos;

    public BolsosModelo() throws TransformerConfigurationException {
        listaBolsos = new ArrayList<Bolsos>();
    }

    public ArrayList<Bolsos> obtenerBolsos() {
        return listaBolsos;
    }

    public void altaBolsoDeMano(String nombre, double precio, String color,
                                LocalDate fechaAdquisicion, String descripcion) {

        BolsoDeMano nuevoBolso = new BolsoDeMano(nombre, precio, color,
                fechaAdquisicion, "Bolso de mano", descripcion);
        listaBolsos.add(nuevoBolso);

    }

    public void altaBandolera(String nombre, double precio, String color,
                              LocalDate fechaAdquisicion, String descripcion) {

        Bandolera nuevaBandolera = new Bandolera(nombre, precio, color,
                fechaAdquisicion, "Bandolera", descripcion);

        listaBolsos.add(nuevaBandolera);
    }

    //exportar XML

    public void exportar(File fichero) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        //nodo raiz
        Element raiz = documento.createElement("Bolsos");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoBolso = null;
        Element nodoDatos = null;
        Text texto;

        for (Bolsos unBolso : listaBolsos) {

            if (unBolso instanceof BolsoDeMano) {
                nodoBolso = documento.createElement("BolsoDeMano");
            } else if (unBolso instanceof Bandolera) {
                nodoBolso = documento.createElement("Bandolera");
            }

            if (nodoBolso != null) {
                raiz.appendChild(nodoBolso);

                // Nombre
                nodoDatos = documento.createElement("Nombre");
                nodoBolso.appendChild(nodoDatos);

                texto = documento.createTextNode(unBolso.getNombre());
                nodoDatos.appendChild(texto);

                // Precio
                nodoDatos = documento.createElement("Precio");
                nodoBolso.appendChild(nodoDatos);

                texto = documento.createTextNode(String.valueOf(unBolso.getPrecio()));
                nodoDatos.appendChild(texto);

                // Color
                nodoDatos = documento.createElement("Color");
                nodoBolso.appendChild(nodoDatos);

                texto = documento.createTextNode(unBolso.getColor());
                nodoDatos.appendChild(texto);

                // Fecha de adquisición
                nodoDatos = documento.createElement("FechaAdquisicion");
                nodoBolso.appendChild(nodoDatos);

                texto = documento.createTextNode(unBolso.getFechaAdquisicion().toString());
                nodoDatos.appendChild(texto);

                // Tipo
                nodoDatos = documento.createElement("Tipo");
                nodoBolso.appendChild(nodoDatos);

                texto = documento.createTextNode(unBolso.getTipo());
                nodoDatos.appendChild(texto);

                // Descripción
                nodoDatos = documento.createElement("Descripcion");
                nodoBolso.appendChild(nodoDatos);

                texto = documento.createTextNode(unBolso.getDescripcion());
                nodoDatos.appendChild(texto);
            }
        }

        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);

        Transformer transformer =
                TransformerFactory.newInstance().newTransformer();

        transformer.transform(source, resultado);
    }

}