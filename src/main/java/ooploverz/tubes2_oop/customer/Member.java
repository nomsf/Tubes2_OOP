package ooploverz.tubes2_oop.customer;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
public class Member extends Customer{
    @Getter @Setter
    protected String name;
    @Getter @Setter
    protected String phoneNumber;
    @Getter @Setter
    protected int points = 0;
    @Getter @Setter
    protected boolean isActive = true;

    public Member(int id, String name, String phoneNum, int points, boolean isActive) {
        super(id);
        this.name = name;
        this.phoneNumber = phoneNum;
        this.points = points;
        this.isActive = isActive;
    }

    public Member(List<String> memberList) {
        this.customerId = Integer.parseInt(memberList.get(0));
        this.name= memberList.get(1);
        this.phoneNumber = memberList.get(2);
        this.points = Integer.parseInt(memberList.get(3));
        this.isActive = Boolean.parseBoolean(memberList.get(4));
    }
    
    public int priceCuts(int totalPrice) {
        if (!this.isActive) return totalPrice;

        int result = Math.max(totalPrice - points, 0);
        this.points = result == 0 ? this.points - totalPrice : 0;
        return result;
    }

    public List<String> toListString() {
        List<String> atributeField = new ArrayList<>();
        atributeField.add("customerId");
        atributeField.add("name");
        atributeField.add("phoneNumber");
        atributeField.add("points");
        atributeField.add("isActive");

        List<String> memberList = new ArrayList<>();
        memberList.add(String.valueOf(customerId));
        memberList.add(name);
        memberList.add(phoneNumber);
        memberList.add(String.valueOf(points));
        memberList.add(String.valueOf(isActive));

        List<String> memberWithField = new ArrayList<>();
        memberWithField.add(atributeField.toString());
        memberWithField.add(memberList.toString());
        return memberWithField;
    }

    public static void main(String[] args) {
        // create a new member instance
        Member member = new Member(1, "John Doe", "555-1234", 100, true);

        // test the getters and setters
        System.out.println(member.getCustomerId()); // output: 1
        System.out.println(member.getName()); // output: John Doe
        System.out.println(member.getPhoneNumber()); // output: 555-1234
        System.out.println(member.getPoints()); // output: 100
        System.out.println(member.isActive()); // output: true

        Member member2 = new Member();

        System.out.println(member2.getCustomerId()); // output: 0
        System.out.println(member2.getName()); // output: null
        System.out.println(member2.getPhoneNumber()); // output: null
        System.out.println(member2.getPoints()); // output: 0
        System.out.println(member2.isActive()); // output: true

        Member member4 = new Member();
        System.out.println(member4.getCustomerId()); // output: 1
        Member member5 = new Member();
        System.out.println(member5.getCustomerId()); // output: 2

        // test the priceCuts method
        System.out.println("-------------------");
        int totalPrice = 200;
        System.out.println(member.getPoints() + " " + member.isActive()); // output: 100 true
        int discountedPrice = member.priceCuts(totalPrice);

        System.out.println(discountedPrice); // output: 100
        System.out.println(member.getPoints()); // output: 0

        member.setPoints(100);
        discountedPrice = member.priceCuts(50);

        System.out.println(discountedPrice); // output: 0
        System.out.println(member.getPoints()); // output: 50

        member.setPoints(100);
        discountedPrice = member.priceCuts(100);

        System.out.println(discountedPrice); // output: 100
        System.out.println(member.getPoints()); // output: 0

        // test the toListString method
        List<String> memberList = member.toListString();

        System.out.println(memberList); // output: [1, Jane Doe, 555-5678, 0, false]
    }
}