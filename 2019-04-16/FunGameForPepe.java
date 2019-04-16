package acmclub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// I made this problem :D
// (It is the first problem I've made, currently the only one)
// http://codeforces.com/gym/241489/problem/C
public class DividingGameDP {
    public String solve(long n) {
        List<Long> primes = primeFactors(n);
        // Suppose the given number is (primes[0]*primes[1]*...*primes[i-1]).
        // diff[i] holds the minimum possible value of
        // (first player score -  second player score)
        int[] diff = new int[primes.size() + 1];
        Arrays.fill(diff, 100000);
        long product = 1;
        diff[0] = 0;
        for (int i = 0; i < primes.size(); i++) {
            product *= primes.get(i);
            long K = 1;
            int count = 0;
            for (int j = i; j >= 0; j--) {
                K *= primes.get(j);
                count++;
                // first check avoids overflow
                if (K > Integer.MAX_VALUE || K * K >= product) {
                    diff[i + 1] = Math.min(diff[i + 1], count - diff[j]);
                }
            }
        }
        if (diff[primes.size()] > 0) {
            return "Lose";
        } else if (diff[primes.size()] == 0) {
            return "Tie";
        } else {
            return "Win";
        }
    }

     /* the square root of the current number. On the ith number,
     * divide out a_i. The sequence a_i must be non-increasing.
     * You win if you divide by less primes than the opponent or
     * divide by the same amount and make the last move
     * n <= 10^18
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        sc.close();
        System.out.println(new DividingGameDP().solve(n));
    }

    // returns a list, sorted in ascending order, of
    // all the prime factors of a number
    private static List<Long> primeFactors(long n) {
        List<Long> primes = new ArrayList<>();
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                primes.add(i);
                n /= i;
                i--;
            }
        }
        if (n != 1) {
            primes.add(n);
        }
        return primes;
    }
}
