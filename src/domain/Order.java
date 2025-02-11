package domain;
import java.util.ArrayList;

public class Order extends Mara {
    int basePrice;
    int ingredientPrice = 1000; // 재료당 추가 가격

    public Order(String name, ArrayList<String> ingredients, int spicinessLevel) {
        super(name, spicinessLevel);  // 메뉴 이름과 맵기 설정
        addIngredients(ingredients);

        if (name.equalsIgnoreCase("마라탕")) {
            this.basePrice = 8000; // 마라탕 기본 가격
        } else if (name.equalsIgnoreCase("마라샹궈")) {
            this.basePrice = 14000; // 마라샹궈 기본 가격
        } else {
            throw new IllegalArgumentException("잘못된 메뉴 선택");
        }
    }

    // 금액 계산 로직
    public int calculateBaseTotal() {
        int total = basePrice;
        for (String ingredient : ingredients) {
            if (ingredient.isEmpty()) {
                continue;  // 빈 문자열 건너뛰기
            }
            if (ingredient.equals("소고기") || ingredient.equals("양고기")) {
                total += 3000; // 소고기와 양고기 가격
            } else {
                total += ingredientPrice; // 기본 재료 가격
            }
        }
        return total;
    }

    public void calculateTotal() {
        int total = calculateBaseTotal();
        System.out.println("총 가격: " + total + "원");
    }
}