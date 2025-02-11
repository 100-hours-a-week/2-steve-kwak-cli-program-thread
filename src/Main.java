import domain.Order;
import domain.Mara;
import service.Pay;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 재료 예시 출력
        String[] ingredientExamples = {
                "숙주나물", "뉴진면", "콩나물", "청경채", "건두부", "알배기배추",
                "두부피", "분모자", "팽이버섯", "새송이버섯", "느타리버섯", "햄", "고수",
                "양고기", "소고기", "목이버섯", "흰목이버섯", "메추리알", "죽순", "다시마", "옥수수면", "중국당면", "납작면"
        };

        System.out.println("=== 마라 주문 시스템 ===");
        System.out.println("1. 마라탕");
        System.out.println("2. 마라샹궈");
        System.out.print("메뉴를 선택하세요(번호로): ");
        int menuChoice = scanner.nextInt();

        // 메뉴 선택
        Mara foodItem;
        if (menuChoice == 1) {
            foodItem = new Mara("마라탕", 0);
        } else if (menuChoice == 2) {
            foodItem = new Mara("마라샹궈", 0);
        } else {
            System.out.println("잘못된 선택입니다.");
            return;
        }

        // 맵기 선택
        System.out.println("\n=== 맵기 선택 ===");
        System.out.println("1. 1단계 (매운맛 적음)");
        System.out.println("2. 2단계");
        System.out.println("3. 3단계");
        System.out.println("4. 4단계");
        System.out.println("5. 5단계 (매운맛 강함)");
        System.out.print("맵기 단계 (1~5)를 입력하세요: ");
        int spicinessLevel = scanner.nextInt();

        // 재료 예시 출력
        System.out.println("\n선택 가능한 재료 목록:");
        for (String ingredient : ingredientExamples) {
            System.out.println("- " + ingredient);
        }

        // 재료 선택
        scanner.nextLine();
        System.out.print("\n사용할 재료를 쉼표(,)로 구분하여 입력하세요(공백없이 입력): ");
        String ingredientInput = scanner.nextLine();

        ArrayList<String> ingredients = new ArrayList<>(Arrays.asList(ingredientInput.split("\\s*,\\s*")));
        if (ingredients.size() == 1 && ingredients.get(0).isEmpty()) {
            System.out.println("선택된 재료가 없으므로 기본값으로 계산됩니다.");
        }

        // 주문 객체 생성
        Order order = new Order(foodItem.name, ingredients, spicinessLevel);

// 결제 객체 생성 (Order 객체 전달)
        Pay pay = new Pay(order);

        System.out.println("\n=== 주문서 ===");
        order.display();
        order.calculateTotal();

        System.out.print("\n할인 코드를 입력하세요(없으면 엔터): ");
        String couponCode = scanner.nextLine();

        pay.calculateTotalWithDiscount(couponCode);

    }
}
