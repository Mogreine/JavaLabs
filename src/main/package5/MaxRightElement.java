package main.package5;

import java.util.Scanner;
import java.util.Stack;


public class MaxRightElement {

    public static void main(String[] args) {
        int     x,
                y,
                startElement,
                amount;
        Scanner in = new Scanner(System.in);
        amount = in.nextInt();
        startElement = in.nextInt();
        x = in.nextInt();
        y = in.nextInt();
        in.close();
        long sum = getIndexSum(amount, startElement, x, y);
        System.out.println(sum);
    }

    static long getIndexSum(int amount, int startElement, int x, int y) {
        Stack<Integer> sequence = new Stack<>();
        int prevElem = startElement;
        int actualElement = 0;
        sequence.push(startElement);
	    long sum = 0;
        for (int i = 1; i < amount; i++, prevElem = actualElement) {
            actualElement = (int) (((long) prevElem * x + y) % 1000000007);
            if (sequence.isEmpty() || actualElement <= sequence.peek()) {
                sequence.push(actualElement);
            }
            else {
                while (!sequence.isEmpty() && sequence.peek() < actualElement) {
                    sequence.pop();
                    sum += i;
                }
                sequence.push(actualElement);
            }
        }
        sum -= sequence.size();
        return sum;
    }

}