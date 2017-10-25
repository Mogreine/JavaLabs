package main.package5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class IsEasy {

    public static class WeiredQueue {
        int size;

        LinkedList<Integer> elements;
        LinkedList<Integer> minimums;

        WeiredQueue() {
            size = 0;
            elements = new LinkedList<>();
            minimums = new LinkedList<>();
        }

        void push(int value) {
            elements.add(value);

        }
    }

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
        LinkedList<Integer> minimums = new LinkedList<>();
        int     sum = 0,
                prev = 0,
                cur = 0;
        prev = startElement;
        minimums.add(prev);
        for (int i = 1; i < amount; i++) {
            int num = (int) (((long) prev * x + y) % 1000000007);
            if (minimums.size() == window) {

            }
        }
        return sum;
    }

}
