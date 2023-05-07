package ooploverz.tubes2_oop.Bill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ooploverz.tubes2_oop.inventory.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

@Getter
@AllArgsConstructor
public class FixedBill extends Bill implements Receipt {
    private boolean paid;

    // Create FixBill from Bill
    public FixedBill(Bill bill){
        super(bill);
        this.paid = false;
    }

    public FixedBill(Bill bill, boolean paid){
        super(bill);
        this.paid = paid;
    }



    @Override
    public JSONObject toJson() {
        // generate json
        try {
            // put attribute total and buyerId and paid in json
            JSONObject jsonBill = new JSONObject();
            jsonBill.put("total", this.getTotal());
            jsonBill.put("buyerId", this.getBuyerId());
            jsonBill.put("paid", this.paid);

            // put item Map
            JSONArray nestedMap = new JSONArray();

            int count = 0;
            for (Map.Entry<Item, Integer> entry : this.getItemMap().entrySet()) {
                count++;
                Item key = entry.getKey();
                Integer value = entry.getValue();

                // generate json
                JSONObject listElement = new JSONObject();
                JSONObject jsonItem = key.getJSONObject();

                listElement.put(String.valueOf(count), jsonItem);
                listElement.put("amount", value);

                nestedMap.put(listElement);
            }
            jsonBill.put("map",nestedMap);
            return jsonBill;
        }
        catch (JSONException error){
            System.out.println("JSON Exception: " + error.getMessage());

        }
        return null;
    }

    public void pay(){
        this.paid = true;
    }
}

