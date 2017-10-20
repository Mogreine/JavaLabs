package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Petrovich {

    public static class Lombard {
        int allItemsCost = 0;

        Stack<Integer> items;
        Stack<Integer> maxCosts;
        ArrayList<String> answers;
        ArrayList<String> requests;

        Lombard(String[] requestsArray) {
            allItemsCost = 0;
            maxCosts = new Stack<>();
            items = new Stack<>();
            answers = new ArrayList<>(requestsArray.length);
            requests = new ArrayList<>(Arrays.asList(requestsArray));
        }

        public void setRequests(ArrayList<String> requests) {
            this.requests = requests;
        }

        public ArrayList<String> getAnswers() {
            for (int i = 0; i < requests.size(); i++) {
                String answer = getAnswer(requests.get(i));
                if (!answer.equals("")) {
                    answers.add(answer);
                }
            }
            return answers;
        }

        public String getAnswer(String request) {
            String answer = "";
            switch (request.charAt(0)) {
                case '1':
                    int cost = Integer.parseInt(request.substring(2));
                    if (maxCosts.empty()) {
                        maxCosts.push(cost);
                    }
                    else {
                        maxCosts.push(Math.max(cost, items.peek()));
                    }
                    allItemsCost += cost;
                    items.push(cost);
                    break;
                case '2':
                    allItemsCost -= items.pop();
                    maxCosts.pop();
                    break;
                case '3':
                    answer += String.valueOf(allItemsCost);
                    break;
                case '4':
                    if (items.size() == 0) {
                        return "0";
                    }
                    answer += String.valueOf(maxCosts.peek());
                    break;
                case '5':
                    if (items.size() == 0) {
                        return "0";
                    }
                    answer += String.valueOf(items.search(maxCosts.peek()));
                    break;
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        int requestsAmount = 0;
        String[] requests = new String[requestsAmount];
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            requestsAmount = Integer.parseInt(in.readLine());
            requests = new String[requestsAmount];
            for (int i = 0; i < requestsAmount; i++) {
                requests[i] = in.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Lombard lombard = new Lombard(requests);
        ArrayList<String> answers = lombard.getAnswers();
        for (int i = 0; i < answers.size(); i++) {
            System.out.println(answers.get(i));
        }
    }

}
