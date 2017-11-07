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
            String tmp = subs.nextToken();
            g.get(subs.nextToken()).add(tmp);
        }
        //ArrayList<String> levels = new ArrayList<String>();
        /*for (Map.Entry<String, Integer> sub : subjects.entrySet()) {
            if (sub.getValue() == -1) {
                dfs(g, subjects, sub.getKey());
            }
        }*/

        for (int i = 0; i < n; i++) {
            for (Map.Entry<String, ArrayList<String>> ss : g.entrySet()) {
                if (ss.getValue().size() == i) {
                    dfs(g, subjects, ss.getKey());
                }
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

    static int dfs(TreeMap<String, ArrayList<String>> g, TreeMap<String, Integer> subjects, String v) {
        int dd = 0;
        for (String vv : g.get(v)) {
            if (subjects.get(vv) == -1) {
                dd = Math.max(dfs(g, subjects, vv), dd);
            }
            else  {
                dd = Math.max(subjects.get(vv) + 1, dd);
            }
        }
        subjects.put(v, dd);
        return dd + 1;
    }

}