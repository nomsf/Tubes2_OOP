package ooploverz.tubes2_oop.DataStore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteJSONDatabase {
    public static void writeToDatabase(JSONArray data, String filename) {
        try {
            // Menuliskan file
            try (FileWriter file = new FileWriter("src/main/resources/ooploverz/tubes2_oop/Database/"+filename)) {
                file.write(data.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
