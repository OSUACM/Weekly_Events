import java.util.Scanner;

// https://codeforces.com/problemset/problem/596/B
public class WilburAndArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int curVal = 0;
        long cost = 0;
        for (int i = 0; i < n; i++) {
            int desiredVal = sc.nextInt();
            cost += Math.abs(curVal - desiredVal);
            curVal = desiredVal;
        }
        System.out.println(cost);
    }
}
