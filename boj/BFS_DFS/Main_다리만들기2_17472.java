package boj.BFS_DFS;
import java.io.*;
import java.util.*;

public class Main_다리만들기2_17472 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,M, islandsCount, islandsNo = 2;
    private static int[][] map;
    private static ArrayList<Island> islands;
    private static int[][] adjIslands;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private static class Island {
        int no;
        ArrayList<Node> grounds;

        public Island(int no, ArrayList<Node> grounds) {
            this.no = no;
            this.grounds = grounds;
        }

        @Override
        public String toString() {
            return "Island{" +
                    "no=" + no +
                    ", grounds=" + grounds +
                    '}';
        }
    }

    private static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        map = new int[N][M];
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < M; j++) {
                map[i][j] = Integer.parseInt(line.nextToken());
            }
        }
        islands = new ArrayList<>();
    }

    private static void printMap() {
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printAdjIslands() {
        for(int i = 0 ; i < islandsCount; i++) {
            for(int j = 0 ; j < islandsCount; j++) {
                System.out.print(adjIslands[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void setIslands() {
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                if(map[i][j] == 1) {
                    setIsland(i, j);
                }
            }
        }
        islandsCount = islands.size();

        adjIslands = new int[islandsCount][islandsCount];
        for(int i = 0 ; i < islandsCount; i++) {
            for(int j = 0 ; j < islandsCount; j++) {
                if(i != j) adjIslands[i][j] = Integer.MAX_VALUE;
            }
        }
        //System.out.println(islands);
        findConnectableIslands();
        System.out.println(getMst());
    }

    private static void setIsland(int x, int y) {
        ArrayList<Node> grounds = new ArrayList<>();
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(new Node(x, y));
        grounds.add(new Node(x, y));
        while (!deque.isEmpty()) {
            Node node = deque.poll();
            int sx = node.x;
            int sy = node.y;
            map[sx][sy] = islandsNo;
            for(int i = 0; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(map[nx][ny] != 1) continue;
                deque.offer(new Node(nx, ny));
                grounds.add(new Node(nx, ny));
                map[nx][ny] = islandsNo;
            }
        }
        islands.add(new Island(islandsNo - 2, grounds));
        islandsCount++;
        islandsNo++;
    }

    private static void findConnectableIslands() {
        for(Island from : islands) {
            int fromNo = from.no;
            ArrayList<Node> fromGrounds = from.grounds;
            for(Node fromGround : fromGrounds) {
                int sx = fromGround.x;
                int sy = fromGround.y;
                //System.out.println(fromNo + " 번 섬에서, " + sx + " " + sy + " 에서 시작");
                for(int d = 0; d < 4; d++) {
                    int nx = sx + dx[d];
                    int ny = sy + dy[d];
                    int bridgeLength = 1;
                    while (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                        //System.out.println(nx + " " + ny);
                        if(map[nx][ny] == 0) {
                            nx += dx[d];
                            ny += dy[d];
                            bridgeLength++;
                            continue;
                        }
                        // 섬을 만났다면
                        if(bridgeLength == 2) break; // 거리가 1밖에 안된다면 다리 X
                        int toNo = map[nx][ny] - 2;
                        //System.out.println(fromNo + " " + toNo);
                        adjIslands[fromNo][toNo] = Math.min(adjIslands[fromNo][toNo], bridgeLength - 1);
                        adjIslands[toNo][fromNo] = Math.min(adjIslands[toNo][fromNo], bridgeLength - 1);
                        break;
                    }
                }
            }
        }
    }

    private static int getMst() {
        boolean[] visited = new boolean[islandsCount];
        int[] minEdge = new int[islandsCount];
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[0] = 0;
        int minVertex = 0, min = 0, result = 0;
        for(int c = 0; c < islandsCount; c++) {
            min = Integer.MAX_VALUE;
            minVertex = 0;

            for(int i = 0 ; i < islandsCount; i++) {
                if(!visited[i] && min > minEdge[i]) {
                    min = minEdge[i];
                    minVertex = i;
                }
            }
            result += min;
            visited[minVertex] = true;

            for(int i = 0 ; i < islandsCount; i++) {
                if(!visited[i] && adjIslands[minVertex][i] != 0 && adjIslands[minVertex][i] != Integer.MAX_VALUE && minEdge[i] > adjIslands[minVertex][i]) {
                    minEdge[i] = adjIslands[minVertex][i];
                }
            }
        }
        for(int i = 0 ; i < islandsCount; i++) {
            if(minEdge[i] == Integer.MAX_VALUE) return -1;
        }
        return result;
    }

    private static void solve() throws IOException {
        setIslands();
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
