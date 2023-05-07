package ooploverz.tubes2_oop.DataStore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteJSONDatabase {
    public static void writeToDatabase(List<List<String>> data, String filename) {
        try {
            // Pada kasus ini hanya satu elemen
            JSONArray rootData = new JSONArray();
            // Isi key
            List<String> keys = new ArrayList<>();
            for (int i=0; i < data.get(0).size(); i++) {
                keys.add(data.get(0).get(i));
            }
            // Isi data
            for (int i=1; i < data.size(); i++){
                JSONObject obj = new JSONObject();
                for (int j=0; j<data.get(i).size(); j++){
                    obj.put(keys.get(j), data.get(i).get(j));
                    System.out.println(obj.toString());
                }
                rootData.put(obj);
//                System.out.println(rootData.toString());
            }


            // Menuliskan file
            try (FileWriter file = new FileWriter("src/main/resources/ooploverz/tubes2_oop/Database/"+filename)) {
                file.write(rootData.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
