package com.vds;

import com.vds.ueconfig.ObjectFactory;
import com.vds.ueconfig.Root;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class Main {

    public static void main(String[] args) throws JAXBException, SAXException {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("ueconfig.xsd"));

        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(schema);
        try {

        Root ueconfig = (Root) unmarshaller.unmarshal(new File( args[0]));
        System.out.println("Die ueconfig konnte erfolgreich validiert werden");
		
        // ueconfig ist nun ein Java Objektbaum, der einfach angesprochen werden kann
        System.out.println(ueconfig.getVersion().getTimestamp());
        System.out.println(ueconfig.getSession().getUUID());
        System.out.println(ueconfig.getSession().getConnectionType());
        System.out.println(ueconfig.getSession().getManufacturer());
        System.out.println(ueconfig.getParameter().getInfo().getObjectName1());
        System.out.println(ueconfig.getParameter().getDestination().get(1).getIdentificator());

        // Aus einem Java Objektbaum kann wieder ein XML File erstellt werden
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("jaxb.formatted.output", true); // Die Ausgabe soll schön formatiert sein
        marshaller.marshal(ueconfig, new File("bsp/ueconfigconfig_generiert.xml"));
        System.out.println("Die ueconfig konnte erfolgreich wieder exportiert werden");

        } catch ( Exception ex) {
            System.out.println("Fehler beim Valideren der ueconfig: \n" + ex.getCause());
        }
    }
}
