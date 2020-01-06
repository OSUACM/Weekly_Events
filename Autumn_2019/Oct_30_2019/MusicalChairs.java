package Autumn_2019.Oct_30_2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//https://ecna19.kattis.com/problems/musicalchairs
public class MusicalChairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<int[]> chairs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int [] pair = new int[2];
            pair[0] = i;
            pair[1] = sc.nextInt();
            chairs.add(pair);
        }

        int start = chairs.get(0)[0];
        for (int i = 0; i < n - 1; i++) {
            int opus= chairs.get(start)[1];
            int takeout = (start + opus - 1) % chairs.size();
            chairs.remove(takeout);
            start = takeout%chairs.size();
        }
        System.out.println(chairs.get(0)[0] + 1);

    }
}