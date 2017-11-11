package package7;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Bridges {

    public static class Edge implements Comparable<Edge> {
        Integer v1, v2;

        public Edge(Integer v1, Integer v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        public int compareTo(Edge another) {
            return v1.compareTo(another.v1) != 0 ? v1.compareTo(another.v1) : v2.compareTo(another.v2);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int     n = in.nextInt(),
                m = in.nextInt();
        ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m && n > 1; i++) {
            int v1 = in.nextInt(), v2 = in.nextInt();
            g.get(v1).add(v2);
            g.get(v2).add(v1);
        }
        TreeSet<Edge> bridges = bridges(g);
        if (bridges.size() == 0) {
            System.out.print(-1);
        }
        else {
            for (Edge bridge : bridges) {
                System.out.println(bridge.v1 + " " + bridge.v2);
            }
        }
        in.close();
    }

    static TreeSet<Edge> bridges(ArrayList<ArrayList<Integer>> g) {
        TreeSet<Edge> bridges = new TreeSet<>();
        int[] u = new int[g.size()];
        int[] deep = new int[g.size()];
        int[] deepUp = new int[g.size()];
        for (int i = 0; i < g.size(); i++) {
            if (u[i] == 0) {
                dfs(g, bridges, u, deep, deepUp, i, -1);
            }
        }
        return bridges;
    }

    static void dfs(ArrayList<ArrayList<Integer>> g,
                    TreeSet<Edge> bridges,
                    int[] u,
                    int[] deep,
                    int[] deepUp,
                    int v,
                    int p) {
        u[v] = 1;
        deep[v] = deepUp[v] = (p == -1 ? 0 : deep[p] + 1);
        for (int i = 0; i < g.get(v).size(); i++) {
            if (u[g.get(v).get(i)] == 0) {
                dfs(g, bridges, u, deep, deepUp, g.get(v).get(i), v);
                deepUp[v] = Math.min(deepUp[v], deepUp[g.get(v).get(i)]);
                if (deepUp[g.get(v).get(i)] > deep[v]) {
                    if (v <= g.get(v).get(i)) {
                        bridges.add(new Edge(v, g.get(v).get(i)));
                    }
                    else {
                        bridges.add(new Edge(g.get(v).get(i), v));
                    }
                }
            }
            else if (g.get(v).get(i) != p) {
                deepUp[v] = Math.min(deepUp[v], deep[g.get(v).get(i)]);
            }
        }
    }

}