package com.example.jsonxmlconverter;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonXmlConverterTest {

    @Test
    public void testConvertJsonToXml() throws IOException, ParserConfigurationException, TransformerException {
        String json = "{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}";
        JsonXmlConverter converter = new JsonXmlConverter();
        Document xmlDocument = converter.convertJsonToXml(json, null);

        assertNotNull(xmlDocument);
        assertEquals("root", xmlDocument.getDocumentElement().getNodeName());
        assertEquals("John", xmlDocument.getDocumentElement().getElementsByTagName("name").item(0).getTextContent());
        assertEquals("30", xmlDocument.getDocumentElement().getElementsByTagName("age").item(0).getTextContent());
        assertEquals("New York", xmlDocument.getDocumentElement().getElementsByTagName("city").item(0).getTextContent());
    }

    @Test
    public void testFieldDifferences() throws IOException, ParserConfigurationException, TransformerException {
        String json = "{\"fullName\":\"John Doe\", \"yearsOld\":30, \"location\":\"New York\"}";
        JsonXmlConverter converter = new JsonXmlConverter();
        Document xmlDocument = converter.convertJsonToXml(json, null);

        assertNotNull(xmlDocument);
        assertEquals("root", xmlDocument.getDocumentElement().getNodeName());
        assertEquals("John Doe", xmlDocument.getDocumentElement().getElementsByTagName("fullName").item(0).getTextContent());
        assertEquals("30", xmlDocument.getDocumentElement().getElementsByTagName("yearsOld").item(0).getTextContent());
        assertEquals("New York", xmlDocument.getDocumentElement().getElementsByTagName("location").item(0).getTextContent());
    }

    @Test
    public void testDataInversion() throws IOException, ParserConfigurationException, TransformerException {
        String json = "{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}";
        JsonXmlConverter converter = new JsonXmlConverter();
        Document xmlDocument = converter.convertJsonToXml(json, null);

        assertNotNull(xmlDocument);
        assertEquals("root", xmlDocument.getDocumentElement().getNodeName());
        assertEquals("John", xmlDocument.getDocumentElement().getElementsByTagName("name").item(0).getTextContent());
        assertEquals("30", xmlDocument.getDocumentElement().getElementsByTagName("age").item(0).getTextContent());
        assertEquals("New York", xmlDocument.getDocumentElement().getElementsByTagName("city").item(0).getTextContent());
    }
}
