package ooploverz.tubes2_oop.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class MemberVIP extends Member {

    public MemberVIP(int id, String name, String phoneNum, int points, boolean isActive) {
        super(id,name,phoneNum,points,isActive);
    }

    @Override
    public int priceCuts(int totalPrice) {
        if (this.isActive) return totalPrice;

        float discountVIP = 0.1f;
        int afterDisc = (int) Math.floor(totalPrice - (totalPrice * discountVIP)) ;
        return super.priceCuts(afterDisc);
    }
}
