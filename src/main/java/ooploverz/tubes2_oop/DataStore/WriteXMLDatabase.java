package ooploverz.tubes2_oop.DataStore;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class WriteXMLDatabase {

    public static void writeToDatabase(JSONArray data, String filename) {
        filename = "test1.xml";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("employees");
            document.appendChild(rootElement);

            Element employeeElement1 = document.createElement("employee");
            rootElement.appendChild(employeeElement1);

            Element firstNameElement1 = document.createElement("firstName");
            firstNameElement1.appendChild(document.createTextNode("John"));
            employeeElement1.appendChild(firstNameElement1);

            Element lastNameElement1 = document.createElement("lastName");
            lastNameElement1.appendChild(document.createTextNode("Doe"));
            employeeElement1.appendChild(lastNameElement1);

            Element ageElement1 = document.createElement("age");
            ageElement1.appendChild(document.createTextNode("30"));
            employeeElement1.appendChild(ageElement1);

            Element employeeElement2 = document.createElement("employee");
//            employeeElement2.setAttribute("id", "2");
            rootElement.appendChild(employeeElement2);

            Element firstNameElement2 = document.createElement("firstName");
            firstNameElement2.appendChild(document.createTextNode("Jane"));
            employeeElement2.appendChild(firstNameElement2);

            Element lastNameElement2 = document.createElement("lastName");
            lastNameElement2.appendChild(document.createTextNode("Smith"));
            employeeElement2.appendChild(lastNameElement2);

            Element ageElement2 = document.createElement("age");
            ageElement2.appendChild(document.createTextNode("25"));
            employeeElement2.appendChild(ageElement2);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("src/main/resources/ooploverz/tubes2_oop/Database/"+ filename));
            transformer.transform(source, result);

            System.out.println("XML file saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
