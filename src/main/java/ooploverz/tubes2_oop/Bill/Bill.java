package ooploverz.tubes2_oop.Bill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ooploverz.tubes2_oop.customer.Customer;
import ooploverz.tubes2_oop.inventory.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Bill implements Receipt{
//  Attribute dari bill
    private int total;
    private int buyerId;
    private Map<Item, Integer> itemMap;

    public Bill(){
        this.total = 0;
        this.buyerId = -1;
        this.itemMap = new HashMap<Item, Integer>();
    }

    public Bill(Bill bill){
        this.total = bill.total;
        this.buyerId = bill.buyerId;
        this.itemMap = bill.itemMap;
    }

    public Bill(Customer buyer){
        this.total = 0;
        this.buyerId = buyer.getCustomerId();
        this.itemMap = new HashMap<Item, Integer>();
    }

    public Bill(int id){
        this.total = 0;
        this.buyerId = id;
        this.itemMap = new HashMap<Item, Integer>();
    }


    public void addItem(Item addedItem){
        total += addedItem.getPrice();
        if (this.itemMap.containsKey(addedItem)) {
            // Key exists, update the value
            this.itemMap.put(addedItem, this.itemMap.get(addedItem) + 1);
        } else {
            // Key does not exist, add a new key-value pair
            this.itemMap.put(addedItem, 1);
        }
    }

    public void addItem(Item addedItem, int amount){
        total += addedItem.getPrice();
        if (this.itemMap.containsKey(addedItem)) {
            // Key exists, update the value
            this.itemMap.put(addedItem, this.itemMap.get(addedItem) + amount);
        } else {
            // Key does not exist, add a new key-value pair
            this.itemMap.put(addedItem, amount);
        }
    }

    public boolean isItemExist(Item check){
        return itemMap.containsKey(check);
    }

    public int getItemAmount(Item check){
        return itemMap.get(check);
    }

    public JSONObject toJson(){
        // generate json
        try {
            // put attribute total and buyerId in json
            JSONObject jsonBill = new JSONObject();
            jsonBill.put("total", this.total);
            jsonBill.put("buyerId", this.buyerId);

            // put item Map
            JSONArray nestedMap = new JSONArray();

            int count = 0;
            for (Map.Entry<Item, Integer> entry : itemMap.entrySet()) {
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

}
