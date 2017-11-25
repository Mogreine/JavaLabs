package package8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DistsFloyd {

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
        int[][] g = new int[n][n];
        for (int i = 0; i < m; i++) {
            g[Integer.parseInt(edges.nextToken())][Integer.parseInt(edges.nextToken())] = Integer.parseInt(edges.nextToken());
        }
        int[][] dist = floyd(g);
        for (int i : dist[0]) {
            if (i == Integer.MAX_VALUE) {
                System.out.print("X ");
            } else {
                System.out.print(i + " ");
            }
        }
    }

    static int[][] floyd(int[][] g) {
        int[][] dist = new int[g.length][g.length];
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g.length; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                }
                else {
                    dist[i][j] = g[i][j] == 0 ? Integer.MAX_VALUE : g[i][j];
                }
            }
        }
        for (int k = 0; k < g.length; k++) {
            for (int i = 0; i < g.length; i++) {
                for (int j = 0; j < g.length; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }

}
