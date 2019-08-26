package codeforces;

import java.util.Scanner;

// https://codeforces.com/problemset/problem/1129/B
public class WrongAnswer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt() + 2000;
        final int MAXV = 1_000_000;
        System.out.println(2000);
        StringBuilder ans = new StringBuilder();
        ans.append(-1);
        for (int i = 1; i < 2000; i++) {
            if (k > MAXV) {
                ans.append(" " + MAXV);
                k -= MAXV;
            } else {
                ans.append(" " + k);
                k = 0;
            }
        }
        System.out.println(ans);
    }
}
