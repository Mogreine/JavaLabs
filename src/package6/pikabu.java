package package6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class pikabu {

    static class Segment<T1 extends Comparable<T1>, T2 extends Comparable<T2>> implements Comparable<Segment<T1, T2>> {
        T1 left;
        T2 right;

        Segment(T1 value1, T2 value2) {
            left = value1;
            right = value2;
        }

        @Override
        public int compareTo(Segment<T1, T2> another) {
            int ans = this.left.compareTo(another.left);
            return ans != 0 ? ans : another.right.compareTo(this.right);
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
        ArrayList<Segment<Long, Long>> ranges = new ArrayList<>(visitsAmount);
        for (int i = 0; i < visitsAmount; i++) {
            ranges.add(new Segment<>(Long.parseLong(tokens.nextToken()), Long.parseLong(tokens.nextToken())));
        }
        TreeSet<Segment<Long, Long>> set = new TreeSet<>();
        long uniquePosts = 0;
        for (int i = 0; i < visitsAmount; i++) {
            uniquePosts = addToSet(set, ranges.get(i));
            System.out.print(uniquePosts + " ");
        }
    }

    static long addToSet(TreeSet<Segment<Long, Long>> set, Segment<Long, Long> range) {
        while (true) {
            Segment<Long, Long> f1 = set.floor(range);
            Segment<Long, Long> f2 = set.ceiling(range);
            Segment<Long, Long> ff;
            if (isCross(f1, range)) {
                ff = f1;
            }
            else if (isCross(f2, range)) {
                ff = f2;
            }
            else {
                break;
            }
            set.remove(ff);
            range.left = Math.min(range.left, ff.left);
            range.right = Math.max(range.right, ff.right);
        }
        set.add(range);
        long uniquePosts = 0;
        for (Segment<Long, Long> seg : set) {
            uniquePosts += seg.right - seg.left + 1;
        }
        return uniquePosts;
    }

    public static boolean isCross(Segment<Long, Long> s1, Segment<Long, Long> s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if ((s2.left <= s1.right && s2.right > s1.left)
                || (s2.right >= s1.left && s2.left < s1.right)
                || (s2.left >= s1.left && s2.right <= s1.right)
                || (s1.left >= s2.left && s1.right <= s2.right)) {
            return true;
        }
        else {
            return false;
        }
    }

}
