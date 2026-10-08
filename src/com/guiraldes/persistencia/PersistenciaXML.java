package com.guiraldes.persistencia;

import com.guiraldes.modelo.Doctor;
import com.guiraldes.modelo.EstadoTurno;
import com.guiraldes.modelo.Paciente;
import com.guiraldes.modelo.Turno;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaXML {

    public static void guardarTurnos(List<Turno> turnos, String archivo) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element raiz = doc.createElement("agenda");
        doc.appendChild(raiz);

        for (Turno t : turnos) {
            Element turnoElem = doc.createElement("turno");
            turnoElem.setAttribute("id", String.valueOf(t.getId()));
            agregarElemento(doc, turnoElem, "fecha", t.getFecha());
            agregarElemento(doc, turnoElem, "hora", t.getHora());
            agregarElemento(doc, turnoElem, "estado", String.valueOf(t.getEstado()));

            Paciente p = t.getPaciente();
            Element paciente = doc.createElement("paciente");
            agregarElemento(doc, paciente, "nombre", p.getNombre());
            agregarElemento(doc, paciente, "apellido", p.getApellido());
            agregarElemento(doc, paciente, "dni", p.getDni());
            agregarElemento(doc, paciente, "email", p.getEmail());
            agregarElemento(doc, paciente, "obraSocial", p.getObraSocial());
            agregarElemento(doc, paciente, "numeroAfiliado", p.getNumeroAfiliado());
            turnoElem.appendChild(paciente);

            Doctor d = t.getDoctor();
            Element doctor = doc.createElement("doctor");
            agregarElemento(doc, doctor, "nombre", d.getNombre());
            agregarElemento(doc, doctor, "apellido", d.getApellido());
            agregarElemento(doc, doctor, "dni", d.getDni());
            agregarElemento(doc, doctor, "email", d.getEmail());
            agregarElemento(doc, doctor, "matricula", d.getMatricula());
            agregarElemento(doc, doctor, "especialidad", d.getEspecialidad());
            turnoElem.appendChild(doctor);

            raiz.appendChild(turnoElem);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(archivo));
        transformer.transform(source, result);
    }

    public static List<Turno> cargarTurnos(String archivo) throws Exception {
        List<Turno> turnos = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(archivo));
        doc.getDocumentElement().normalize();

        NodeList turnoNodes = doc.getElementsByTagName("turno");

        for (int i = 0; i < turnoNodes.getLength(); i++) {
            Element turnoElem = (Element) turnoNodes.item(i);

            int id = Integer.parseInt(turnoElem.getAttribute("id"));
            String fecha = leerTexto(turnoElem, "fecha");
            String hora = leerTexto(turnoElem, "hora");
            String textoEstado = leerTexto(turnoElem, "estado");
            EstadoTurno estado = textoEstado.isEmpty() ? EstadoTurno.PENDIENTE : EstadoTurno.valueOf(textoEstado);

            Element pacienteElem = (Element) turnoElem.getElementsByTagName("paciente").item(0);
            Paciente paciente = new Paciente(leerTexto(pacienteElem, "nombre"), leerTexto(pacienteElem, "apellido"),
                    leerTexto(pacienteElem, "dni"), leerTexto(pacienteElem, "email"), "",
                    leerTexto(pacienteElem, "obraSocial"), leerTexto(pacienteElem, "numeroAfiliado"));

            Element doctorElem = (Element) turnoElem.getElementsByTagName("doctor").item(0);
            Doctor doctor = new Doctor(leerTexto(doctorElem, "nombre"), leerTexto(doctorElem, "apellido"),
                    leerTexto(doctorElem, "dni"), leerTexto(doctorElem, "email"), "",
                    leerTexto(doctorElem, "matricula"), leerTexto(doctorElem, "especialidad"));

            turnos.add(new Turno(id, paciente, doctor, fecha, hora, estado));
        }

        return turnos;
    }

    private static void agregarElemento(Document doc, Element padre, String etiqueta, String valor) {
        Element elemento = doc.createElement(etiqueta);
        elemento.setTextContent(valor);
        padre.appendChild(elemento);
    }

    private static String leerTexto(Element padre, String etiqueta) {
        NodeList nodos = padre.getElementsByTagName(etiqueta);
        if (nodos.getLength() == 0) {
            return "";
        }
        return nodos.item(0).getTextContent();
    }
}