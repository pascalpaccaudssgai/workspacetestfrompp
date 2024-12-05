package com.example.jsonxmlconverter.cli;

import com.example.jsonxmlconverter.JsonXmlConverter;
import com.example.jsonxmlconverter.Logger;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class JsonXmlConverterCLI {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar JsonXmlConverterCLI.jar <input-json-file> <output-xml-file> [xsd-file]");
            return;
        }

        String inputJsonFile = args[0];
        String outputXmlFile = args[1];
        String xsdFile = args.length > 2 ? args[2] : null;

        try {
            String json = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(inputJsonFile)));
            JsonXmlConverter converter = new JsonXmlConverter();
            Document xmlDocument = converter.convertJsonToXml(json, xsdFile);
            converter.saveXmlDocument(xmlDocument, outputXmlFile);
            Logger.info("Conversion successful. Output saved to " + outputXmlFile);
        } catch (IOException | ParserConfigurationException | TransformerException e) {
            Logger.error("Error during conversion: " + e.getMessage());
        }
    }
}
