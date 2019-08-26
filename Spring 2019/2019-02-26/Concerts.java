package icpc.seerc2017;

import java.util.Arrays;
import java.util.Scanner;

// https://codeforces.com/gym/101669
public class Concerts {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();
        int[] breakLen = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        char[] watchSeq = sc.nextLine().toCharArray();
        char[] seq = sc.nextLine().toCharArray();
        // ways[i][j]: ways to watch shows in watchSeq up to not including
        // watchSeq[j] before the show at seq[i] comes on.
        int[][] ways = new int[n + 1][k + 1];
        for (int i = 0; i < n; i++) {
            ways[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                //don't watch this one
                ways[i][j] = ways[i - 1][j];
                //watch this one
                if (watchSeq[j - 1] == seq[i - 1]) {
                    int lastValid = i - 1;
                    if (j > 1) {
                        lastValid = i - 1 - breakLen[watchSeq[j - 2] - 'A'];
                    }
                    if (lastValid >= 0) {
                        ways[i][j] = (ways[i][j] + ways[lastValid][j - 1]) % MOD;
                    }
                }
            }
        }
        System.out.println(ways[n][k]);
    }
}
