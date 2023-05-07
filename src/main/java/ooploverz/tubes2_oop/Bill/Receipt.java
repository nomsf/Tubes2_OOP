package ooploverz.tubes2_oop.Bill;

import ooploverz.tubes2_oop.inventory.Item;

public interface Receipt {
    public void saveToDataStore();
    public boolean isItemExist(Item search);
    public int getItemAmount(Item search);

}
