package Autumn_2019.Oct_30_2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://open.kattis.com/problems/taxededitor
public class TaxedEditor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] argg = br.readLine().split(" ");
        int n = Integer.parseInt(argg[0]);
        int m = Integer.parseInt(argg[1]);
        Book[] books = new Book[n];
        for (int i = 0; i < n; i++) {
            String[] arg2 = br.readLine().split(" ");
            books[i] = new Book(Integer.parseInt(arg2[0]), Integer.parseInt(arg2[1]));
        }
        Arrays.sort(books, Comparator.comparingInt(x -> x.deadline));
        long high = 1L << 50;
        long low = 1;
        while (low != high) {
            long mid = (low + high) / 2;
            if (canRead(books, m, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(low);
    }

    private static boolean canRead(Book[] books, int m, long speed) {
        int missed = 0;
        PriorityQueue<Book> Q = new PriorityQueue<>(Comparator.comparingInt(x -> -x.pages));
        long totalPages = 0;
        for (Book book : books) {
            Q.add(book);
            totalPages += book.pages;
            if ((1.0*totalPages)/speed > book.deadline) {
                Book unread = Q.poll();
                totalPages -= unread.pages;
                missed++;
            }
        }
        return m >= missed;
    }

    public static class Book {
        int pages;
        int deadline;

        Book(int pages, int deadline) {
            this.pages = pages;
            this.deadline = deadline;
        }
    }
}
