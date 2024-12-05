package com.example.jsonxmlconverter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JsonXmlConverter {

    public Document convertJsonToXml(String json, String xsdPath) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element root = document.createElement("root");
        document.appendChild(root);

        convertJsonNodeToXmlElement(jsonNode, root, document);

        return document;
    }

    private void convertJsonNodeToXmlElement(JsonNode jsonNode, Element parentElement, Document document) {
        if (jsonNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                Element element = document.createElement(field.getKey());
                parentElement.appendChild(element);
                convertJsonNodeToXmlElement(field.getValue(), element, document);
            }
        } else if (jsonNode.isArray()) {
            for (JsonNode arrayElement : jsonNode) {
                Element element = document.createElement("item");
                parentElement.appendChild(element);
                convertJsonNodeToXmlElement(arrayElement, element, document);
            }
        } else {
            parentElement.setTextContent(jsonNode.asText());
        }
    }

    public void saveXmlDocument(Document document, String filePath) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(filePath));
        transformer.transform(domSource, streamResult);
    }

    // Implement methods to handle field name, type, and length differences
    // Implement methods to handle data inversion
}
