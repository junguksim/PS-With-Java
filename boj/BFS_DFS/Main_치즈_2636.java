package boj.BFS_DFS;

import java.io.*;
import java.util.*;

public class Main_치즈_2636 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int W,H;
    private static int[][] map;
    private static ArrayList<Node> cheeses;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void input() throws IOException {
        StringTokenizer wh = new StringTokenizer(bufferedReader.readLine());
        W = Integer.parseInt(wh.nextToken());
        H = Integer.parseInt(wh.nextToken());
        map = new int[W][H];
        cheeses = new ArrayList<>();
        for(int i = 0 ; i < W; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < H; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    cheeses.add(new Node(i,j));
                }
            }
        }
    }

    private static boolean bfs(Node start, boolean[][] visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int sx = node.x;
            int sy = node.y;
            for(int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= W || ny >= H) {
                    // 밖으로 나갈 수 있다
                    return true;
                }
                if(visited[nx][ny] || map[nx][ny] == 1) {
                    // 방문한 곳이거나, 다음 방향이 치즈면 갈 필요가 없음
                    continue;
                }
                queue.add(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return false;
    }

    private static void printMap(int t) {
        System.out.println("================" + t + "초==================");
        for(int i = 0 ; i < W; i++) {
            for(int j = 0 ; j < H; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void solve() throws IOException {
        int time = 0;
        int cheeseAtLastOneHour = 0;
        while (!cheeses.isEmpty()) {
            ArrayList<Node> newCheeses = new ArrayList<>();
            ArrayList<Node> meltedCheeses = new ArrayList<>();
            for(Node cheese : cheeses) {
                if(!bfs(cheese, new boolean[W][H])) {
                    // 나갈 수 없는 치즈라면 녹지 않고
                    newCheeses.add(cheese);
                } else {
                    // 나갈 수 있는 치즈라면 녹아야 함
                    meltedCheeses.add(cheese);
                }
            }
            for(Node meltedCheese : meltedCheeses) {
                // map 에도 녹음을 표시한다.
                map[meltedCheese.x][meltedCheese.y] = 0;
            }
            cheeseAtLastOneHour = cheeses.size();
            cheeses = newCheeses;
            time++;
//            printMap(time);
//            for(Node meltedCheese : meltedCheeses) {
//                // map 에도 녹음을 표시한다.
//                map[meltedCheese.x][meltedCheese.y] = 0;
//            }
        }
        System.out.println(time);
        System.out.println(cheeseAtLastOneHour);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
