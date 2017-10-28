package package6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class MarshrutkaWaiting {

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
        TreeMap<Integer, Integer> marshrutki = new TreeMap<>();
        int max = 0;
        for (int i = 0; i < size; i++) {
            int marshNum = Integer.parseInt(numbers.nextToken());
            Integer prevTime = marshrutki.put(marshNum, i);
            if (prevTime != null) {
                int temp = i - prevTime;
                if (temp > max) {
                    max = temp;
                }
            }
        }
        System.out.print(max);
    }

}
