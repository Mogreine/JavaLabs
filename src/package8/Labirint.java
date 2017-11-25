package package8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Labirint {

    public static class Coords implements Comparable<Coords> {
        Integer x,
                y;

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Coords o) {
            int distComp = x.compareTo(o.x);
            return distComp == 0 ? y.compareTo(o.y) : distComp;
        }
    }

    public static void main(String[] args) {
        StringBuilder tt = new StringBuilder();
        int     n = 0,
                m = 0;
        ArrayList<ArrayList<Character>> g = new ArrayList<ArrayList<Character>>(n);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer ss = new StringTokenizer(in.readLine(), " ");
            n = Integer.parseInt(ss.nextToken());
            m = Integer.parseInt(ss.nextToken());
            for (int i = 0; i < n; i++) {
                g.add(new ArrayList<Character>());
                for (int j = 0; j < m; j++) {
                    g.get(i).add((char) in.read());
                }
                in.read();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        TreeMap<Coords, Coords> parent = dijkstra(g);
        g.get(0).set(0, '+');
        g.get(g.size() - 1).set(g.get(0).size() - 1, '+');
        Coords cur = parent.get(new Coords(g.get(0).size() - 1, g.size() - 1));
        do if (parent.size() > 1) {
            g.get(cur.y).set(cur.x, '+');
            cur = parent.get(cur);
        }
        while (cur != null && !(cur.x == 0 && cur.y == 0));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(g.get(i).get(j));
            }
            System.out.println();
        }
    }

    static TreeMap<Coords, Coords> dijkstra(ArrayList<ArrayList<Character>> g) {
        ArrayList<ArrayList<Integer>> dist = new ArrayList<ArrayList<Integer>>(g.size());
        for (int i = 0; i < g.size(); i++) {
            dist.add(new ArrayList<Integer>());
            for (int j = 0; j < g.get(0).size(); j++) {
                dist.get(i).add(Integer.MAX_VALUE);
            }
        }
        TreeMap<Coords, Coords> parent2 = new TreeMap<Coords, Coords>();
        TreeSet<Coords> used = new TreeSet<Coords>();
        dist.get(0).set(0, 0);
        used.add(new Coords(0, 0));
        while (!used.isEmpty()) {
            Coords v = used.pollFirst();
            int distToV = dist.get(v.y).get(v.x);
            if (v.x > 0 && g.get(v.y).get(v.x - 1) != '#') {
                if (dist.get(v.y).get(v.x - 1) > distToV + 1) {
                    dist.get(v.y).set(v.x - 1, distToV + 1);
                    parent2.put(new Coords(v.x - 1, v.y), v);
                    used.add(new Coords(v.x - 1, v.y));
                }
            }
            if (v.x < g.get(0).size() - 1 && g.get(v.y).get(v.x + 1) != '#') {
                if (dist.get(v.y).get(v.x + 1) > distToV + 1) {
                    dist.get(v.y).set(v.x + 1, distToV + 1);
                    parent2.put(new Coords(v.x + 1, v.y), v);
                    used.add(new Coords(v.x + 1, v.y));
                }
            }
            if (v.y > 0 && g.get(v.y - 1).get(v.x) != '#') {
                if (dist.get(v.y - 1).get(v.x) > distToV + 1) {
                    dist.get(v.y - 1).set(v.x, distToV + 1);
                    parent2.put(new Coords(v.x, v.y - 1), v);
                    used.add(new Coords(v.x, v.y - 1));
                }
            }
            if (v.y < g.size() - 1 && g.get(v.y + 1).get(v.x) != '#') {
                if (dist.get(v.y + 1).get(v.x) > distToV + 1) {
                    dist.get(v.y + 1).set(v.x, distToV + 1);
                    parent2.put(new Coords(v.x, v.y + 1), v);
                    used.add(new Coords(v.x, v.y + 1));
                }
            }
        }
        return parent2;
    }

}
