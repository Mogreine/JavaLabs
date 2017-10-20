package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class MaxRightElement {

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
        int[] arr = fillArray(amount, startElement, x, y);
        long sum = getIndexSum(arr);
        System.out.println(sum);
    }

    static int[] fillArray(int amount, int startElement, int x, int y) {
        int[] arr = new int[amount];
        arr[0] = startElement;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = (int) (((long) arr[i - 1] * x + y) % 1000000007);
        }
        return arr;
    }

    static long getIndexSum(int[] array) {
        Stack<Integer> sequence = new Stack<>();
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (sequence.isEmpty() || array[i] <= sequence.peek()) {
                sequence.push(array[i]);
            }
            else {
                while (!sequence.empty() && sequence.peek() < array[i]) {
                    sequence.pop();
                    sum += i;
                }
                sequence.push(array[i]);
            }
        }
        while (!sequence.empty()) {
            sum += -1;
            sequence.pop();
        }
        return sum;
    }

}