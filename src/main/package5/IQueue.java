package main.package5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class IQueue {

    public static class MyQueue<T> {
        private LinkedList<T> elements;

        MyQueue() {
            elements = new LinkedList<>();
        }

        public int size() {
            return elements.size();
        }

        public void push(T value) {
            elements.add(value);
        }

        public T pop() {
            return elements.isEmpty() ? null : elements.poll();
        }

        public T peek() {
            return elements.isEmpty() ? null : elements.getFirst();
        }

        public boolean isEmpty() {
            return elements.isEmpty();
        }
    }

    public static void main(String[] args) {
        String request;
        ArrayList<String> answers = new ArrayList<>();
        MyQueue<Integer> queue = new MyQueue<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int requestsAmount = Integer.parseInt(in.readLine());
            for (int i = 0; i < requestsAmount; i++) {
                request = in.readLine();
                if (request.equals("pop")) {
                    if (queue.isEmpty()) {
                        answers.add("EMPTY");
                    } else {
                        answers.add(String.valueOf(queue.pop()));
                    }
                } else {
                    queue.push(Integer.parseInt(request.substring(5)));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for (String ans : answers) {
            System.out.println(ans);
        }
    }
}
