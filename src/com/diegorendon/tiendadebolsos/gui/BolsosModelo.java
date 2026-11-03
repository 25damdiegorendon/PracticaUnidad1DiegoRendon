package com.diegorendon.tiendadebolsos.gui;

import com.diegorendon.tiendadebolsos.base.Bandolera;
import com.diegorendon.tiendadebolsos.base.BolsoDeMano;
import com.diegorendon.tiendadebolsos.base.Bolsos;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BolsosModelo {

    /** Lista de bolsos. */

    public ArrayList<Bolsos> listaBolsos;

    /**
     * Constructor que inicializa la lista.
     */

    public BolsosModelo() {
        listaBolsos = new ArrayList<Bolsos>();
    }

    /**
     * Devuelve la lista de bolsos.
     */

    public ArrayList<Bolsos> obtenerBolsos() {
        return listaBolsos;
    }

    /**
     * Metodo para annadir bolso de mano.
     * Añade un bolso de mano a la lista.
     *  nombre Nombre del bolso.
     *  precio Precio del bolso.
     *  color Color del bolso.
     *  fechaAdquisicion Fecha de adquisición.
     *  descripcion Descripción del bolso.
     */

    public void altaBolsoDeMano(String nombre, double precio, String color,
                                LocalDate fechaAdquisicion, String descripcion) {

        BolsoDeMano nuevoBolso = new BolsoDeMano(nombre, precio, color,
                fechaAdquisicion, "Bolso de mano", descripcion);
        listaBolsos.add(nuevoBolso);

    }

    /**
     * Metodo para annadir bandolera.
     * Añade un bolso de mano a la lista.
     *  nombre Nombre del bolso.
     *  precio Precio del bolso.
     *  color Color del bolso.
     *  fechaAdquisicion Fecha de adquisición.
     *  descripcion Descripción del bolso.
     */

    public void altaBandolera(String nombre, double precio, String color,
                              LocalDate fechaAdquisicion, String descripcion) {

        Bandolera nuevaBandolera = new Bandolera(nombre, precio, color,
                fechaAdquisicion, "Bandolera", descripcion);

        listaBolsos.add(nuevaBandolera);
    }

     /**
      * meotodo existeBolso
      * Comprueba si existe un bolso con ese nombre.
      * Devuelve true si existe, false si no existe.
      */

    public boolean existeBolso(String nombre) {
        for (Bolsos unBolso : listaBolsos) {
            if (unBolso.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo exportar
     * Exporta la lista de bolsos a un archivo XML.
     * fichero Archivo donde se guardan los datos.
     * ParserConfigurationException Si hay un error al crear el documento.
     * TransformerException Si hay un error al guardar el XML.
     */

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

    /**
     * Metodo importar.
     * Importa los bolsos desde un archivo XML.
     *  fichero Archivo XML que contiene los datos.
     * ParserConfigurationException Si hay un error al crear el documento.
     * IOException Si hay un error al leer el archivo.
     * SAXException Si el XML tiene un formato incorrecto.
     */

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaBolsos = new ArrayList<Bolsos>();
        BolsoDeMano nuevoBolso = null;
        Bandolera nuevaBandolera = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);


        NodeList listaElementos = documento.getElementsByTagName("Bolsos").item(0).getChildNodes();
        for (int i = 0; i < listaElementos.getLength(); i++) {
            if (listaElementos.item(i) instanceof Element) {
                Element nodoBolso = (Element) listaElementos.item(i);
                if (nodoBolso.getTagName().equals("BolsoDeMano")) {
                    nuevoBolso = new BolsoDeMano();
                    nuevoBolso.setNombre(nodoBolso.getChildNodes().item(0).getTextContent());
                    nuevoBolso.setPrecio(Double.parseDouble(nodoBolso.getChildNodes().item(1).getTextContent()));
                    nuevoBolso.setColor(nodoBolso.getChildNodes().item(2).getTextContent());
                    nuevoBolso.setFechaAdquisicion(LocalDate.parse(nodoBolso.getChildNodes().item(3).getTextContent()));
                    nuevoBolso.setTipo(nodoBolso.getChildNodes().item(4).getTextContent());
                    nuevoBolso.setDescripcion(nodoBolso.getChildNodes().item(5).getTextContent());
                    listaBolsos.add(nuevoBolso);

                } else if (nodoBolso.getTagName().equals("Bandolera")) {
                    nuevaBandolera = new Bandolera();
                    nuevaBandolera.setNombre(nodoBolso.getChildNodes().item(0).getTextContent());
                    nuevaBandolera.setPrecio(Double.parseDouble(nodoBolso.getChildNodes().item(1).getTextContent()));
                    nuevaBandolera.setColor(nodoBolso.getChildNodes().item(2).getTextContent());
                    nuevaBandolera.setFechaAdquisicion(LocalDate.parse(nodoBolso.getChildNodes().item(3).getTextContent()));
                    nuevaBandolera.setTipo(nodoBolso.getChildNodes().item(4).getTextContent());
                    nuevaBandolera.setDescripcion(nodoBolso.getChildNodes().item(5).getTextContent());
                    listaBolsos.add(nuevaBandolera);

                }
            }
        }
    }
}