package ooploverz.tubes2_oop.Bill;
import lombok.Data;
import java.util.Date;

@Data
public class Bill {
//  Attribute dari bill
    private int total;
    private final Customer buyer;
    private Date buy_date;
    private Item[] itemList;

    public Bill(Customer buyer, Item buyedItem){

    }

}
