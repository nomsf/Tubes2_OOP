package ooploverz.tubes2_oop.dataStore;

import ooploverz.tubes2_oop.SettingsData.SettingsData;
import org.json.JSONArray;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJSONDatabase {
    public JSONArray read(String filename){
        JSONArray data = new JSONArray();
        try{
            String directoryPath = SettingsData.directoryPath;
            if (directoryPath == ""){
                directoryPath = "src/main/resources/ooploverz/tubes2_oop/Database";
            }
            String jsonString = new String(Files.readAllBytes(Paths.get(directoryPath+"/"+filename)));
            data = new JSONArray(jsonString);


        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
