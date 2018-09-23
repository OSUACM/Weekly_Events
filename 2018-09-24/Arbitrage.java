package acmclub;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Arbitrage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n_currency = Integer.parseInt(sc.nextLine());
        do {
            Map<String, Integer> currencies = new HashMap<>();
            for (int i = 0; i < n_currency; i++) {
                currencies.put(sc.next(), i);
            }
            double[][] edgeMatrix = new double[n_currency][n_currency];
            for (int i = 0; i < edgeMatrix.length; i++) {
                for (int j = 0; j < edgeMatrix[i].length; j++) {
                    if (i != j) {
                        edgeMatrix[i][j] = Double.NEGATIVE_INFINITY;
                    }
                }
            }
            int n_rates = Integer.parseInt(sc.next());
            for (int i = 0; i < n_rates; i++) {
                String cur1 = sc.next();
                int node1 = currencies.get(cur1);
                String cur2 = sc.next();
                int node2 = currencies.get(cur2);
                String[] ratioStr = sc.next().split(":");
                double ratio = Double.parseDouble(ratioStr[1]) / Double.parseDouble(ratioStr[0]);
                edgeMatrix[node1][node2] = Math.log(ratio);
            }
            for (int i = 0; i < edgeMatrix.length; i++) {
                for (int j = 0; j < edgeMatrix.length; j++) {
                    for (int k = 0; k < edgeMatrix.length; k++) {
                        //check if there is bigger gain j->i->k
                        if (edgeMatrix[j][i] + edgeMatrix[i][k] > edgeMatrix[j][k]) {
                            edgeMatrix[j][k] = edgeMatrix[j][i] + edgeMatrix[i][k];
                        }
                    }
                }
            }
            boolean arbitrage = false;
            for (int i = 0; i < edgeMatrix.length; i++) {
                if (edgeMatrix[i][i] > 1e-7) {
                    arbitrage = true;
                }
            }
            if (arbitrage) {
                System.out.println("Arbitrage");
            } else {
                System.out.println("Ok");
            }
            n_currency = Integer.parseInt(sc.next());
        } while (n_currency != 0);
        sc.close();
    }
}
