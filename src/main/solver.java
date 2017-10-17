package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class solver {

    public static void main(String[] args) {
        String str = null;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            str = in.readLine();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        String answer = isCorrect(str) ? "YES" : "NO";
        System.out.println(answer);
    }

    static boolean isCorrect(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ')' || str.charAt(i) == '}' || str.charAt(i) == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (Math.abs(stack.peek() - str.charAt(i)) > 2) {
                    return false;
                }
                else {
                    stack.pop();
                }
            }
            else {
                stack.push(str.charAt(i));
            }
        }
        return stack.isEmpty();
    }

}
