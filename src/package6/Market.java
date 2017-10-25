package package6;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Market {

    static class pair<T1 extends Comparable<T1>, T2 extends Comparable<T2>> implements Comparable<pair<T1, T2>>{
        T1 first;
        T2 second;

        public pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(pair<T1, T2> o) {
            return first.compareTo(o.first) != 0 ? first.compareTo(o.first) : second.compareTo(o.second);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cashboxAmount = in.nextInt();
        ArrayList<Integer> serviceTime = new ArrayList<>(cashboxAmount);
        TreeSet<pair<Integer, Integer>> queue = new TreeSet<>();
        for (int i = 0; i < cashboxAmount; i++) {
            serviceTime.add(in.nextInt());
            queue.add(new pair<>(0, i));
        }
        int buyerAmount = in.nextInt();
        ArrayList<Integer> purchases = new ArrayList<>(buyerAmount);
        for (int i = 0; i < buyerAmount; i++) {
            purchases.add(in.nextInt());
        }
        ArrayList<Integer> output = new ArrayList<>(buyerAmount);
        for (int i = 0; i < purchases.size(); i++) {
            pair<Integer, Integer> lowest = queue.pollFirst();
            lowest.first += purchases.get(i) * serviceTime.get(lowest.second);
            queue.add(lowest);
            output.add(lowest.second + 1);
        }
        for (int i = 0; i < output.size(); i++) {
            System.out.print(output.get(i) + " ");
        }
    }

}