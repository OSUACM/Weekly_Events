import java.io.PrintWriter;
import java.util.Scanner;

// https://codeforces.com/problemset/problem/729/D
public class SeaBattle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        char[] c = sc.nextLine().toCharArray();
        int maxShips = 0;
        int unusedLen = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] == '1') {
                unusedLen = 0;
            } else {
                unusedLen++;
                if (unusedLen % b == 0) {
                    maxShips++;
                }
            }
        }
        int shots = maxShips - a + 1;
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(shots);
        unusedLen = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] == '1') {
                unusedLen = 0;
            } else {
                unusedLen++;
                if (unusedLen % b == 0) {
                    if (shots == 1) {
                        pw.println(i + 1);
                        pw.flush();
                        return;
                    } else {
                        shots--;
                        pw.print((i + 1) + " ");
                    }
                }
            }
        }
    }
}
