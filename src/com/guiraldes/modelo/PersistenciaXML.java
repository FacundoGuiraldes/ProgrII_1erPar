package com.guiraldes.modelo;

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

            Element fecha = doc.createElement("fecha");
            fecha.setTextContent(t.getFecha());
            turnoElem.appendChild(fecha);

            Element hora = doc.createElement("hora");
            hora.setTextContent(t.getHora());
            turnoElem.appendChild(hora);

            Element paciente = doc.createElement("paciente");
            Element pNombre = doc.createElement("nombre");
            pNombre.setTextContent(t.getPaciente().getNombre());
            Element pApellido = doc.createElement("apellido");
            pApellido.setTextContent(t.getPaciente().getApellido());
            Element pDni = doc.createElement("dni");
            pDni.setTextContent(t.getPaciente().getDni());
            paciente.appendChild(pNombre);
            paciente.appendChild(pApellido);
            paciente.appendChild(pDni);
            turnoElem.appendChild(paciente);

            Element doctor = doc.createElement("doctor");
            Element dNombre = doc.createElement("nombre");
            dNombre.setTextContent(t.getDoctor().getNombre());
            Element dApellido = doc.createElement("apellido");
            dApellido.setTextContent(t.getDoctor().getApellido());
            Element dMatricula = doc.createElement("matricula");
            dMatricula.setTextContent(t.getDoctor().getMatricula());
            doctor.appendChild(dNombre);
            doctor.appendChild(dApellido);
            doctor.appendChild(dMatricula);
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
            String fecha = turnoElem.getElementsByTagName("fecha").item(0).getTextContent();
            String hora = turnoElem.getElementsByTagName("hora").item(0).getTextContent();

            Element pacienteElem = (Element) turnoElem.getElementsByTagName("paciente").item(0);
            String pNombre = pacienteElem.getElementsByTagName("nombre").item(0).getTextContent();
            String pApellido = pacienteElem.getElementsByTagName("apellido").item(0).getTextContent();
            String pDni = pacienteElem.getElementsByTagName("dni").item(0).getTextContent();
            Paciente paciente = new Paciente(pNombre, pApellido, pDni, "", "", "", "");

            Element doctorElem = (Element) turnoElem.getElementsByTagName("doctor").item(0);
            String dNombre = doctorElem.getElementsByTagName("nombre").item(0).getTextContent();
            String dApellido = doctorElem.getElementsByTagName("apellido").item(0).getTextContent();
            String dMatricula = doctorElem.getElementsByTagName("matricula").item(0).getTextContent();
            Doctor doctor = new Doctor(dNombre, dApellido, "", "", "", dMatricula, "");

            Turno turno = new Turno(id, paciente, doctor, fecha, hora);
            turnos.add(turno);
        }

        return turnos;
    }
}