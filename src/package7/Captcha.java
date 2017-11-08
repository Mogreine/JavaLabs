package package7;

import java.util.Scanner;

public class Captcha {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int     height = in.nextInt(),
                width = in.nextInt();
        int[][] g = new int[height][width];
        in.nextLine();
        for (int i = 0; i < height; i++) {
            String tmp = in.nextLine();
            for (int j = 0; j < width; j++) {
                g[i][j] = tmp.charAt(j) == '.' ? 1 : 0;
            }
        }
        int components = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (g[i][j] == 1) {
                    dfs(g, i, j);
                    components++;
                }
            }
        }
        int num = components == 3 ? 8 : components == 2 ? 9 : 7;
        System.out.print(num);
    }

    static void dfs(int[][] g, int i, int j) {
        g[i][j]++;
        callDFS(g, i, j);
    }

    static void callDFS(int[][] g, int i, int j) {
        if (j > 0 && g[i][j - 1] == 1) {
            dfs(g, i, j - 1);
        }
        if (j < g[i].length - 1 && g[i][j + 1] == 1) {
            dfs(g, i, j + 1);
        }
        if (i > 0 && g[i - 1][j] == 1) {
            dfs(g, i - 1, j);
        }
        if (i < g.length - 1 && g[i + 1][j] == 1) {
            dfs(g, i + 1, j);
        }
    }

}
