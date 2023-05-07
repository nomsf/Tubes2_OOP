package ooploverz.tubes2_oop.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ooploverz.tubes2_oop.DataStore.DataMember;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ListOfMember {
    @Getter
    private final List<Member> memberList;

    public ListOfMember() throws JSONException {
        this.memberList = new ArrayList<>();
        for (int i = 0; i < DataMember.getData().length(); i++) {
            Member member = DataMember.getData().getJSONObject(i).getBoolean("isVip") ?
                    new MemberVIP(DataMember.getData().getJSONObject(i).getInt("customerId"),
                            DataMember.getData().getJSONObject(i).getString("name"),
                            DataMember.getData().getJSONObject(i).getString("phoneNumber"),
                            DataMember.getData().getJSONObject(i).getInt("points"),
                            DataMember.getData().getJSONObject(i).getBoolean("isActive")) :
                    new Member(DataMember.getData().getJSONObject(i).getInt("customerId"),
                            DataMember.getData().getJSONObject(i).getString("name"),
                            DataMember.getData().getJSONObject(i).getString("phoneNumber"),
                            DataMember.getData().getJSONObject(i).getInt("points"),
                            DataMember.getData().getJSONObject(i).getBoolean("isActive"));
            memberList.add(member);
        }
        // TODO : Implementasi Id++ dengan mengambil dari databases dan menambah 1 jika ada, jika belum 0
    }


    public void addMember(Member member) {
        memberList.add(member);
        memberList.sort(Comparator.comparingInt(Member::getCustomerId));
    }

    public void addAllMember(List<Member> members) {
        memberList.addAll(members);
        memberList.sort(Comparator.comparingInt(Member::getCustomerId));
    }

    public Member getMember(int index) { return memberList.get(index); }

    public void updateMember(Member member) {
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getCustomerId() == member.getCustomerId()) {
                memberList.set(i, member);
                break;
            }
        }
    }


    public int searchMember(String name, int id) {
        for (Member member : memberList) {
            if (member.getName().equals(name) && member.getCustomerId() == id) {
                return member.getCustomerId();
            }
        }
        return -1;
    }
}
