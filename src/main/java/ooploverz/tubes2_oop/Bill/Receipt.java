package ooploverz.tubes2_oop.Bill;

import ooploverz.tubes2_oop.inventory.Item;
import org.json.JSONException;
import org.json.JSONObject;

public interface Receipt {
    public JSONObject toJson() throws JSONException;
    public boolean isItemExist(Item search);
    public int getItemAmount(Item search);

}
