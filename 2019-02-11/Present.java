import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://codeforces.com/problemset/problem/460/C
public class Present {
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int days = Integer.parseInt(params[1]);
        int w = Integer.parseInt(params[2]);
        a = Arrays.stream(br.readLine().split(" "))
                .mapToInt(x -> Integer.parseInt(x)).toArray();
        int min = 0; // ans is at least min
        int max = 1 << 30; // ans is at most max
        while (min != max) {
            int mid = (min + max + 1) / 2;
            if (canHaveHeight(mid, w, days)) {
                min = mid;
            } else {
                max = mid - 1;
            }
        }
        System.out.println(min);
        br.close();
    }

    //check if we can achieve an answer of mid
    private static boolean canHaveHeight(int mid, int width, int days) {
        int curWater = 0;
        int[] segmentEnds = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            curWater -= segmentEnds[i];
            int waterRequired = mid - (curWater + a[i]);
            if (waterRequired > 0) {
                days -= waterRequired;
                curWater += waterRequired;
                if (days < 0) {
                    return false;
                } else {
                    if (i + width < a.length) {
                        segmentEnds[i + width] += waterRequired;
                    }
                }
            }
        }
        return true;
    }
}
