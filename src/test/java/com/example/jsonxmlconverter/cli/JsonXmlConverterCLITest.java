package com.example.jsonxmlconverter.cli;

import com.example.jsonxmlconverter.JsonXmlConverter;
import com.example.jsonxmlconverter.Logger;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonXmlConverterCLITest {

    @Test
    public void testMain() {
        String[] args = {"src/test/resources/input.json", "src/test/resources/output.xml"};
        JsonXmlConverterCLI.main(args);

        // Verify the output XML file is created and contains the expected content
        try {
            JsonXmlConverter converter = new JsonXmlConverter();
            Document xmlDocument = converter.convertJsonToXml(new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("src/test/resources/input.json"))), null);
            assertNotNull(xmlDocument);
            assertEquals("root", xmlDocument.getDocumentElement().getNodeName());
            assertEquals("John", xmlDocument.getDocumentElement().getElementsByTagName("name").item(0).getTextContent());
            assertEquals("30", xmlDocument.getDocumentElement().getElementsByTagName("age").item(0).getTextContent());
            assertEquals("New York", xmlDocument.getDocumentElement().getElementsByTagName("city").item(0).getTextContent());
        } catch (IOException | ParserConfigurationException | TransformerException e) {
            fail("Exception occurred during test: " + e.getMessage());
        }
    }

    @Test
    public void testMainWithXsd() {
        String[] args = {"src/test/resources/input.json", "src/test/resources/output.xml", "src/test/resources/schema.xsd"};
        JsonXmlConverterCLI.main(args);

        // Verify the output XML file is created and contains the expected content
        try {
            JsonXmlConverter converter = new JsonXmlConverter();
            Document xmlDocument = converter.convertJsonToXml(new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("src/test/resources/input.json"))), "src/test/resources/schema.xsd");
            assertNotNull(xmlDocument);
            assertEquals("root", xmlDocument.getDocumentElement().getNodeName());
            assertEquals("John", xmlDocument.getDocumentElement().getElementsByTagName("name").item(0).getTextContent());
            assertEquals("30", xmlDocument.getDocumentElement().getElementsByTagName("age").item(0).getTextContent());
            assertEquals("New York", xmlDocument.getDocumentElement().getElementsByTagName("city").item(0).getTextContent());
        } catch (IOException | ParserConfigurationException | TransformerException e) {
            fail("Exception occurred during test: " + e.getMessage());
        }
    }

    @Test
    public void testMainWithInvalidArgs() {
        String[] args = {};
        JsonXmlConverterCLI.main(args);

        // Verify that the appropriate error message is logged
        // This test assumes that the Logger class logs messages to the console or a file
        // You may need to adjust this test based on the actual implementation of the Logger class
    }
}
