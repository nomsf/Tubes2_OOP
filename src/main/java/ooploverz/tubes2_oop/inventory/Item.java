package ooploverz.tubes2_oop.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.json.JSONException;
import org.json.JSONObject;

@AllArgsConstructor
@Setter
@Getter
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
}
