import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class G {

    private static boolean goodGrid(int[][] M, int splitter){
        List<List<Integer>> ones_in_row = new ArrayList<>(M.length);
        for(int i = 0; i < M.length; i++){
            int[] row = M[i];
            ones_in_row.add(new ArrayList<>(row.length));
            for(int j=0; j<row.length; j++){
                int cell = row[j];
                if(cell >= splitter) {
                    ones_in_row.get(i).add(j);
                }
            }
        }
//        System.out.println(splitter + " " + ones_in_row);

        boolean[][] colPairs = new boolean[M[0].length][M[0].length];

        for(List<Integer> ones_list : ones_in_row){
            for(int j1=0; j1<ones_list.size(); j1++){
                for(int j2=j1+1; j2<ones_list.size(); j2++){
                    int x = ones_list.get(j1);
                    int y = ones_list.get(j2);
                    if(colPairs[x][y]){
                        return true;
                    }
                    colPairs[x][y] = true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = in.readLine().split(" ");
        assert firstLine.length == 2;
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        int[][] M = new int[n][m];
        for(int i=0; i<n; i++){
            String[] line = in.readLine().split(" ");
            for(int j=0; j<m; j++){
                M[i][j] = Integer.parseInt(line[j]);
            }
        }

        int hi = 1_000_000_001;
        int lo = 0;

        while(hi - lo > 1){
            int mid = (hi+lo)/2;
            if(goodGrid(M, mid)){
                lo = mid;
//                System.out.println("good: " + mid);
            } else {
                hi = mid;
//                System.out.println("bad: " + mid);
            }
        }

        System.out.println(lo);
    }
}
