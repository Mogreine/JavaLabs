package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;



public class MaxRightElement {

    public static class pair {
        int value;
        int index;

        pair() {
            this(0, 0);
        }

        pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        int     x,
                y,
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
        startElement = Integer.parseInt(separatedData.nextToken());
        x = Integer.parseInt(separatedData.nextToken());
        y = Integer.parseInt(separatedData.nextToken());
        pair[] arr = fillArray(amount, startElement, x, y);
        int sum = getIndexSum(arr);
        System.out.println(sum);
    }

    static pair[] fillArray(int amount, int startElement, int x, int y) {
        pair[] arr = new pair[amount];
        arr[0] = new pair(startElement, 0);
        for (int i = 1; i < arr.length; i++) {
            long tempLong = ((long) arr[i - 1].value * x + y) % 1000000007;
            arr[i] = new pair((int) tempLong, i);
        }
        return arr;
    }

    static int getIndexSum(pair[] array) {
        Stack<pair> sequence = new Stack<>();
        int[] indexArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (sequence.isEmpty() || array[i].value <= sequence.peek().value) {
                sequence.push(array[i]);
            }
            else {
                while (!sequence.empty() && sequence.peek().value < array[i].value) {
                    indexArray[sequence.pop().index] = i;
                }
                sequence.push(array[i]);
            }
        }
        while (!sequence.empty()) {
            indexArray[sequence.pop().index] = -1;
        }
        int sum = 0;
        for (int i = 0; i < indexArray.length; i++) {
            sum += indexArray[i];
        }
        return sum;
    }

}
