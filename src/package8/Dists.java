package package8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dists {

    public static class Edge {
        int from,
                to,
                weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

    }

    public static class Vertex implements Comparable<Vertex> {
        Integer number,
                distTo;

        public Vertex(int number, int distTo) {
            this.number = number;
            this.distTo = distTo;
        }

        @Override
        public int compareTo(Vertex o) {
            int distComp = distTo.compareTo(o.distTo);
            return distComp == 0 ? number.compareTo(o.number) : distComp;
        }
    }

    public static void main(String[] args) {
        StringBuilder tt = new StringBuilder();
        int     n = 0,
                m = 0;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer ss = new StringTokenizer(in.readLine(), " ");
            n = Integer.parseInt(ss.nextToken());
            m = Integer.parseInt(ss.nextToken());
            for (int i = 0; i < m; i++) {
                tt.append(in.readLine()).append(" ");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        StringTokenizer edges = new StringTokenizer(tt.toString(), " ");
        ArrayList<ArrayList<Edge>> g = new ArrayList<ArrayList<Edge>>(n);
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Edge>());
        }
        for (int i = 0; i < m; i++) {
            int from = Integer.parseInt(edges.nextToken());
            int to = Integer.parseInt(edges.nextToken());
            int weight = Integer.parseInt(edges.nextToken());
            g.get(from).add(new Edge(from, to, weight));
            g.get(to).add(new Edge(to, from, weight));
        }
        ArrayList<Integer> dist = dijkstra(g);
        for (int i : dist) {
            if (i == Integer.MAX_VALUE) {
                System.out.print("X ");
            }
            else {
                System.out.print(i + " ");
            }
        }
    }

    static ArrayList<Integer> dijkstra(ArrayList<ArrayList<Edge>> g) {
        ArrayList<Integer> dist = new ArrayList<>(g.size());
        for (int i = 0; i < g.size(); i++) {
            dist.add(Integer.MAX_VALUE);
        }
        TreeSet<Vertex> used = new TreeSet<Vertex>();
        dist.set(0, 0);
        used.add(new Vertex(0, 0));
        while (!used.isEmpty()) {
            int v = used.pollFirst().number;
            for (Edge neighborEdge : g.get(v)) {
                if (dist.get(neighborEdge.to) > dist.get(v) + neighborEdge.weight) {
                    dist.set(neighborEdge.to, dist.get(v) + neighborEdge.weight);
                    used.add(new Vertex(neighborEdge.to, dist.get(neighborEdge.to)));
                }
            }
        }
        return dist;
    }

}
