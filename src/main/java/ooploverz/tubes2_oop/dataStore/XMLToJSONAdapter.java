package ooploverz.tubes2_oop.dataStore;

import org.json.JSONArray;

public class XMLToJSONAdapter implements IReadJSON{
    private ReadXMLDatabase readXMLDatabase = new ReadXMLDatabase();
    public JSONArray read(String filename){
        JSONArray result = new JSONArray();
        try{
//            XmlMapper xmlMapper = new XmlMapper();
//            JsonNode rootNode = xmlMapper.readTree(document);
//            String jsonString = xmlMapper.writeValueAsString(rootNode);
//            readXMLDatabase.read(filename);

        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
