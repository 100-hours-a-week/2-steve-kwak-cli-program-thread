package domain;

import java.util.Scanner;

public class Spiciness {
    int level;

    public Spiciness(int level) {
        this.level = level;
    }

    public void displaySpiciness() {
        System.out.println("선택한 맵기: " + level + "단계");
    }
}
