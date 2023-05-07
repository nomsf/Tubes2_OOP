package ooploverz.tubes2_oop.DataStore;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DataInventory {
//    public static void main(String[] args) {
////        List<List<String>> data = new ArrayList<>();
////        List<String> index = new ArrayList<>(List.of("stock", "name", "price", "buyPrice", "category", "image"));
////        List<String> row1 = new ArrayList<>(List.of("100", "Siuu", "999", "1000", "Football", "penaldo.png"));
////        List<String> row2 = new ArrayList<>(List.of("100", "El Mundo", "119", "100", "Food", "mundo.png"));
////        data.add(index);
////        data.add(row1);
////        data.add(row2);
////        WriteDatabase.writeToDatabase(data, items.json);
//        System.out.println(ReadDatabase.readFromDatabase("items.json"));
//    }
    public static JSONArray getData(){
        return ReadJSONDatabase.readFromDatabase("items.json");
    }

    public  static void updateData(JSONArray data){
        WriteJSONDatabase.writeToDatabase(data, "items.json");
    }
}
