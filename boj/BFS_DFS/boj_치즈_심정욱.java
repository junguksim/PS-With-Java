package boj.BFS_DFS;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_치즈_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int R,C;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static ArrayList<Node> cheeses;

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static void input() throws IOException {
        StringTokenizer rc = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(rc.nextToken());
        C = Integer.parseInt(rc.nextToken());
        map = new int[R][C];
        cheeses = new ArrayList<>();
        for(int i = 0; i < R; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    cheeses.add(new Node(i, j));
                }
            }
        }
    }

    static boolean bfs(Node start, boolean[][] visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            Node s = queue.poll();
            int x = s.x;
            int y = s.y;
            visited[x][y] = true;
            for(int i = 0 ; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    return true;
                }
                if(visited[nx][ny] || map[nx][ny] >= 1) continue;
                queue.add(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return false;
    }

    static void printMap() {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void solve() {
        int count = 0;
        int time = 0;
        while (!cheeses.isEmpty()) {
            ArrayList<Node> newCheeses = new ArrayList<>();
            ArrayList<Node> deleteCheeses = new ArrayList<>();
            for(Node cheese : cheeses) {
                if(!bfs(cheese, new boolean[R][C])) {
                    newCheeses.add(cheese);
                } else {
                    deleteCheeses.add(cheese);
                }
            }
            for(Node cheese : deleteCheeses) {
                map[cheese.x][cheese.y] = 0;
            }
            count = cheeses.size();
            cheeses = newCheeses;
            time++;
//            printMap();
//            System.out.println("=================");
        }
        System.out.println(time);
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
