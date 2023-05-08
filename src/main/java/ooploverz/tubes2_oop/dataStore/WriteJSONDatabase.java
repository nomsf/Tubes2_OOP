package ooploverz.tubes2_oop.dataStore;

import ooploverz.tubes2_oop.SettingsData.SettingsData;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;

public class WriteJSONDatabase {
    public static void writeToDatabase(JSONArray data, String filename) {
        try {
            // Menuliskan file
            String directoryPath = SettingsData.directoryPath;
            if (directoryPath == ""){
                directoryPath = "src/main/resources/ooploverz/tubes2_oop/Database";
            }
            try (FileWriter file = new FileWriter(directoryPath+"/"+filename)) {
                file.write(data.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
