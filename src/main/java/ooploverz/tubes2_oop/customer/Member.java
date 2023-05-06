package ooploverz.tubes2_oop.customer;

import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Member extends Customer{
    @NonNull @Getter @Setter
    protected String name;
    @NonNull @Getter @Setter
    protected String phoneNumber;
    @Getter @Setter
    protected int points = 0;

    Member() {
        super();
        this.name = "";
        this.phoneNumber = "";
    }
    public int priceCuts(int totalPrice) {
        int result = Math.max(totalPrice - points, 0);
        this.points = result == 0 ? this.points - totalPrice : 0;
        return result;
    }
}