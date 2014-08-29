package com.verba.vblz.newproject.buildscript;

import com.verba.vblz.helpers.ConsoleOutput;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sircodesalot on 14/8/29.
 */
public class CreateBuildScriptTask {
    private final Document xmlDoc;
    private final String author;
    private final String name;
    private final String version;

    public CreateBuildScriptTask(String author, String name, String version) {
        this.author = author;
        this.name = name;
        this.version = version;
        xmlDoc = createXmlDocument();
    }

    private Document createXmlDocument() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            return documentBuilder.newDocument();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);

            // Impossible to reach
            return null;
        }
    }

    public void perform() throws Exception {
        File file = new File("vblz-build.xml");
        FileOutputStream outputStream = new FileOutputStream(file);

        this.createBuildScript();

        ConsoleOutput.printlnOk("Creating vblz-build.xml script");
        ConsoleOutput.printlnOk("Created vblz-build.xml script");
        ConsoleOutput.println();

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(xmlDoc);
        StreamResult outstream = new StreamResult(outputStream);
        transformer.transform(source, outstream);

        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);

        outputStream.close();

        ConsoleOutput.println();
    }

    public void createBuildScript() throws Exception {
        generateConfiguration(xmlDoc);

        System.out.println();
    }

    void generateConfiguration(Document document) {
        Element configuration = document.createElement("configuration");
        document.appendChild(configuration);

        generateBuildInfo(configuration);
    }

    void generateBuildInfo(Element configuration) {
        configuration.appendChild(createElement("author", author));
        configuration.appendChild(createElement("name", name));
        configuration.appendChild(createElement("version", version));
    }

    Element createElement(String name) {
        return xmlDoc.createElement(name);
    }

    Element createElement(String name, String value) {
        Element element = xmlDoc.createElement(name);
        element.setTextContent(value);

        return element;
    }
}
