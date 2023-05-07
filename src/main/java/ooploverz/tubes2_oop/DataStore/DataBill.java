package ooploverz.tubes2_oop.DataStore;

import org.json.JSONArray;

import java.util.List;

public class DataBill {
    public static JSONArray getData(){
        return ReadJSONDatabase.readFromDatabase("bills.json");
    }

    public  static void updateData(JSONArray data){
        WriteJSONDatabase.writeToDatabase(data, "bills.json");
    }
}
