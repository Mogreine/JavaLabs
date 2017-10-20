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
        long sum = getIndexSum(amount, startElement, x, y);
        System.out.println(sum);
    }

    static long getIndexSum(int amount, int startElement, int x, int y) {
        Stack<Integer> sequence = new Stack<>();
        int[] array = new int[amount];
        array[0] = startElement;
        sequence.push(startElement);
        long sum = 0;
        for (int i = 1; i < array.length; i++) {
            array[i] = (int) (((long) array[i - 1] * x + y) % 1000000007);
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