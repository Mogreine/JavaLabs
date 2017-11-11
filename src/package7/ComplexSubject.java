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
        boolean isCircle = false;
        TreeMap<String, Integer> u = new TreeMap<String, Integer>(subjects);
        for (Map.Entry<String, Integer> sub : subjects.entrySet()) {
            if (sub.getValue() == -1) {
                if (dfs(g, subjects, u, sub.getKey()) == -1) {
                    isCircle = true;
                    break;
                }
            }
        }
        if (isCircle) {
            System.out.println("Impossible");
        }
        else {
            int max = Collections.max(subjects.values());
            for (Map.Entry<String, Integer> sub : subjects.entrySet()) {
                if (sub.getValue() == max) {
                    System.out.print(sub.getKey() + " ");
                }
            }
        }
        in.close();
    }

    static int dfs(TreeMap<String, ArrayList<String>> g, TreeMap<String, Integer> subjects, TreeMap<String, Integer> u, String v) {
        u.put(v, 1);
        boolean isCircle = false;
        int dd = 0;
        for (String vv : g.get(v)) {
            if (u.get(vv) == 1) {
                return -1;
            }
            else if (subjects.get(vv) == -1) {
                int smth = dfs(g, subjects, u, vv);
                if (smth == -1) {
                    isCircle = true;
                }
                dd = Math.max((smth), dd);
            }
            else  {
                dd = Math.max(subjects.get(vv) + 1, dd);
            }
            if (isCircle) {
                return -1;
            }
        }
        subjects.put(v, dd);
        u.put(v, 2);
        return dd + 1;
    }

}