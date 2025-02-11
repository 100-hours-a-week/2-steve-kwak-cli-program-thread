package service;
import domain.Order;
public class Pay {
    private Order order; // Order 객체 포함

    public Pay(Order order) {
        this.order = order;
    }

    public void calculateTotalWithDiscount(String couponCode) {
        int total = order.calculateBaseTotal(); // Order 객체의 금액 계산 로직 호출

        // 할인 코드 확인
        if ("1111".equals(couponCode)) {
            total *= 0.9; // 10% 할인 적용
            System.out.println("할인 코드 적용: 10% 할인되었습니다.");
        } else {
            System.out.println("할인 코드가 유효하지 않습니다.");
        }

        System.out.println("최종 결제 금액: " + total + "원");
    }
}
