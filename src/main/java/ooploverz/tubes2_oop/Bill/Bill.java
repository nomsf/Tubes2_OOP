package ooploverz.tubes2_oop.Bill;
import lombok.Data;
import ooploverz.tubes2_oop.customer.Customer;
import ooploverz.tubes2_oop.inventory.Item;
import java.util.HashMap;
import java.util.Map;

@Data
public class Bill {
//  Attribute dari bill
    private int total;
    private final Customer buyer;
    private Map<Item, Integer> itemMap;

    public Bill(Customer buyer){
        this.total = 0;
        this.buyer = buyer;
        this.itemMap = new HashMap<Item, Integer>();
    }

    public void addItem(Item addedItem, int amount){
        total += addedItem.getPrice();
        itemMap.put(addedItem, Integer.valueOf(amount));
    }

}
