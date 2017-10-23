package package6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class pikabu {

    static class pair<T1, T2> {
        T1 first;
        T2 second;

        pair(T1 value1, T2 value2) {
            first = value1;
            second = value2;
        }
    }

    public static void main(String[] args) {
        long postsAmount;
        int visitsAmount;
        StringBuilder rangesStr = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer parametrs = new StringTokenizer(in.readLine(), " ");
            postsAmount = Long.parseLong(parametrs.nextToken());
            visitsAmount = Integer.parseInt(parametrs.nextToken());
            for (int i = 0; i < visitsAmount; i++) {
                rangesStr.append(in.readLine()).append(" ");
            }
        }
        catch (IOException e) {
            postsAmount = 0;
            visitsAmount = 0;
            e.printStackTrace();
        }
        StringTokenizer tokens = new StringTokenizer(rangesStr.toString(), " ");
        ArrayList<pair<Integer, Integer>> ranges = new ArrayList<>(visitsAmount);
        for (int i = 0; i < visitsAmount; i++) {
            ranges.add(new pair<>(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
        }
        TreeSet<Integer> set = new TreeSet<>();
        long uniquePosts = 0;
        for (int i = 0; i < visitsAmount; i++) {
            uniquePosts += addToSet(set, ranges.get(i));
            System.out.print(uniquePosts + " ");
        }
    }

    static long addToSet(TreeSet<Integer> set, pair<Integer, Integer> range) {
        int uniquePostsRead = 0;
        for (int i = range.first; i <= range.second; i++) {
            if (set.add(i)) {
                uniquePostsRead++;
            }
        }
        return uniquePostsRead;
    }

}
