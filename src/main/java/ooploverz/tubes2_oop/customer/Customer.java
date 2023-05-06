package ooploverz.tubes2_oop.customer;

import lombok.Data;

@Data
public class Customer {
    private static int id = 0;
    protected int customerId;
    Customer() {
        this.customerId = id;
        // TODO : Implementasi Id++ dengan mengambil dari databases dan menambah 1 jika ada, jika belum 0
        id++;
    }

    public static void main(String[] args) {
        Customer a = new Customer();
        Customer b = new Customer();
        Customer c = new Customer();

        System.out.println(a + b.toString());
    }
}
