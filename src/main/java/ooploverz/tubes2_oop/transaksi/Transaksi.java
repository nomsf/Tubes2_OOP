package ooploverz.tubes2_oop.transaksi;
import ooploverz.tubes2_oop.inventory.Item;
import

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class Transaksi {
    private int id;
    private LocalDate date;
    private String customer;
    private Item[] ListItem;
}
