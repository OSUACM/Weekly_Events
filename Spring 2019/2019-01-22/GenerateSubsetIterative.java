import java.io.PrintWriter;

public class GenerateSubsetIterative {

    private static PrintWriter pw = new PrintWriter(System.out);
    private static final int N = 30;

    public static void main(String[] args) {
        printSubsets();
        pw.flush();
        pw.close();
    }

    // print all subsets of {1,2,..., N}

    private static void printSubsets() {
        // for each possible combination from 0 to 2^MAX -1
        for (int i = 0; i < 1 << N; i++) {
            pw.print("{");
            // an element j is in the subset iff the jth bit of i is set to 1.
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) == (1 << j)) {
                    pw.print(j + " ");
                }
            }
            pw.println("}");
        }
    }
}
