package ooploverz.tubes2_oop.bill;

import org.json.JSONException;
import org.json.JSONObject;

public interface Receipt {
    JSONObject toJson() throws JSONException;
}
