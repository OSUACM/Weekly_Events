package codeforces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SingleUseStones {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int l = Integer.parseInt(br.readLine().split(" ")[1]);
		int[] j = Arrays.stream(br.readLine().split(" ")).mapToInt(x->Integer.parseInt(x)).toArray();
		int s = Arrays.stream(Arrays.copyOfRange(j, 0, l)).sum();
		int minW = s;
		for (int i = 0; i < j.length-l; i++) {
			minW = Math.min(minW, s);
			s+=j[i+l]-j[i];
		}
		System.out.println(Math.min(minW, s));
	}
}
