package ooploverz.tubes2_oop.dataStore;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import ooploverz.tubes2_oop.SettingsData.SettingsData;
import org.w3c.dom.Document;

import java.io.File;

public class ReadXMLDatabase {
    public Document read(String filename) throws Exception{
        String directoryPath = SettingsData.directoryPath;
        if (directoryPath == ""){
            directoryPath = "src/main/resources/ooploverz/tubes2_oop/Database";
        }
        File file = new File(directoryPath +"/" + filename);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        return document;
    }
}