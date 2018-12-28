import java.util.Scanner;

public class InverseFactorial {
	public static void main(String[] args) {
		final Scanner s = new Scanner(System.in);
		final String in = s.nextLine().trim();
		s.close();
		final int digits = in.length();
		int solution = 1;
		if (digits < 4) {
			solution = edgeCases(Integer.parseInt(in));
		} else {
			double curLen = 1;
			while ((int) curLen != digits) {
				solution++;
				curLen += Math.log10(solution);
			}
		}
		System.out.println(solution);
	}

	private static int edgeCases(int val) {
		switch (val) {
		case 1:
			return 1;
		case 2:
			return 2;
		case 6:
			return 3;
		case 24:
			return 4;
		case 120:
			return 5;
		case 720:
			return 6;
		}
		// the code should not get here
		return -1;
	}
}
