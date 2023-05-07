package ooploverz.tubes2_oop.customer;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class Customer {
    protected int customerId;

    private static int id = 1;
    public Customer() {
        this.customerId = id;
        // TODO : Implementasi Id++ dengan mengambil dari databases dan menambah 1 jika ada, jika belum 0
        id ++;
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        System.out.println(customer.getCustomerId());
        Customer customer1 = new Customer();
        System.out.println(customer1.getCustomerId());
        Customer customer2 = new Customer(10);
        System.out.println(customer2.getCustomerId());
    }
}
