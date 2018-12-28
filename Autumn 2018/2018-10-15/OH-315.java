package acmclub;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class OH315 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Event> events = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            double center = sc.nextDouble();
            double len = sc.nextDouble();
            events.add(new Event(center-len/2, true));
            events.add(new Event(center+len/2, false));
        }
        int max = 0;
        int cur = 0;
        while (!events.isEmpty()){
            if(events.poll().start){
                cur++;
                max = Math.max(cur,max);
            } else {
                cur--;
            }
        }
        System.out.println(315-max);
    }

    static class Event implements Comparable{
        boolean start;
        double time = 0;
        public Event(double time, boolean start){
            this.time = time;
            this.start = start;
        }

        @Override
        public int compareTo(Object o) {
            return Double.compare(time,((Event)o).time);
        }
    }

}
