package ooploverz.tubes2_oop.customer;

import lombok.Getter;
import ooploverz.tubes2_oop.dataStore.DataMember;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ListOfMember {
    @Getter
    private final List<Member> memberList;

    public ListOfMember() {
        this.memberList = new ArrayList<>();
        for (int i = 0; i < DataMember.getData().length(); i++) {
            try {
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
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        memberList.sort(Comparator.comparingInt(Member::getCustomerId));


    }


    public void addMember(Member member) {
        memberList.add(member);
        memberList.sort(Comparator.comparingInt(Member::getCustomerId).reversed());
    }

    public void addAllMember(List<Member> members) {
        memberList.addAll(members);
        memberList.sort(Comparator.comparingInt(Member::getCustomerId).reversed());
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


    public int searchMember(String name) {
        for (Member member : memberList) {
            if (member.getName().equals(name)) {
                return member.getCustomerId();
            }
        }
        return -1;
    }
}
