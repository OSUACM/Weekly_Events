package codeforces;

import java.io.IOException;
import java.util.Scanner;

public class ColorfulBricks {
    private static final long MOD = 998244353;

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        int m = reader.nextInt();
        int k = reader.nextInt();

        long[][] ways = new long[k + 1][n];
        for (int i = 0; i < n; i++) {
            ways[0][i] = m;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k + 1; j++) {
                ways[j][i] = (ways[j - 1][i - 1] * (m - 1) + ways[j][i - 1]) % MOD;

            }
        }
        System.out.println(ways[k][n - 1]);
    }


}

