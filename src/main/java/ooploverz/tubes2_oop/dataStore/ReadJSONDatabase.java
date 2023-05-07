package ooploverz.tubes2_oop.dataStore;

import org.json.JSONArray;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJSONDatabase {
    public JSONArray read(String filename){
        JSONArray data = new JSONArray();
        try{
            String jsonString = new String(Files.readAllBytes(Paths.get("src/main/resources/ooploverz/tubes2_oop/Database/"+filename)));
            data = new JSONArray(jsonString);


        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
