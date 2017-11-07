package package7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

public class Components {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int     n = in.nextInt(),
                m = in.nextInt();
        ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>(n);
        ArrayList<ArrayList<Integer>> gR = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Integer>());
            gR.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            int v1 = in.nextInt(), v2 = in.nextInt();
            g.get(v1).add(v2);
            gR.get(v2).add(v1);
        }
        System.out.print(strongComponentsAmount(g, gR));
    }

    static int strongComponentsAmount(ArrayList<ArrayList<Integer>> g, ArrayList<ArrayList<Integer>> gR) {
        ArrayList<Integer> order = new ArrayList<Integer>(g.size());
        int[] visited = new int[g.size()];
        Arrays.fill(visited, -1);
        tpSort(g, visited, order);
        Arrays.fill(visited, -1);
        int strongComponents = 0;
        for (int i = 0; i < gR.size(); i++) {
            if (visited[order.get(i)] == -1) {
                dfs2(gR, visited, strongComponents, order.get(i));
                strongComponents++;
            }
        }
        return strongComponents;
    }

    static void tpSort(ArrayList<ArrayList<Integer>> g, int[] visited, ArrayList<Integer> order) {
        for (int i = 0; i < g.size(); i++) {
            if (visited[i] == -1) {
                dfs1(g, visited, order, i);
            }
        }
        Collections.reverse(order);
    }


    static void dfs1(ArrayList<ArrayList<Integer>> g, int[] visited, ArrayList<Integer> order, int v) {
        visited[v] = 1;
        for (int vv = 0; vv < g.get(v).size(); vv++) {
            if (visited[g.get(v).get(vv)] == -1) {
                dfs1(g, visited, order, g.get(v).get(vv));
            }
        }
        order.add(v);
    }

    static void dfs2(ArrayList<ArrayList<Integer>> gR, int[] visited, int color, int v) {
        visited[v] = color;
        for (int i = 0; i < gR.get(v).size(); i++) {
            final int ii = i;
            if (visited[gR.get(v).get(i)] == -1) {
                dfs2(gR, visited, color, gR.get(v).get(i));
            }
        }
    }
}