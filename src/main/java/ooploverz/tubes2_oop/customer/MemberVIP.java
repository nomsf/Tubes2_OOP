package ooploverz.tubes2_oop.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class MemberVIP extends Member {
    @Getter @Setter
    private boolean isVIP = false;

    @Override
    public int priceCuts(int totalPrice) {
        float discountVIP = 0.1f;
        int afterDisc = isVIP ? (int) Math.floor(totalPrice - (totalPrice * discountVIP)) : totalPrice;
        int result = Math.max(afterDisc - points, 0);
        this.points = result == 0 ? this.points - afterDisc : 0;
        return result;
    }
}
