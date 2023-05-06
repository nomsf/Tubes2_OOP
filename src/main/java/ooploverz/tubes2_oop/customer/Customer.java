package ooploverz.tubes2_oop.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Customer {
    protected final int customerId;
    Customer() {
        this.customerId = 0;
        // TODO : Implementasi Id++ dengan mengambil dari databases dan menambah 1 jika ada, jika belum 0
    }
}
