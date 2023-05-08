package ooploverz.tubes2_oop.bill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import ooploverz.tubes2_oop.inventory.Item;
import ooploverz.tubes2_oop.util.DateTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

@Getter
@AllArgsConstructor
@ToString
public class FixedBill extends Bill implements Receipt {
    private boolean paid;
    private String dateNow;

    // Create FixBill from Bill
    public FixedBill(Bill bill){
        super(bill);
        this.paid = false;
        DateTime now = new DateTime();
        this.dateNow = now.toString();
    }

    public FixedBill(Bill bill, boolean paid, String date){
        super(bill);
        this.paid = paid;
        this.dateNow = date;
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
            jsonBill.put("dateNow", this.dateNow);

            // put item Map
            JSONArray nestedMap = new JSONArray();

            int count = 0;
            for (Map.Entry<Item, Integer> entry : this.getItemMap().entrySet()) {
                Item key = entry.getKey();
                Integer value = entry.getValue();

                // generate json
                JSONObject listElement = new JSONObject();
                JSONObject jsonItem = key.getJSONObject();

                listElement.put(String.valueOf(count), jsonItem);
                listElement.put("amount", value);

                nestedMap.put(listElement);
                count++;
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

