package boj.백트래킹;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_스도쿠_2239 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[][] map = new int[9][9];
    private static ArrayList<Node> shouldFill;

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void input() throws IOException {
        shouldFill = new ArrayList<>();
        for(int i = 0 ; i < 9; i++) {
            //String[] line = bufferedReader.readLine().split("");
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < 9; j++) {
                //map[i][j] = Integer.parseInt(line[j]);
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) shouldFill.add(new Node(i, j));
            }
        }
    }

    private static void printMap() {
        for(int i = 0 ; i < 9; i++) {
            for(int j = 0 ; j < 9; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void findAbleNumber(int idx) {
        if(idx == shouldFill.size()) {
            printMap();
            System.exit(0);
        }
        int r = shouldFill.get(idx).x;
        int c = shouldFill.get(idx).y;
        boolean[] row = new boolean[10];
        boolean[] col = new boolean[10];
        boolean[] square = new boolean[10];
        for(int x = 0 ; x < 9; x++) {
            if(map[x][c] != 0) row[map[x][c]] = true;
        }
        for(int y = 0 ; y < 9; y++) {
            if(map[r][y] != 0) col[map[r][y]] = true;
        }
        int sr = (r / 3) * 3;
        int sc = (c / 3) * 3;
        for(int x = sr; x < sr + 3; x++) {
            for(int y = sc; y < sc + 3; y++) {
                if(map[x][y] != 0) square[map[x][y]] = true;
            }
        }
        for(int i = 1; i <= 9; i++) {
            if(!row[i] && !col[i] && !square[i]) {
                map[r][c] = i;
                findAbleNumber(idx+1);
                map[r][c] = 0;
            }
        }
    }

    private static void solve() throws IOException {
       findAbleNumber(0);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
