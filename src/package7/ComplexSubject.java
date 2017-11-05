package package7;

import java.util.*;

public class ComplexSubject {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        TreeMap<String, ArrayList<String>> g = new TreeMap<String, ArrayList<String>>();
        TreeMap<String, Integer> subjects = new TreeMap<String, Integer>();
        StringTokenizer subs = new StringTokenizer(in.nextLine(), " ");
        while (subs.hasMoreTokens()) {
            String temp = subs.nextToken();
            g.put(temp, new ArrayList<String>());
            subjects.put(temp, -1);
        }
        int m = in.nextInt();
        in.nextLine();
        for (int i = 0; i < m; i++) {
            subs = new StringTokenizer(in.nextLine(), " ");
            g.get(subs.nextToken()).add(subs.nextToken());
        }
        for (Map.Entry<String, Integer> sub : subjects.entrySet()) {
            if (sub.getValue() == -1) {
                dfs(g, subjects, sub.getKey(), 0);
            }
        }
        int max = Collections.max(subjects.values());
        for (Map.Entry<String, Integer> sub : subjects.entrySet()) {
            if (sub.getValue() == max) {
                System.out.print(sub.getKey() + " ");
            }
        }
        in.close();
    }

    static void dfs(TreeMap<String, ArrayList<String>> g, TreeMap<String, Integer> subjects, String v, int deep) {
        if (deep > subjects.get(v)) {
            subjects.put(v, deep);
        }
        for (String vv : g.get(v)) {
            dfs(g, subjects, vv, deep + 1);
        }
    }

}