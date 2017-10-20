package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;

public class IHeap {

    public static void main(String[] args) {
        Integer[] arr;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int size = Integer.parseInt(in.readLine());
            arr = new Integer[size];
            StringTokenizer numbers = new StringTokenizer(in.readLine(), " ");
            for (int i = 0; i < size; i++) {
                arr[i] = Integer.parseInt(numbers.nextToken());
            }
        }
        catch (IOException e) {
            arr = new Integer[]{0};
            e.printStackTrace();
        }
        System.out.println(heapify(arr));
    }

    public static int heapify(Integer[] array) {
        int topCallsAmount = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            topCallsAmount = down(array, i, topCallsAmount);
        }
        return topCallsAmount;
    }

    public static int top(Integer[] array, int index, int topCallsAmount) {
        for (int i = index; (i - 1) / 2 >= 0 && i != 0; i = (i - 1) / 2) {
            if (array[(i - 1) / 2] < array[i]) {
                swap(array, i, (i - 1) / 2);
                topCallsAmount++;
            }
        }
        return topCallsAmount;
    }

    public static int down(Integer[] array, int index, int topCallsAmount) {
        for (int i = index; i * 2 + 1 <= array.length - 1; ) {
            int indMax;
            if (i * 2 + 1 + 1 <= array.length - 1) {
                indMax = array[i * 2 + 1].compareTo(array[i * 2 + 2]) > 0 ? i * 2 + 1 : i * 2 + 2;
            }
            else {
                indMax = i * 2 + 1;
            }
            if (array[indMax].compareTo(array[i]) > 0) {
                swap(array, i, indMax);
                topCallsAmount++;
            }
            i = indMax;
        }
        return topCallsAmount;
    }

    public static  <T> void swap(T[] array, int ind1, int ind2) {
        T temp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = temp;
    }

}
