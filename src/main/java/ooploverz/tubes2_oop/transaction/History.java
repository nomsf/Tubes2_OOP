package ooploverz.tubes2_oop.transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ooploverz.tubes2_oop.inventory.Inventory;
import ooploverz.tubes2_oop.inventory.Item;
import ooploverz.tubes2_oop.util.DateTime;

public class History {
    private List<Transaction> transactions;

    public History() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public History filterTransaction(int input){
        History temp = new History();
        for (Transaction i : transactions ) {
            if (i.getId() == input) {
                temp.addTransaction(i);
            }
        }
        return temp;
    }
}
