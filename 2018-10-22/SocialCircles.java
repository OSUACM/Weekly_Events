import java.util.Arrays;
import java.util.Scanner;
//http://codeforces.com/problemset/problem/1060/D
public class SocialCircles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] r = new int[n];
        int[] l = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = sc.nextInt();
            l[i] = sc.nextInt();
        }
        Arrays.sort(r);
        Arrays.sort(l);
        long sum = n;
        for (int i = 0; i < n; i++) {
            sum+= Math.max(r[i],l[i]);
        }
        System.out.println(sum);
    }
}
