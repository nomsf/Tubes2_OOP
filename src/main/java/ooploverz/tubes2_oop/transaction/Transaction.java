package ooploverz.tubes2_oop.transaction;

import ooploverz.tubes2_oop.inventory.Item;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class Transaction {
    private int id;
    private LocalDate date;
    private String customer;
    private Item[] listItem;
    private int itemCount;

    public void addItem(Item item) {
        if (itemCount < listItem.length) {
            listItem[itemCount] = item;
            itemCount++;
        } else {
            System.out.println("Tidak dapat menambahkan item. Kapasitas transaksi penuh.");
        }
    }

    public void removeItem(Item item) {
        int index = -1;
        for (int i = 0; i < itemCount; i++) {
            if (listItem[i].equals(item)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Geser item-item setelah index ke kiri
            for (int i = index; i < itemCount - 1; i++) {
                listItem[i] = listItem[i + 1];
            }
            listItem[itemCount - 1] = null; // Menghapus referensi pada index terakhir
            itemCount--;
        } else {
            System.out.println("Item tidak ditemukan dalam transaksi.");
        }
    }
}
