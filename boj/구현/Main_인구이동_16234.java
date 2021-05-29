package boj.구현;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_인구이동_16234 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int N,L,R,RL;
    private static final int UP=0,RIGHT=2,DOWN=1,LEFT=3;
    private static int[][] map;
    private static int[][] connected;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,1,-1};

    private static class Node {
        int r;
        int c;
        int group;

        public Node(int r, int c, int group) {
            this.r = r;
            this.c = c;
            this.group = group;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
    
    private static void input() throws IOException {
        StringTokenizer nlr = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nlr.nextToken());
        L = Integer.parseInt(nlr.nextToken());
        R = Integer.parseInt(nlr.nextToken());
        RL = R-L;
        map = new int[N][N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < N; j++) {
                map[i][j] = Integer.parseInt(line.nextToken());
            }
        }
    }

    private static boolean openLine() {
        connected = new int[N][N];
        int group = 1;
        boolean atLeastOneOpened = false;
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(connected[r][c] != 0) continue; // 이미 연결된 애들은 넘어간다.
                Queue<Node> queue = new LinkedList<>();
                queue.add(new Node(r, c, group));
                connected[r][c] = group++;
                while (!queue.isEmpty()) {
                    Node before = queue.poll();
                    int sr = before.r;
                    int sc = before.c;
                    int sg = before.group;
                    for(int d = 0; d < 4; d++) {
                        int nr = sr + dx[d];
                        int nc = sc + dy[d];
                        if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                        if(connected[nr][nc] != 0) continue;
                        int next = map[nr][nc];
                        int howManyDiff = Math.abs(map[sr][sc] - next);
                        if(howManyDiff >= L && howManyDiff <= R) {
                            connected[nr][nc] = connected[sr][sc];
                            atLeastOneOpened = true;
                            queue.add(new Node(nr, nc, sg));
                        }
                    }
                }
            }
        }
        return atLeastOneOpened;
    }
    private static void checkLine() {
        for(int r = 0 ; r < N; r++) {
            for(int c = 0 ; c < N; c++) {
                System.out.print(connected[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("===========");
    }

    private static void printMap() {
        for(int r = 0 ; r < N; r++) {
            for(int c = 0 ; c < N; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("===========");
    }

    private static void move() {
        boolean[][] visited = new boolean[N][N];
        for(int r = 0 ; r < N; r++) {
            for(int c = 0 ; c < N; c++) {
                if(visited[r][c]) continue; // 이미 처리한 곳이라면 넘어가기
                ArrayList<Node> inGroup = new ArrayList<>(); // 해당 그룹에 있는 노드들을 담아두기
                int populationSum = map[r][c]; // 해당 그룹의 총 인구 수
                Queue<Node> queue = new LinkedList<>();
                queue.add(new Node(r, c, connected[r][c]));
                inGroup.add(new Node(r, c, connected[r][c]));
                while (!queue.isEmpty()) {
                    Node before = queue.poll();
                    int sr = before.r;
                    int sc = before.c;
                    visited[sr][sc] = true;
                    int sg = before.group;
                    for(int d = 0; d < 4; d++) {
                        int nr = sr + dx[d];
                        int nc = sc + dy[d];
                        if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                        if(visited[nr][nc]) continue;
                        if(connected[nr][nc] == sg) {
                            visited[nr][nc] = true;
                            populationSum += map[nr][nc];
                            queue.add(new Node(nr,nc,sg));
                            inGroup.add(new Node(nr, nc, sg));
                        }
                    }
                }
                for(Node x : inGroup) {
                    map[x.r][x.c] = populationSum / inGroup.size();
                }
            }
        }
    }

    private static void solve() {
        int count = 0;
        while (true) {
            if(!openLine()) {
                break;
            }
            //printMap();
            //checkLine();
            move();
            //printMap();
            count++;
        }
        System.out.println(count);
    }
}