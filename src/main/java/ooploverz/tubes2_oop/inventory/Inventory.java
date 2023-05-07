package ooploverz.tubes2_oop.inventory;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inventory {
    private Item[] listItem = new Item[0];


    public void addItem(int stock, String name, int price, int buyPrice, String category, String image){
        Item[] temp = new Item[listItem.length + 1];
        System.arraycopy(listItem, 0, temp, 0, listItem.length);
        temp[listItem.length] = new Item(stock, name, price, buyPrice, category, image);
        listItem = temp;
    }

    public void editItem(String name, int stock, String newName, int price, int buyPrice, String category, String image){
        changeName(name, newName);
        setStock(newName, stock);
        setPrice(newName, price);
        setBuyPrice(newName, buyPrice);
        setCategory(newName, category);
        setImage(newName, image);
    }

    public void removeItem(String name){
        Item[] temp = new Item[listItem.length - 1];
        int index = 0;
        for (Item i : listItem) {
            if (!i.getName().equals(name)) {
                temp[index] = i;
                index++;
            }
        }
        listItem = temp;
    }

    public void setStock(String name, int stock){
        for (Item i : listItem) {
            if (i.getName().equals(name)) {
                i.setStock(stock);
            }
        }
    }

    public void changeName(String name, String newName){
        for (Item i : listItem) {
            if (i.getName().equals(name)) {
                i.setName(newName);
            }
        }
    }

    public void setPrice(String name, int price){
        for (Item i : listItem) {
            if (i.getName().equals(name)) {
                i.setPrice(price);
            }
        }
    }

    public void setBuyPrice(String name, int buyPrice){
        for (Item i : listItem) {
            if (i.getName().equals(name)) {
                i.setBuyPrice(buyPrice);
            }
        }
    }

    public void setCategory(String name, String category){
        for (Item i : listItem) {
            if (i.getName().equals(name)) {
                i.setCategory(category);
            }
        }
    }

    public void setImage(String name, String image){
        for (Item i : listItem) {
            if (i.getName().equals(name)) {
                i.setImage(image);
            }
        }
    }

    public int getTotalItem(){
        return listItem.length;
    }

    public Inventory filterInventory(String input){
        Inventory temp = new Inventory();
        for (Item i : listItem) {
            if (i.getName().toLowerCase().contains(input.toLowerCase()) || i.getCategory().toLowerCase().contains(input.toLowerCase())) {
                temp.addItem(i.getStock(), i.getName(), i.getPrice(), i.getBuyPrice(), i.getCategory(), i.getImage());
            }
        }
        return temp;
    }
}
