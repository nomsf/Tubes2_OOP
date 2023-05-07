package ooploverz.tubes2_oop.DataStore;

import org.json.JSONArray;

public class DataFixedBill {
    public static JSONArray getData(){
        String formatFile = "json";
        JSONArray result = new JSONArray();
        if (formatFile.equals("json")){
            ReadJSONDatabase reader = new ReadJSONDatabase();
            result = reader.read("fixedBills.json");
        } else if (formatFile.equals("xml")){
            IReadJSON reader = new XMLToJSONAdapter();
            result = reader.read("fixedBills.xml");
        }
        return  result;
    }


    public  static void updateData(JSONArray data){
        WriteJSONDatabase.writeToDatabase(data, "fixedBills.json");
    }
}