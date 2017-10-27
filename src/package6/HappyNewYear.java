package package6;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class HappyNewYear {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int     boxes = in.nextInt(),
                iterationAmount = in.nextInt();
        ArrayList<ArrayList<Integer>> iterations = new ArrayList<ArrayList<Integer>>(iterationAmount);
        for (int i = 0; i < iterationAmount; i++) {
            iterations.add(new ArrayList<Integer>(3));
            for (int j = 0; j < 3; j++) {
                iterations.get(i).add(in.nextInt());
            }
        }
        TreeMap<Integer, Integer> box = new TreeMap<>();
        for (int i = 0; i < iterations.size(); i++) {
            int l = iterations.get(i). get(0);
            int r = iterations.get(i). get(1) + 1;
            int k = iterations.get(i). get(2);
            if (box.get(l) == null) {
                box.put(l, k);
            }
            else {
                box.put(l, k + box.get(l));
            }
            if (r <= boxes) {
                if (box.get(r) == null) {
                    box.put(r, -k);
                } else {
                    box.put(r, box.get(r) - k);
                }
            }
        }
        long    maxSweets = 0,
                maxSweetsInd = 0,
                currentSweet = 0;
        boolean afterMax = false;
        for (Map.Entry<Integer, Integer> pair : box.entrySet()) {
            currentSweet += pair.getValue();
            if (afterMax) {
                maxSweetsInd = pair.getKey() - 1;
                afterMax = false;
            }
            if (currentSweet > maxSweets) {
                maxSweets = currentSweet;
                maxSweetsInd = pair.getKey();
                afterMax = true;
            }
        }
        if (box.lastKey() == maxSweetsInd) {
            maxSweetsInd = boxes;
        }
        System.out.print(maxSweetsInd);
        in.close();
    }

}
