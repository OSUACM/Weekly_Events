import java.io.*;
import java.util.Scanner;

public class R2 {
    public static void main(String[] args) {
        //create object to read input - never make more than 1!
        Scanner sc = new Scanner(System.in);
        int r1 = sc.nextInt();
        int s = sc.nextInt();
        System.out.println(s * 2 - r1);
    }

    //Advanced: Fast input/output
    //You won't need it unless you're tackling problems with ~10^6 pieces
    //of input and output, but in that case, it's your only option, unfortunately
    public static void fastmain(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //read line
        String[] inputLine = br.readLine().split(" ");
        //convert line to integers
        int r1 = Integer.parseInt(inputLine[0]);
        int s = Integer.parseInt(inputLine[1]);
        //you can also use the StringBuilder class, it may be simpler
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(s * 2 - r1);
        //you're always expected to end with a newline
        bw.newLine();
    }
}

