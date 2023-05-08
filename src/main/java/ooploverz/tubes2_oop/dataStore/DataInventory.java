package ooploverz.tubes2_oop.dataStore;

import ooploverz.tubes2_oop.SettingsData.SettingsData;
import org.json.JSONArray;

public class DataInventory {
    public static JSONArray getData(){
        String formatFile = SettingsData.dataFormat;
        if (formatFile.equals("")){
            formatFile = "json";
        }
        JSONArray result = new JSONArray();
        if (formatFile.equals("json")){
            ReadJSONDatabase reader = new ReadJSONDatabase();
            result = reader.read("items.json");
        } else if (formatFile.equals("xml")){
            IReadJSON reader = new XMLToJSONAdapter();
            result = reader.read("items.xml");
        }
        return result;
    }

    public  static void updateData(JSONArray data){
        WriteJSONDatabase.writeToDatabase(data, "items.json");
    }
}
