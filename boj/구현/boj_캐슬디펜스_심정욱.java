package boj.구현;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class boj_캐슬디펜스_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,M,D, answer, enemyCnt;
    static ArrayList<Node> enemies, enemiesTemp;
    static int[][] map, mapTemp, es;
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

    static void input() throws IOException {
        StringTokenizer nmd = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nmd.nextToken());
        M = Integer.parseInt(nmd.nextToken());
        D = Integer.parseInt(nmd.nextToken());
        enemies = new ArrayList<>();
        enemiesTemp = new ArrayList<>();
        map = new int[N+1][M];
        mapTemp = new int[N+1][M];
        answer = Integer.MIN_VALUE;
        sniperPositions = new int[3];
        for(int i = 0; i< N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    enemies.add(new Node(i, j, 0));
                }
            }
        }
        enemyCnt = enemies.size();
    }

    static void makeCombination(int cnt, int start) {
        if(cnt == 3) {
            System.out.println(Arrays.toString(sniperPositions));
            int sum = getKilledEnemiesCount();
            System.out.println("최종 죽인 몬스터 수 : " + sum);
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

    static void mapPrint() {
        System.out.println("========map========");
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("================");
        System.out.println("========mapTemp========");
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                System.out.print(mapTemp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("================");
    }
    static int getKilledEnemiesCount() {
        int sum = 0;
        int enemyCount = enemyCnt;
        mapCopy();
        while(enemyCount > 0) {
            //mapPrint();
            int deletedByShoot = shoot();
            sum += deletedByShoot;
            enemyCount -= deletedByShoot;
            //System.out.println("쏴서 죽은 몹 : " + deletedByShoot);
            int deletedByMove = enemyMove();
            enemyCount -= deletedByMove;
            //System.out.println("움직여서 죽은 몹 : " + deletedByMove);
        }
        return sum;
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

    static int shoot() {
        int deleteByShootCount = 0;
        for (int sniperPosition : sniperPositions) {
            //System.out.printf("%d,%d에 있는 궁수 사격 시작\n", N, sniperPosition);
            deleteByShootCount += bfs(N - 1, sniperPosition); // 항상 성안에 있으므로 x는 N-1 고정
        }
        return deleteByShootCount;
    }
    static int bfs(int sx, int sy) {
        visited = new boolean[N+1][M];
        int killX = N;
        int killY = M;
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
                if(killY > y) {
                    killX = x;
                    killY = y;
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
        if(killX < N && killY < M) {
            mapTemp[killX][killY] = 0;
            return 1;
        }
        return 0;
    }

    static void solve() {
        makeCombination(0,0);
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
