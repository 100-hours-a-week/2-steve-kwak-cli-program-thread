import domain.Order;
import domain.Mara;
import service.Pay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String[] ingredientExamples = {
                "숙주나물", "뉴진면", "콩나물", "청경채", "건두부", "알배기배추",
                "두부피", "분모자", "팽이버섯", "새송이버섯", "느타리버섯", "햄", "고수",
                "양고기", "소고기", "목이버섯", "흰목이버섯", "메추리알", "죽순", "다시마", "옥수수면", "중국당면", "납작면"
        };

        Scanner scanner = new Scanner(System.in);

        ExecutorService executorService = Executors.newFixedThreadPool(2); // 비동기 실행을 위한 스레드 풀

        System.out.println("=== 마라 주문 시스템 ===");
        System.out.println("1. 마라탕");
        System.out.println("2. 마라샹궈");
        System.out.print("메뉴를 선택하세요(번호로): ");
        int menuChoice = scanner.nextInt();

        Mara foodItem;
        if (menuChoice == 1) {
            foodItem = new Mara("마라탕", 0);
        } else if (menuChoice == 2) {
            foodItem = new Mara("마라샹궈", 0);
        } else {
            System.out.println("잘못된 선택입니다.");
            return;
        }

        System.out.println("\n=== 맵기 선택 ===");
        System.out.println("1. 1단계 (매운맛 적음)");
        System.out.println("2. 2단계");
        System.out.println("3. 3단계");
        System.out.println("4. 4단계");
        System.out.println("5. 5단계 (매운맛 강함)");
        System.out.print("맵기 단계 (1~5)를 입력하세요: ");
        int spicinessLevel = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n선택 가능한 재료 목록:");
        for (String ingredient : ingredientExamples) {
            System.out.println("- " + ingredient);
        }

        System.out.print("\n사용할 재료를 쉼표(,)로 구분하여 입력하세요: ");
        String ingredientInput = scanner.nextLine();
        ArrayList<String> ingredients = new ArrayList<>(Arrays.asList(ingredientInput.split("\\s*,\\s*")));

        // 주문 객체 생성
        Order order = new Order(foodItem.name, ingredients, spicinessLevel);
        Pay pay = new Pay(order);

        System.out.println("\n=== 주문 진행 중... ===");

        // 비동기 주문 처리
        CompletableFuture<Void> orderFuture = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("주문을 처리 중입니다...");
                Thread.sleep(2000); // 주문 준비 과정 시뮬레이션
                order.display();
                order.calculateTotal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, executorService);

        // 비동기 결제 처리
        CompletableFuture<Void> paymentFuture = orderFuture.thenRunAsync(() -> {
            System.out.print("\n할인 코드를 입력하세요(없으면 엔터): ");
            String couponCode = scanner.nextLine();

            try {
                System.out.println("결제 처리 중...");
                Thread.sleep(1500); // 결제 과정 시뮬레이션
                pay.calculateTotalWithDiscount(couponCode);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, executorService);

        // 모든 작업 완료 후 종료
        paymentFuture.thenRun(() -> {
            System.out.println("\n주문이 완료되었습니다!");
            executorService.shutdown();
        });
    }
}
