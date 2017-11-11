package package7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ComplexSubject {

    public static void main(String[] args) {
        StringBuilder task = new StringBuilder();
        int m = 0;
        try (BufferedReader in2  = new BufferedReader(new InputStreamReader(System.in))) {
            task.append(in2.readLine()).append(" ").append(in2.readLine()).append(" ");
            m = Integer.parseInt(in2.readLine());
            for (int i = 0; i < m; i++) {
                task.append(in2.readLine()).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringTokenizer ff = new StringTokenizer(task.toString(), " ");
        int n = Integer.parseInt(ff.nextToken());
        TreeMap<String, ArrayList<String>> g = new TreeMap<String, ArrayList<String>>();
        TreeMap<String, Integer> subjects = new TreeMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            String temp = ff.nextToken();
            g.put(temp, new ArrayList<String>());
            subjects.put(temp, -1);
        }
        for (int i = 0; i < m; i++) {
            String tmp = ff.nextToken();
            g.get(ff.nextToken()).add(tmp);
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