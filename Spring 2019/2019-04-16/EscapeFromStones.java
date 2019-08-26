package codeforces;

import java.io.PrintWriter;
import java.util.*;

//https://codeforces.com/contest/264/problem/A
public class EscapeFromStones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] dir = sc.nextLine().toCharArray();
        Queue<Integer> st = new ArrayDeque<>();
        Deque<Integer> end = new ArrayDeque<>(); // Stack
        for (int i = 1; i <= dir.length; i++) {
            if (dir[i - 1] == 'r') {
                st.add(i);
            } else {
                end.push(i);
            }
        }
        PrintWriter pw = new PrintWriter(System.out);
        for (Integer i : st) {
            pw.println(i);
        }
        for (Integer i : end) {
            pw.println(i);
        }
        pw.close();
        sc.close();
    }
}
