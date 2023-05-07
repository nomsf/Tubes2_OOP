package ooploverz.tubes2_oop.customer;

import lombok.*;
import ooploverz.tubes2_oop.DataStore.DataFixedBill;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.OptionalInt;
import java.util.stream.IntStream;

@AllArgsConstructor
@Getter
@Setter
public class Customer {
    protected int customerId;

    private static int id;

    public Customer() {
        int maxId = 0;
        JSONArray data = DataFixedBill.getData();

        for (int i = 0; i < data.length(); i++) {
            try {
                JSONObject obj = data.getJSONObject(i);
                int id = obj.getInt("customerId");
                if (id > maxId) {
                    maxId = id;
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        this.customerId = maxId + 1;
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        System.out.println(customer.getCustomerId());
        Customer customer1 = new Customer();
        System.out.println(customer1.getCustomerId());
        Customer customer2 = new Customer(10);
        System.out.println(customer2.getCustomerId());
    }
}
