package Autumn_2019.Sep_10_2019;

import java.util.Scanner;

// ACM Problem
public class PickingStones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int num_stones = sc.nextInt();
        int[] pickup = new int[n];
        for (int i = 0; i < n; i++) {
            pickup[i] = sc.nextInt();
        }
        // you go first
        // can you win?
        boolean[] isWinning = new boolean[num_stones + 1];
        isWinning[0] = false;
        for (int i = 1; i < isWinning.length; i++) {
            isWinning[i] = false;
            for (int value : pickup) {
                if (i >= value) {
                    if (!isWinning[i - value]) {
                        isWinning[i] = true;
                    }
                }
            }
        }
        if (isWinning[num_stones]) {
            System.out.println("Winning");
        } else {
            System.out.println("Losing");
        }
    }
}
