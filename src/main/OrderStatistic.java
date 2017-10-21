package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class OrderStatistic {

    public static void main(String[] args) {
        int     x,
                y,
                k,
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
        k = Integer.parseInt(separatedData.nextToken());
        startElement = Integer.parseInt(separatedData.nextToken());
        x = Integer.parseInt(separatedData.nextToken());
        y = Integer.parseInt(separatedData.nextToken());
        long minElementsSum = fillArray(amount, k, startElement, x, y);
        System.out.print(minElementsSum);
    }

    static long fillArray(int amount, int k, int startElement, int x, int y) {
        Integer[] array = new Integer[amount];
        Integer[] additionalArray = new Integer[amount];
        long sum = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        array[0] = startElement;
        heap.add(startElement);
        if (k == 1) {
            sum += startElement;
        }
        for (int i = 1; i < amount; i++) {
            array[i] = (int) (((long) array[i - 1] * x + y) % 1000000007);
            heap.add(array[i]);
            sum += findOrderStatistic(heap.toArray(additionalArray), heap.size(), k);
        }
        return sum;
    }

    static int findOrderStatistic(Integer[] array, int size, int k) {
        if (k > size) {
            return -1;
        }
        int left = 0, right = size - 1;
        while (true) {
            int mid = partition(array, left, right);
            if (mid == k) {
                return array[mid];
            } else if (k < mid) {
                right = mid;
            } else {
                k -= mid + 1;
                left = mid + 1;
            }
        }
    }

    static int partition(Integer[] array, int left, int right) {
        int i = left + 1, j = right;
        while (true) {
            while (i < right && array[i] < array[left])
                i++;
            while (j > left && array[j] > array[left])
                j--;
            if (i >= j)
                break;
        }
        return j;
    }

}
