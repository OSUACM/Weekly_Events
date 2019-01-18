import java.io.PrintWriter;
import java.util.Stack;

public class GenerateSubsetRecursive {

    private static PrintWriter pw = new PrintWriter(System.out);
    private static final int MAX_LEN = 3;
    boolean works = false;

    public static void main(String[] args) {
        Stack<Integer> cur_subset = new Stack<>();
        check_subsets(cur_subset, 0);
        pw.flush();
        pw.close();
    }

    /**
     * for each subset S of {min,min+1,..., MAX - 1}:
     * print a set corresponding to the elements in [cur_subset, S]
     */
    private static void check_subsets(Stack<Integer> cur_subset, int min) {
        if (min == MAX_LEN) {
            check_stack(cur_subset);
        } else {
            check_subsets(cur_subset, min + 1);
            cur_subset.add(min);
            check_subsets(cur_subset, min + 1);
            cur_subset.pop();
        }
    }

    /**
     * check if a stack satisfies the condition and print the stack
     */
    private static void check_stack(Stack<Integer> st) {
        pw.print("{");
        for (int i : st) {
            pw.print(i + " ");
        }
        pw.println("}");
    }
}
