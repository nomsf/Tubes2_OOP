package ooploverz.tubes2_oop.DataStore;

import org.json.JSONArray;

import java.util.List;

public class DataBill {
    public static JSONArray getData(){
        String formatFile = "json";
        JSONArray result = new JSONArray();
        if (formatFile.equals("json")){
            ReadJSONDatabase reader = new ReadJSONDatabase();
            result = reader.read("bills.json");
        } else if (formatFile.equals("xml")){
            IReadJSON reader = new XMLToJSONAdapter();
            result = reader.read("bills.xml");
        }
        return  result;
    }


    public  static void updateData(JSONArray data){
        WriteJSONDatabase.writeToDatabase(data, "bills.json");
    }
}
