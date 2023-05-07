package ooploverz.tubes2_oop.customer;

import lombok.Getter;
import ooploverz.tubes2_oop.Bill.Receipt;

import java.util.ArrayList;
import java.util.List;

public class ListOfMember {
    @Getter
    private final List<Member> memberList;

    public ListOfMember() {
        this.memberList = new ArrayList<>();

        // TODO : Implementasi Id++ dengan mengambil dari databases dan menambah 1 jika ada, jika belum 0
    }

    public void addMember(Member member) {
        memberList.add(member);
    }

    public Member getMember(int index) {
        return memberList.get(index);
    }


    public int searchMember(String name) {
        for (Member member : memberList) {
            if (member.getName().equals(name)) {
                return member.getCustomerId();
            }
        }
        return -1;
    }
}
