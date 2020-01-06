package codeforces;

import java.util.Scanner;

//https://codeforces.com/contest/1091/problem/B
public class NewYearAndTheTreasureGeolocation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long x = 0;
        long y = 0;
        for (int i = 0; i < 2 * n; i++) {
            x += sc.nextInt();
            y += sc.nextInt();
        }
        System.out.println(x / n + " " + y / n);
        sc.close();
    }
}
