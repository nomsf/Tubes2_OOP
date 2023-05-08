package ooploverz.tubes2_oop.bill;

import lombok.Getter;

import ooploverz.tubes2_oop.dataStore.DataFixedBill;
import ooploverz.tubes2_oop.inventory.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FixedBillList implements ReceiptList{
    @Getter
    private ArrayList<Receipt> fixedBillList;

    public FixedBillList()  {
        JSONArray billListData = DataFixedBill.getData();
        this.fixedBillList = new ArrayList<>();
        try {
            if(billListData.length() != 0) {
                for (int i = 0; i < billListData.length(); i++) {
                    int total;
                    int buyerId;
                    boolean paid;
                    String dateNow;
                    Map<Item, Integer> itemMap = new HashMap<>();

                    JSONObject billData = billListData.getJSONObject(i);

                    total = billData.getInt("total");
                    buyerId = billData.getInt("buyerId");
                    paid = billData.getBoolean("paid");
                    dateNow = billData.getString("dateNow");


                    // make map from json
                    JSONArray mapData = billData.getJSONArray("map");
                    for (int j = 0; j < mapData.length(); j++) {
                        JSONObject itemContData = mapData.getJSONObject(j);

                        JSONObject itemData = itemContData.getJSONObject(String.valueOf(j));
                        Item newItem = Item.getItemObject(itemData);
                        int amount = itemContData.getInt("amount");

                        // put on the map
                        itemMap.put(newItem, amount);

                    }
                    Bill tempBill = new Bill(total, buyerId, itemMap);
                    FixedBill newBill = new FixedBill(tempBill, paid, dateNow);



                    fixedBillList.add(newBill);
                }
            }
            System.out.println("fixed bill list:");
            System.out.println(fixedBillList);
        }
        catch (JSONException error){
            System.out.println("JSON Exception: " + error.getMessage());
        }
    }

    public void saveData(){
        JSONArray billListData = new JSONArray();
        try {
            for (Receipt oneBill : fixedBillList) {
                JSONObject billData = oneBill.toJson();
                billListData.put(billData);

            }
            DataFixedBill.updateData(billListData);
        }
        catch (JSONException error){
            System.out.println("JSON Exception: " + error.getMessage());
        }
    }

    public void addBill(Receipt addedBill){
        this.fixedBillList.add(addedBill);
    }




}
