package ooploverz.tubes2_oop.customer;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class Customer {
    protected int customerId;
    Customer() {
        this.customerId = 0;
        // TODO : Implementasi Id++ dengan mengambil dari databases dan menambah 1 jika ada, jika belum 0
    }
}
