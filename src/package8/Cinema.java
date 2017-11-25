package package8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Cinema {

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
        int     n = 0,
                m = 0;
        ArrayList<ArrayList<Character>> g = new ArrayList<ArrayList<Character>>(n);
        ArrayList<Coords> occupiedPlaces = new ArrayList<Coords>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer ss = new StringTokenizer(in.readLine(), " ");
            n = Integer.parseInt(ss.nextToken());
            m = Integer.parseInt(ss.nextToken());
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if ((char) in.read() == 'X') {
                        occupiedPlaces.add(new Coords(j + 1, i + 1));
                    }
                }
                in.read();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        int size = n * m;
        int dist[] = new int[size];
        for (int k = 0; k < size; k++) {
            dist[k] = Collections.min(distToPlaces(occupiedPlaces, k, m));
        }
        int maxDist = 0, count = 0;
        for (int d : dist) {
            if (d > maxDist) {
                maxDist = d;
                count = 1;
            }
            else if (d == maxDist) {
                count++;
            }
        }
        System.out.print(maxDist + " " + count);

    }

    static ArrayList<Integer> distToPlaces(ArrayList<Coords> places, int k, int m) {
        ArrayList<Integer> dist = new ArrayList<Integer>(places.size());
        for (int i = 0; i < places.size(); i++) {
            dist.add(calcDist(m, k, places.get(i)));
        }
        return dist;
    }

    static int calcDist(int m, int k, Coords c) {
        int ky = k / m + 1;
        int kx = k % m + 1;
        return Math.abs(kx - c.x) + Math.abs(ky - c.y);
    }

}
