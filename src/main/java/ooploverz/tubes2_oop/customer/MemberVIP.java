package ooploverz.tubes2_oop.customer;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MemberVIP extends Member {

    public MemberVIP(int id, String name, String phoneNum, int points, boolean isActive) {
        super(id,name,phoneNum,points,isActive);
    }

    @Override
    public int priceCuts(int totalPrice) {
        if (!this.isActive) return totalPrice;

        float discountVIP = 0.1f;
        int afterDisc = (int) Math.floor(totalPrice - (totalPrice * discountVIP)) ;
        return super.priceCuts(afterDisc);
    }

    public static void main(String[] args) {
        // create a new member instance
        MemberVIP member = new MemberVIP(1, "John Doe", "555-1234", 100, true);

        // test the getters and setters
        System.out.println(member.getCustomerId()); // output: 1
        System.out.println(member.getName()); // output: John Doe
        System.out.println(member.getPhoneNumber()); // output: 555-1234
        System.out.println(member.getPoints()); // output: 100
        System.out.println(member.isActive()); // output: true

        MemberVIP member2 = new MemberVIP();

        System.out.println(member2.getCustomerId()); // output: 0
        System.out.println(member2.getName()); // output: null
        System.out.println(member2.getPhoneNumber()); // output: null
        System.out.println(member2.getPoints()); // output: 0
        System.out.println(member2.isActive()); // output: true

        MemberVIP member4 = new MemberVIP();
        System.out.println(member4.getCustomerId()); // output: 1
        MemberVIP member5 = new MemberVIP();
        System.out.println(member5.getCustomerId()); // output: 2

        // test the priceCuts method
        System.out.println("-------------------");
        int totalPrice = 2000;
        System.out.println(member.getPoints() + " " + member.isActive()); // output: 100 true
        int discountedPrice = member.priceCuts(totalPrice);

        System.out.println(discountedPrice); // output: 1700
        System.out.println(member.getPoints()); // output: 0

        member.setPoints(100);
        discountedPrice = member.priceCuts(100);

        System.out.println(discountedPrice); // output: 0
        System.out.println(member.getPoints()); // output: 55

        member.setPoints(100);
        discountedPrice = member.priceCuts(100);

        System.out.println(discountedPrice); // output: 0
        System.out.println(member.getPoints()); // output: 10

        // test the toListString method
        List<String> memberList = member.toListString();

        System.out.println(memberList); // output: [1, Jane Doe, 555-5678, 0, false]
    }
}
