package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class IsEasy {

    public static void main(String[] args) {
        int     x,
                y,
                window,
                startElement,
                amount;
        StringBuilder data = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            data.append(in.readLine()).append(" ");
            data.append(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringTokenizer separatedData = new StringTokenizer(data.toString(), " ");
        amount = Integer.parseInt(separatedData.nextToken());
        window = Integer.parseInt(separatedData.nextToken());
        startElement = Integer.parseInt(separatedData.nextToken());
        x = Integer.parseInt(separatedData.nextToken());
        y = Integer.parseInt(separatedData.nextToken());
        int minElementsSum = getSumMin(amount, window, startElement, x, y);
        System.out.println(minElementsSum);
    }

    static int getSumMin(int amount, int window, int startElement, int x, int y) {
        LinkedList<Integer> arr = new LinkedList<>();
        ArrayList<Integer> minimums = new ArrayList<>();
        arr.add(startElement);
        for (int i = 1; i < amount; i++) {
            if (arr.size() == window) {
                minimums.add(arr.pollFirst());
            }
            arr.add((int) ((long) arr.getLast() * x + y) % 1000000007);
            for (int j = arr.size() - 2, counter = 1; j >= 0 && counter < window; j--, counter++) {
                if (arr.getLast() < arr.get(j)) {
                    arr.set(j, arr.getLast());
                }
            }
        }
        int sum = arr.pollFirst();
        for (Integer minimum : minimums) {
            sum += minimum;
        }
        return sum;
    }

}
