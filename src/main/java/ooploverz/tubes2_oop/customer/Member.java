package ooploverz.tubes2_oop.customer;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Member extends Customer{
    @Getter @Setter
    protected String name;
    @Getter @Setter
    protected String phoneNumber;
    @Getter @Setter
    protected int points = 0;
    @Getter @Setter
    protected Boolean isActive = true;

    public Member(int id, String name, String phoneNum, int points, boolean isActive) {
        super(id);
        this.name = name;
        this.phoneNumber = phoneNum;
        this.points = points;
        this.isActive = isActive;
    }

    Member(List<String> memberList) {
        this.customerId = Integer.parseInt(memberList.get(0));
        this.name= memberList.get(1);
        this.phoneNumber = memberList.get(2);
        this.points = Integer.parseInt(memberList.get(3));
        this.isActive = Boolean.valueOf(memberList.get(4));
    }
    
    public int priceCuts(int totalPrice) {
        if (!this.isActive) return totalPrice;

        int result = Math.max(totalPrice - points, 0);
        this.points = result == 0 ? this.points - totalPrice : 0;
        return result;
    }

    public List<String> toListString() {
        List<String> memberList = new ArrayList<>();
        memberList.add(String.valueOf(customerId));
        memberList.add(name);
        memberList.add(phoneNumber);
        memberList.add(String.valueOf(points));
        memberList.add(String.valueOf(isActive));
        return memberList;
    }
}