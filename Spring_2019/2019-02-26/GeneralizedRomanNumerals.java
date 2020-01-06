package icpc.ecna2014;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

// https://codeforces.com/gym/100641
public class GeneralizedRomanNumerals {
    private static Set<Integer>[][] validValues;
    private static int[] expression;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        char[] expressionStr = input.nextLine().toCharArray();
        int caseNum = 1;
        while (expressionStr[0] != '0') {
            int n = expressionStr.length;
            validValues = new HashSet[n + 1][n + 1];
            validValues[0][0] = new HashSet<>();
            expression = new int[n];
            for (int i = 0; i < n; i++) {
                switch (expressionStr[i]) {
                    case 'I':
                        expression[i] = 1;
                        break;
                    case 'V':
                        expression[i] = 5;
                        break;
                    case 'X':
                        expression[i] = 10;
                        break;
                    case 'L':
                        expression[i] = 50;
                        break;
                    case 'C':
                        expression[i] = 100;
                        break;
                }
            }

            for (int i = 0; i < n; i++) {
                validValues[i][i + 1] = new HashSet<>();
                validValues[i][i + 1].add(expression[i]);
            }
            for (int len = 2; len <= n; len++) {
                for (int st = 0; st <= n - len; st++) {
                    int end = st + len;
                    validValues[st][end] = new HashSet<>();
                    boolean[] added = new boolean[5001]; // to increase speed
                    for (int mid = st + 1; mid < end; mid++) {
                        for (int l : validValues[st][mid]) {
                            for (int r : validValues[mid][end]) {
                                int toAdd = l >= r ? l + r : r - l;
                                if (!added[toAdd]) {
                                    validValues[st][end].add(toAdd);
                                    added[toAdd] = true;
                                }
                            }
                        }
                    }
                }
            }
            pw.print("Case " + caseNum + ":");
            for (int i : new TreeSet<>(validValues[0][n])) {
                pw.print(" " + i);
            }
            pw.println();
            caseNum++;
            expressionStr = input.nextLine().toCharArray();
        }
        pw.flush();
    }
}
