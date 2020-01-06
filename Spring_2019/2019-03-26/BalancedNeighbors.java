import java.util.*;

//https://atcoder.jp/contests/agc032/tasks/agc032_b
public class BalancedNeighbors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Set<Integer>> groups = new ArrayList<>();
        if (n % 2 == 0) {
            for (int i = 1; i <= n / 2; i++) {
                Set<Integer> group = new HashSet<Integer>();
                group.add(i);
                group.add(n + 1 - i);
                groups.add(group);
            }
        } else {
            for (int i = 0; i <= n / 2; i++) {
                Set<Integer> group = new HashSet<Integer>();
                if (i > 0) {
                    group.add(i);
                }
                group.add(n - i);
                groups.add(group);
            }
        }
        Set<Pair> edges = new HashSet<>();
        for (int i = 0; i < groups.size(); i++) {
            for (int j : groups.get(i)) {
                for (int k = 0; k < groups.size(); k++) {
                    if (k != i) {
                        for (int l : groups.get(k)) {
                            int min = Math.min(j, l);
                            int max = Math.max(j, l);
                            edges.add(new Pair(min, max));
                        }
                    }
                }
            }
        }
        System.out.println(edges.size());
        for (Pair e : edges) {
            System.out.println(e.key + " " + e.value);
        }
    }

    private static class Pair {
        int key;
        int value;

        public Pair(int k, int v) {
            key = k;
            value = v;
        }

        @Override
        public boolean equals(Object obj) {
            return key == ((Pair) (obj)).key && value == ((Pair) (obj)).value;
        }

        @Override
        public int hashCode() {
            return key * 100 + value;
        }
    }
}
