package codeforces;

import java.util.Scanner;

//https://codeforces.com/problemset/problem/1097/B
public class PetrAndCombinationLock {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < 1 << n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) == (1 << j)) {
                    sum += a[j];
                } else {
                    sum -= a[j];
                }
            }
            if (sum % 360 == 0) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
}
