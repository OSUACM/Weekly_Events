package Autumn_2019.Oct_30_2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
//https://ecna19.kattis.com/problems/justpassingthrough
public class JustPassingThrough {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inLine = br.readLine().split(" ");
        int r = Integer.parseInt(inLine[0]);
        int c = Integer.parseInt(inLine[1]);
        int n = Integer.parseInt(inLine[2]);
        int[][] map = new int[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        boolean[][] isPass = new boolean[r][c];
        for (int i = 1; i < r - 1; i++) {
            for (int j = 1; j < c - 1; j++) {
                int h = map[i][j];
                if (map[i][j] == -1 || map[i - 1][j] == -1 || map[i + 1][j] == -1 || map[i][j - 1] == -1 || map[i][j + 1] == -1) {
                    continue;
                }
                isPass[i][j] = h < map[i + 1][j] && h < map[i - 1][j] && h > map[i][j - 1] && h > map[i][j + 1];
            }
        }
        int[][][] dp = new int[r][c][n + 2];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k <= n + 1; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        for (int j = 0; j < r; j++) {
            if (map[j][0] != -1) {
                dp[j][0][0] = map[j][0];
            }
        }
        for (int j = 1; j < c; j++) {
            for (int i = 0; i < r; i++) {
                if (map[i][j] == -1) {
                    continue;
                }
                for (int k = 0; k <= n; k++) {
                    int minV = Integer.MAX_VALUE;
                    if (i + 1 < r && dp[i + 1][j - 1][k] != -1 && map[i + 1][j - 1] != -1) {
                        minV = Math.min(dp[i + 1][j - 1][k], minV);

                    }
                    if (dp[i][j - 1][k] != -1 && map[i][j - 1] != -1) {
                        minV = Math.min(dp[i][j - 1][k], minV);

                    }
                    if (i - 1 >= 0 && dp[i - 1][j - 1][k] != -1 && map[i - 1][j - 1] != -1) {
                        minV = Math.min(dp[i - 1][j - 1][k], minV);

                    }
                    if (minV == Integer.MAX_VALUE) {
                        continue;
                    }
                    if (isPass[i][j]) {
                        if (dp[i][j][k + 1] == -1) {
                            dp[i][j][k + 1] = minV + map[i][j];
                        } else {
                            dp[i][j][k + 1] = Math.min(dp[i][j][k + 1], minV + map[i][j]);
                        }
                    } else {
                        if (dp[i][j][k] == -1) {
                            dp[i][j][k] = minV + map[i][j];
                        } else {
                            dp[i][j][k] = Math.min(dp[i][j][k], minV + map[i][j]);
                        }
                    }
                }
            }
        }

        int best = Integer.MAX_VALUE;
        for (int i = 0; i < r; i++) {
            if (dp[i][c - 1][n] != -1) {
                best = Math.min(best, dp[i][c - 1][n]);
            }
        }
        if (best == Integer.MAX_VALUE) {
            System.out.println("impossible");
        } else {
            System.out.println(best);
        }
    }
}