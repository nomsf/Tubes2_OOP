package ooploverz.tubes2_oop.Bill;

import lombok.Getter;

@Getter
public class FixedBill extends Bill implements Receipt {
    private boolean paid;

    // Create FixBill from Bill
    public FixedBill(Bill bill){
        super(bill.getBuyerId());
        this.paid = false;
    }
    @Override
    public void saveToDataStore() {

    }
}

