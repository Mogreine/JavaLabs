package package7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Components {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int     n = in.nextInt(),
                m = in.nextInt();
        ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; i++) {
            g.get(in.nextInt()).add(in.nextInt());
        }
        System.out.print(strongComponentsAmount(g));
    }

    static int strongComponentsAmount(ArrayList<ArrayList<Integer>> g) {
        ArrayList<Integer> order = new ArrayList<Integer>(g.size());
        int[] visited = new int[g.size()];
        Arrays.fill(visited, -1);
        tpSort(g, visited, order);
        Arrays.fill(visited, 0);
        int strongComponents = 0;
        for (int i = 0; i < g.size(); i++) {
            if (visited[order.get(i)] == 0) {
                dfsSC(g, visited, strongComponents + 1, order.get(i));
                strongComponents++;
            }
        }
        return strongComponents;
    }

    static void tpSort(ArrayList<ArrayList<Integer>> g, int[] visited, ArrayList<Integer> order) {
        for (int i = 0; i < g.size(); i++) {
            if (visited[i] == -1) {
                dfsTS(g, visited, order, i);
            }
        }
        Collections.reverse(order);
    }


    static void dfsTS(ArrayList<ArrayList<Integer>> g, int[] visited, ArrayList<Integer> order, int v) {
        visited[v] = 1;
        for (int vv = 0; vv < g.get(v).size(); vv++) {
            if (visited[g.get(v).get(vv)] == -1) {
                dfsTS(g, visited, order, g.get(v).get(vv));
            }
        }
        visited[v] = 2;
        order.add(v);
    }

    static void dfsSC(ArrayList<ArrayList<Integer>> g, int[] visited, int color, int v) {
        visited[v] = color;
        for (int i = 0; i < g.get(v).size(); i++) {
            if (visited[g.get(v).get(i)] == 0) {
                dfsSC(g, visited, color, g.get(v).get(i));
            }
        }
    }
}