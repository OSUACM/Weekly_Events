import java.util.*;
import java.lang.*;

public class PrimeFactor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Print all primes up to: ");
        int n = sc.nextInt();
        primesNaive(n);
        System.out.println();
        primesSieve(n);
        System.out.println();
        primesFast(n);
        System.out.println();
        sc.close();
    }

    // How to make this faster? We want to find if there
    // exists an integer such that a*b=n.
    private static void primesNaive(int n) {
        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                System.out.print(i + " ");
            }
        }
    }

    // Time O(n*loglogn)
    private static void primesSieve(int n) {
        boolean[] isComposite = new boolean[n + 1];
        for (int i = 2; i * i <= n; i++) {
            if (!isComposite[i]) {
                for (int j = i*2; j <= n; j += i) {
                    isComposite[j] = true;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (!isComposite[i]) {
                System.out.print(i + " ");
            }
        }
    }


    /**
     * Print primes up to and including n. Takes only O(n) time!
     * Also generates an array of the minimum factor for every number
     * from 1 to n, which can be used to find all factors of any number up to n.
     */
    private static void primesFast(int n) {
        List<Integer> primes = new ArrayList<>();
        // minFact[i] contains the minimum prime factor of i.
        int[] minFact = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            if (minFact[i] == 0) {
                primes.add(i);
                minFact[i] = i;
            }
            /* Find multiples of i where primes[j] is the minimum prime factor. This loop will go exactly once for each
             composite number. How it works: given a number i with prime factorization p1*...*pn, where p1<=...<=pn,
             we say that, for each prime p <= p1, the number i*p is composite with minimum prime factor p.
             Why this gets to every number exactly once: the number p1*...*pn will be generated only by p2*...*pn.
             That's because all of it's prime factors need to be higher than it. */
            for (int j = 0; j < primes.size() && primes.get(j) <= minFact[i] && i * primes.get(j) <= n; ++j) {
                minFact[i * primes.get(j)] = primes.get(j);
            }
        }
        for (int p : primes) {
            System.out.print(p + " ");
        }
    }
}
