import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

// https://codeforces.com/problemset/problem/1081/E
public class MissingNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        long[] inputArray = Arrays.stream(br.readLine().split(" "))
                .mapToLong(x -> Long.parseLong(x)).toArray();
        // answer array
        long[] a = new long[n];
        for (int i = 0; i < n / 2; i++) {
            a[2 * i + 1] = inputArray[i];
        }
        // value such that a[0] + ... + a[i] = k^2
        long k = 0;
        // value such that a[0] + ... + a[i+1] = l^2
        long l = 0;
        //a[0] + ... + a[i - 1] = prefixSum
        long prefixSum = 0;
        for (int i = 0; i < n; i += 2) {
            boolean found = false;
            while (!found) {
                //we are given that a[i] < 10^13
                if (k * k - prefixSum > 10_000_000_000_000L) {
                    System.out.println("No");
                    return;
                }
                // suppose a[i] is such that a[0] + ... + a[i] = k^2
                // check if a[0] + ... + a[i+1] = l^2
                long sumToiPlus1 = k * k + a[i + 1];
                while (sumToiPlus1 > l * l) {
                    l++;
                }
                if (sumToiPlus1 == l * l) {
                    found = true;
                    a[i] = k * k - prefixSum;
                    prefixSum = sumToiPlus1;
                    // the next k will be greater than the current l
                    k = l + 1;
                }
                k++;
            }
        }
        pw.println("Yes");
        for (int i = 0; i < a.length - 1; i++) {
            pw.print(a[i] + " ");
        }
        pw.println(a[a.length - 1]);
        pw.flush();
        br.close();
        pw.close();
    }
}
