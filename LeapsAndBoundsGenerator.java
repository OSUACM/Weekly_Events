import java.util.HashSet;
import java.util.Set;

public class LeapsAndBoundsGenerator {
    public static void main(String[] args) {
        int n = 100;
        System.out.println(n);
        Set<Integer> lions = new HashSet<>();
        Set<Integer> deer = new HashSet<>();
        for (int i = 0; i < n - 1; i++) {
            int next = 0;
            while (!lions.contains(next)) {
                next = (int) (Math.random() * 850);
            }
            lions.add(next);
            int nextD = next + (int) (Math.random() * 100);
            while (!deer.contains(nextD)) {
                nextD = next +(int) (Math.random() * 100);
            }
            deer.add(next);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(lions);
        sb.append(System.lineSeparator());
        sb.append(deer);
        System.out.println(sb);
        for (int lion : lions) {
            System.out.print(lion + " ");
        }
        System.out.println();
        for(int dee : deer){
            System.out.print(deer + " ");
        }
        System.out.println();
    }
}
