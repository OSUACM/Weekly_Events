package codeforces;

import java.io.*;
import java.util.*;
//https://codeforces.com/problemset/problem/1098/B
public class NiceTable {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        boolean flip = false;
        if (n > m) {
            flip = true;
        }
        char[][] table = new char[Math.min(n, m)][Math.max(n, m)];
        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (flip) {
                    table[j][i] = chars[j];
                } else {
                    table[i][j] = chars[j];
                }
            }
        }
        char[] chars = {'A', 'C', 'G', 'T'};
        char[][] bestTable = new char[2][table[0].length];
        int bestDir = 0;
        int minChange = Integer.MAX_VALUE;
        // iterate through every permutation. Let i, j be the indexes of
        // characters for odd spots, this forces the other characters for even spots.
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                List<Character> evenChars = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
                    if (k != i && k != j) {
                        evenChars.add(chars[k]);
                    }
                }
                // choose the direction to go through the table (horizontal or vertical rows)
                for (int dir = 0; dir < 2; dir++) {
                    int curChange = 0;
                    char[][] curTable;
                    curTable = new char[2][table[0].length];

                    //chars[i] & chars[j] go in even spots
                    for (int k = 0; k < (dir == 0 ? table[0].length : table.length); k++) {
                        char[] toUse = new char[2];
                        if (k % 2 == 0) {
                            toUse[0] = chars[i];
                            toUse[1] = chars[j];
                        } else {
                            toUse[0] = evenChars.get(0);
                            toUse[1] = evenChars.get(1);
                        }
                        int diff1 = 0;
                        int diff2 = 0;
                        for (int l = 0; l < (dir == 0 ? table.length : table[0].length); l++) {
                            if (dir == 0) {
                                if (table[l][k] != toUse[l % 2]) {
                                    diff1++;
                                }
                                if (table[l][k] != toUse[1 - (l % 2)]) {
                                    diff2++;
                                }
                            } else {
                                if (table[k][l] != toUse[l % 2]) {
                                    diff1++;
                                }
                                if (table[k][l] != toUse[1 - (l % 2)]) {
                                    diff2++;
                                }

                            }
                        }
                        curChange += Math.min(diff1, diff2);
                        if (diff1 <= diff2) {
                            curTable[0][k] = toUse[0];
                            curTable[1][k] = toUse[1];
                        } else {
                            curTable[0][k] = toUse[1];
                            curTable[1][k] = toUse[0];
                        }
                    }
                    if (curChange < minChange) {
                        bestDir = dir;
                        minChange = curChange;
                        bestTable = curTable;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (flip ^ (bestDir == 1)) {
                    pw.print(bestTable[j % 2][i]);
                } else {
                    pw.print(bestTable[i % 2][j]);
                }
            }
            pw.println();
        }
        pw.flush();
        br.close();
        pw.close();
    }

}
