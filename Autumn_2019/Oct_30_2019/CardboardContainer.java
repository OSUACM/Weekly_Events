package Autumn_2019.Oct_30_2019;

import java.util.Scanner;

//https://open.kattis.com/contests/r6w6qz/problems/cardboardcontainer
public class CardboardContainer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int best = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n/i; j++) {
                int k = n/(i*j);
                if(i*j*k==n){
                    best = Math.min(best, i*j+j*k+i*k);
                }
            }
        }
        System.out.println(best*2);
    }
}
