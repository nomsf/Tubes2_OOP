//package ooploverz.tubes2_oop.customer;
//
//import lombok.*;
//
//@ToString
//@RequiredArgsConstructor
//@AllArgsConstructor
//public class Member extends Customer{
//    private final float discoutVIP = 0.1f;
//    @NonNull @Getter @Setter
//    private String name;
//    @NonNull @Getter @Setter
//    private String phoneNumber;
//    @Getter @Setter
//    private int points = 0;
//    @Getter @Setter
//    private boolean isVIP = false;
//
//    public int priceCuts(int price) {
//        int afterDiscount = discountCuts(hargaBarang);
//        int pointUsed = Math.min(afterDiscount, points);
//        int resultCuts = afterDiscount - pointUsed;
//        this.points -= pointUsed;
//        return resultCuts;
//    }
//
//    protected int discountCuts(int price) {
//        return this.isVIP ? (int) Math.floor(price - (price * discoutVIP)) : price;
//    }
//}
//
