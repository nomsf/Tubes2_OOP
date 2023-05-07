package ooploverz.tubes2_oop.DataStore;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.json.JSONArray;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLDatabase {
    public static JSONArray readFromDatabase(String filename){
        JSONArray data = new JSONArray();
        try {
            File file = new File("src/main/resources/ooploverz/tubes2_oop/Database/" + filename);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            document.getDocumentElement().normalize();

            System.out.println("Root element: " + document.getDocumentElement().getNodeName());

            NodeList nodeList = document.getElementsByTagName("employee");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getAttribute("id");
                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String age = element.getElementsByTagName("age").item(0).getTextContent();

                    System.out.println("Employee id: " + id);
                    System.out.println("First name: " + firstName);
                    System.out.println("Last name: " + lastName);
                    System.out.println("Age: " + age);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}