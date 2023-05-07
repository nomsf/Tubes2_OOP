package ooploverz.tubes2_oop.transaction;

import ooploverz.tubes2_oop.inventory.Item;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;
import ooploverz.tubes2_oop.util.DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class Transaction {
    private int id;
    private String customer;
    private Item[] listItem;
    private DateTime date;
    private int itemCount;
}