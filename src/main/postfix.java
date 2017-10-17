package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class postfix {

    public static void main(String[] args) {
        String str = null;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            str = in.readLine();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        StringTokenizer op = new StringTokenizer(str);
        Stack<Double> numbers = new Stack<>();
        Queue<String> operations = new ArrayDeque<>();
        ArrayList<String> list = new ArrayList<>(Arrays.asList("*", "/", "-", "+"));
        while (op.hasMoreTokens()) {
            String temp = op.nextToken();
            if (list.contains(temp)) {
                operations.add(temp);
                numbers = getAnswer(numbers, operations);
            }
            else {
                numbers.push(Double.parseDouble(temp));
            }
        }
        System.out.println(String.format("%.4f", numbers.pop()));
    }

    static Stack<Double> getAnswer(Stack<Double> numbers, Queue<String> operations) {
        double secNum = numbers.pop();
        double firstNum;
        while (!numbers.isEmpty() && !operations.isEmpty()) {
            firstNum = numbers.pop();
            switch (operations.poll()) {
                case "+":
                    firstNum += secNum;
                    break;
                case "-":
                    firstNum -= secNum;
                    break;
                case "*":
                    firstNum *= secNum;
                    break;
                case "/":
                    if (secNum == 0) {
                        if (firstNum > 0) {
                            firstNum = 1e6;
                        }
                        else {
                            firstNum = -1e6;
                        }
                    }
                    else {
                        firstNum /= secNum;
                    }
                    break;
            }
            if (firstNum >= 1e6) {
                numbers.push(1e6);
            }
            else if (firstNum <= -1e6) {
                numbers.push(-1e6);
            }
            else if (Math.abs(firstNum) < 1e-6) {
                numbers.push(0.0);
            }
            else {
                numbers.push(firstNum);
            }
        }
        return numbers;
    }
}
