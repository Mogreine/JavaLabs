package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class lab2 {

    public static void main(String[] args) {
        int size = 0;
        String numbersStr;
        try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            size = Integer.parseInt(in.readLine());
            numbersStr = in.readLine();
        }
        catch (IOException e) {
            numbersStr = "";
            e.printStackTrace();
        }

        StringTokenizer numbers = new StringTokenizer(numbersStr, " ");
        ArrayList<Integer> notUnique = new ArrayList<>(size);
        TreeSet<Integer> uniqueNumbers = new TreeSet<>();
        for (int i = 1; i <= size; i++) {
            if (!uniqueNumbers.add(Integer.parseInt(numbers.nextToken()))) {
                notUnique.add(i);
            }
        }
        if (!notUnique.isEmpty()) {
            for (Integer elem : notUnique) {
                System.out.print(elem + " ");
            }
        }
        else {
            System.out.println(0);
        }
    }

}
