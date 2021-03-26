package boj.BFS_DFS;
import java.io.*;
import java.util.*;

public class Main_연구소_14502 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N,M, ans = Integer.MIN_VALUE;
    static int[][] map;
    static ArrayList<Node> viruses;
    static ArrayList<Node> safes;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        map = new int[N][M];
        viruses = new ArrayList<>();
        safes = new ArrayList<>();
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0;j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    viruses.add(new Node(i, j));
                } else if(map[i][j] == 0) {
                    safes.add(new Node(i, j));
                }
            }
        }
    }

    private static void makeCombination(int cnt, int start ,int[] arr) {
        if(cnt == 3) {
            int[][] newMap = new int[N][M];
            for(int i = 0 ; i < N; i++) {
                newMap[i] = map[i].clone();
            }
            for(int i = 0; i < 3; i++) {
                Node n = safes.get(arr[i]);
                newMap[n.x][n.y] = 1;
            }
            bfs(new boolean[N][M], newMap);
            return;
        }
        for(int i = start; i < safes.size() ; i++) {
            arr[cnt] = i;
            makeCombination(cnt+1, i+1, arr);
        }
    }


    static void bfs(boolean[][] visited, int[][] map) {
        Deque<Node> deque = new ArrayDeque<>(viruses);
        for(int i = 0; i < viruses.size(); i++) {
            Node virus = viruses.get(i);
            visited[virus.x][virus.y] = true;
        }
        while (!deque.isEmpty()) {
            Node node = deque.poll();
            int sx = node.x;
            int sy = node.y;
            for(int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visited[nx][ny] || map[nx][ny] != 0) continue;
                map[nx][ny] = 2;
                visited[nx][ny] = true;
                deque.offer(new Node(nx, ny));
            }
        }
        int safeZone = 0;
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                if(map[i][j] == 0) safeZone++;
            }
        }
        ans = Math.max(safeZone, ans);
    }

    static void solve() {
        makeCombination(0,0,new int[3]);
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
