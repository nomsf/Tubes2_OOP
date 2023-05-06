package ooploverz.tubes2_oop.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Item {
    private int stock;
    private String name;
    private int price;
    private int buyPrice;
    private String category;
    private String image;
}
