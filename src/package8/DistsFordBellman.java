package package8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DistsFordBellman {

    public static class Edge {
        String  from,
                to;
        double weight;

        public Edge(String from, String to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

    }

    public static void main(String[] args) {
        StringBuilder tt = new StringBuilder();
        int     n = 0,
                m = 0;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer ss = new StringTokenizer(in.readLine(), " ");
            m = Integer.parseInt(ss.nextToken());
            for (int i = 0; i < m; i++) {
                tt.append(in.readLine()).append(" ");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        StringTokenizer edges = new StringTokenizer(tt.toString(), " ");
        Edge[] g = new Edge[m];
        TreeMap<String, Integer> view = new TreeMap<String, Integer>();
        for (int i = 0, j = 0; i < m; i++) {
            int c1 = Integer.parseInt(edges.nextToken());
            String from = edges.nextToken();
            edges.nextToken();
            int c2 = Integer.parseInt(edges.nextToken());
            String to = edges.nextToken();
            if (view.get(from) == null) {
                view.put(from, j);
                j++;
            }
            if (view.get(to) == null) {
                view.put(to, j);
                j++;
            }
            g[i] = new Edge(from, to, (double) c2 / c1);
        }
        n = view.size();
        double[] dist = fordBellman(g, view, n);
        boolean easyMoney = false;
        for (Edge i : g) {
            if (i.to.equals("RUB")) {
                easyMoney = Math.abs(dist[view.get(i.from)]) * i.weight > 1;
            }
        }
        String ans = easyMoney ? "YES" : "NO";
        System.out.print(ans);
    }

    static double[] fordBellman(Edge[] g, TreeMap<String, Integer> view, int n) {
        double[] dist = new double[n];
        Arrays.fill(dist, 1);
        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : g) {
                if ((dist[view.get(edge.from)] != 1 || edge.from.equals("RUB")) && !edge.to.equals("RUB") && (dist[view.get(edge.to)]) > Math.abs(dist[view.get(edge.from)] * edge.weight) * (-1)) {
                    dist[view.get(edge.to)] = Math.abs(dist[view.get(edge.from)] * edge.weight) * (-1);
                }
            }
        }
        return dist;
    }

}
