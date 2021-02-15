package boj.BFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class boj_적록색약_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static char[][] graph;
    static boolean[][] visited;
    static int N, normalCnt, blindCnt;
    static int[] dy = {0,0,1,-1};
    static int[] dx = {-1,1,0,0};
    static Queue<Red> RNodes = new LinkedList<>(), RNodesSave = new LinkedList<>();
    static Queue<Green> GNodes = new LinkedList<>(), GNodesSave = new LinkedList<>();
    static Queue<Blue> BNodes = new LinkedList<>(), BNodesSave = new LinkedList<>();

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Red extends Node {
        public Red(int x, int y) {
            super(x, y);
        }
    }

    static class Green extends Node {
        public Green(int x, int y) {
            super(x, y);
        }
    }

    static class Blue extends Node {
        public Blue(int x, int y) {
            super(x, y);
        }
    }

    static void bfs(Node v, boolean isColorBlind) {
        if(isColorBlind) {
            blindCnt++;
        } else {
            normalCnt++;
        }
        visited[v.x][v.y] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(v);
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            for(int i = 0 ; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if(visited[nx][ny]) {
                    continue;
                }
                if(isColorBlind) {
                    if(((v instanceof Red || v instanceof Green) && graph[nx][ny] == 'B') || (v instanceof Blue && graph[nx][ny] != 'B')) {
                        continue;
                    }
                }
                else {
                    if((v instanceof Red && graph[nx][ny] != 'R') || (v instanceof Blue && graph[nx][ny] != 'B') || (v instanceof Green && graph[nx][ny] != 'G')) {
                        continue;
                    }
                }
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny));
            }
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        graph = new char[N][N];
        visited = new boolean[N][N];
        for(int i = 0 ; i < N; i++) {
            char[] line = bufferedReader.readLine().toCharArray();
            for(int j = 0; j < N; j++) {
                graph[i][j] = line[j];
                if(line[j] == 'G') {
                    GNodes.add(new Green(i,j));
                    GNodesSave.add(new Green(i,j));
                } else if(line[j] == 'R') {
                    RNodes.add(new Red(i,j));
                    RNodesSave.add(new Red(i,j));
                } else {
                    BNodes.add(new Blue(i,j));
                    BNodesSave.add(new Blue(i,j));
                }
            }
        }
    }

    static void solve() {
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < N; j++) {
                if(graph[i][j] == 'R' && !RNodes.isEmpty()) {
                    Red r = RNodes.poll();
                    if(!visited[i][j]) {
                        bfs(r, false);
                    }
                } else if(graph[i][j] == 'G'  && !GNodes.isEmpty()) {
                    Green g = GNodes.poll();
                    if(!visited[i][j]) {
                        bfs(g, false);
                    }
                } else if(graph[i][j] == 'B' && !BNodes.isEmpty()){
                    Blue b = BNodes.poll();
                    if(!visited[i][j]) {
                        bfs(b, false);
                    }
                }
            }
        }
        visited = new boolean[N][N];
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < N; j++) {
                if(graph[i][j] == 'R' && !RNodesSave.isEmpty()) {
                    Red r = RNodesSave.poll();
                    if(!visited[i][j]) {
                        bfs(r, true);
                    }
                } else if(graph[i][j] == 'G'  && !GNodesSave.isEmpty()) {
                    Green g = GNodesSave.poll();
                    if(!visited[i][j]) {
                        bfs(g, true);
                    }
                } else if(graph[i][j] == 'B' && !BNodesSave.isEmpty()){
                    Blue b = BNodesSave.poll();
                    if(!visited[i][j]) {
                        bfs(b, true);
                    }
                }
            }
        }
        System.out.println(normalCnt+" "+blindCnt);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
