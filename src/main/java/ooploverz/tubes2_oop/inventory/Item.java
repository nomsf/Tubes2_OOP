package ooploverz.tubes2_oop.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.json.JSONException;
import org.json.JSONObject;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Item {
    private int stock;
    private String name;
    private int price;
    private int buyPrice;
    private String category;
    private String image;

    public JSONObject getJSONObject(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("stock", stock);
            obj.put("name", name);
            obj.put("price", price);
            obj.put("buyPrice", buyPrice);
            obj.put("category", category);
            obj.put("image", image);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static Item getItemObject(JSONObject obj){
        // from JSONObject to Item
        try {
            return new Item(obj.getInt("stock"), obj.getString("name"), obj.getInt("price"), obj.getInt("buyPrice"), obj.getString("category"), obj.getString("image"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
