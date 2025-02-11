package domain;

public class FoodItem {
    public String name;

    FoodItem(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("선택한 메뉴: " + name);
    }
}
