package ooploverz.tubes2_oop.DataStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadDatabase {
    public static JSONArray readFromDatabase(String filename){
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
