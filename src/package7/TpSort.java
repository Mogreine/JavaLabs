package package7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class TpSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int     n = in.nextInt(),
                m = in.nextInt();
        ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Integer>());
        }
        for (int z = 0; z < m; z++) {
            g.get(in.nextInt()).add(in.nextInt());
        }
        int[] visited = new int[n];
        Arrays.fill(visited, 0);
        ArrayList<Integer> order = new ArrayList<Integer>(n);
        tpSort(g, visited, order);
        for (int i : order) {
            System.out.print(i + " ");
        }
        in.close();
    }

    static boolean dfs(ArrayList<ArrayList<Integer>> g, int[] visited, ArrayList<Integer> order, int v) {
        visited[v] = 1;
        boolean isCircle = false;
        for (int vv = 0; vv < g.get(v).size() && !isCircle; vv++) {
            if (visited[g.get(v).get(vv)] == 1) {
                return true;
            }
            if (visited[g.get(v).get(vv)] == 0) {
                isCircle = dfs(g, visited, order, g.get(v).get(vv));
            }
        }
        visited[v] = 2;
        order.add(v);
        return isCircle;
    }

    static void tpSort(ArrayList<ArrayList<Integer>> g, int[] visited, ArrayList<Integer> order) {
        boolean isCircle = false;
        for (int i = 0; i < g.size() && !isCircle; i++) {
            if (visited[i] == 0) {
                isCircle = dfs(g, visited, order, i);
            }
        }
        if (isCircle) {
            order.clear();
            order.add(-1);
        }
        else {
            Collections.reverse(order);
        }
    }
}
