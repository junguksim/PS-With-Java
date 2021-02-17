package boj.구현;

import java.io.*;
import java.util.*;

public class boj_캐슬디펜스_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N,M,D, answer, enemyCnt;
    static ArrayList<Node> deletePoints;
    static int[][] map, mapTemp;
    static int[] sniperPositions;
    static boolean[][] visited;
    static int[] dx = {-1,0,0};
    static int[] dy = {0,-1,1};

    static class Node {
        int x;
        int y;
        int depth;

        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }

    static void input() throws IOException {
        StringTokenizer nmd = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nmd.nextToken());
        M = Integer.parseInt(nmd.nextToken());
        D = Integer.parseInt(nmd.nextToken());
        map = new int[N+1][M];
        mapTemp = new int[N+1][M];
        answer = Integer.MIN_VALUE;
        sniperPositions = new int[3];
        deletePoints = new ArrayList<>();
        for(int i = 0; i< N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    enemyCnt++;
                }
            }
        }
    }

    static void solve() {
        makeCombination(0,0);
        System.out.println(answer);
    }

    static void makeCombination(int cnt, int start) {
        if(cnt == 3) {
            int sum = getKilledEnemiesCount();
            answer = Math.max(answer,sum );
            return;
        }
        for(int i = start; i < M; i++) {
            sniperPositions[cnt] = i;
            makeCombination(cnt+1, i+1);
        }
    }

    static void mapCopy() {
        for(int i = 0 ; i < N; i++) {
            mapTemp[i] = map[i].clone();
        }
    }

    static int getKilledEnemiesCount() {
        int sum = 0;
        int enemyCount = enemyCnt;
        mapCopy(); // mapTemp 에 map 의 형태를 담아서 궁수의 위치 조합마다 실행될 수 있게 함
        while(enemyCount > 0) {
            int deletedByShoot = shoot(); // 사격 후 죽은 숫자
            sum += deletedByShoot;
            enemyCount -= deletedByShoot;
            int deletedByMove = enemyMove();
            enemyCount -= deletedByMove;
        }
        return sum;
    }

    static int shoot() {
        int deleteByShootCount = 0;
        deletePoints = new ArrayList<>();
        for (int sniperPosition : sniperPositions) {
            bfs(N - 1, sniperPosition); // 항상 성안에 있으므로 x는 N-1 고정
        }
        for(Node deletePoint : deletePoints) {
            if(mapTemp[deletePoint.x][deletePoint.y] == 1) {
                mapTemp[deletePoint.x][deletePoint.y] = 0;
                deleteByShootCount++;
            }
        }
        return deleteByShootCount;
    }
    static void bfs(int sx, int sy) {
        visited = new boolean[N+1][M];
        int killX = N;
        int killY = M;
        int killD = N + M;
        Queue<Node> queue = new LinkedList<>();
        visited[sx][sy] = true;
        queue.add(new Node(sx, sy, 1));
        while (!queue.isEmpty()) {
            Node sniper = queue.poll();
            int x = sniper.x;
            int y = sniper.y;
            int d = sniper.depth;
            if(d > D) {
                break;
            }
            if(mapTemp[x][y] == 1) {
                if(killY > y && killD >= d) {
                    killX = x;
                    killY = y;
                    killD = d;
                }
            }
            for(int i = 0 ; i <3 ; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N|| ny >= M) {
                    continue;
                }
                if(visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny, d + 1));
            }
        }
        if(killX < N && killY < M && killD <= D) {
            deletePoints.add(new Node(killX, killY, killD));
        }
    }

    static void swapLine(int i, int j) {
        int[] temp = mapTemp[i];
        mapTemp[i] = mapTemp[j];
        mapTemp[j] = temp;
    }

    static int enemyMove() {
        int sum = 0;
        for(int i = 0; i < M; i++) {
            if(mapTemp[N-1][i] == 1) {
                mapTemp[N-1][i] = 0;
                sum++;
            }
        }
        for(int i = N-2; i>=0; i--) {
            swapLine(i, i+1);
        }
        return sum;
    }
}
