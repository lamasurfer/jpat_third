package org.example.task2;

public class Frog {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 10;

    private int position;

    public Frog() {
        position = 5;
    }

    public boolean jump(int steps) {
        int jump = position + steps;
        if (jump < MIN_POSITION || jump > MAX_POSITION) {
            System.out.println("Лягушка так не может!");
            return false;
        } else {
            for (int i = 0; i < Math.abs(steps); i++) {
                System.out.print("Ква!");
            }
            System.out.println();
            position += steps;
            return true;
        }
    }

    public void showFrog() {
        System.out.printf("%30s%n", "\uD83C\uDF19");
        System.out.print("\uD83C\uDF35 ");
        for (int i = 0; i < MAX_POSITION + 1; i++) {
            if (i == position) {
                System.out.print(" \uD83D\uDC38 ");
            } else {
                System.out.print(" . ");
            }
        }
        System.out.print(" \uD83C\uDF35");
        System.out.println();
    }
}
