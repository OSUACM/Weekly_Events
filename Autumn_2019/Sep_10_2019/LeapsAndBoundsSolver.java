import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;

public class LeapsAndBoundsSolver {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // read input and sort array
        int[] st = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int[] end = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        // check if possible
        for (int i = 0; i < n; i++) {
            if (st[i] > end[i]) {
                System.out.println("NO");
                return;
            }
        }
        // generate a solution by always moving the first lion. Use a treeset for quickly
        // asking the question, "which lion in the set is first">
        int moves = 0;
        TreeSet<Integer> lionPositions = new TreeSet<Integer>();
        for (int lion : st) {
            lionPositions.add(lion);
        }
        int lionIndex = 0;
        // next position without a lion in it
        int jumpTo = 0;
        // while not all lions are in their happy place
        while (lionIndex < end.length) {
            int firstLion = lionPositions.first();
            if (jumpTo < firstLion) {
                jumpTo = firstLion + 1;
            }
            // this lion is where it should be
            if (firstLion == end[lionIndex]) {
                lionIndex++;
                lionPositions.remove(firstLion);
            } else {
                /* make that first lion LEAPFROG to the end!!!
                   note jumpTo doesn't start at the index of the first lion,
                   but wherever the last lion jumped to. This is still correct since
                   it won't be past the first contiguous group of lions.
                   It also improves the speed to guaranteed O(nlogn), n is the
                   number of segments.
                */
                while (lionPositions.contains(jumpTo)) {
                    jumpTo++;
                }
                lionPositions.remove(firstLion);
                lionPositions.add(jumpTo);
                moves++;
            }
        }
        System.out.println(moves);
    }
}
