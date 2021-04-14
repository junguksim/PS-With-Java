package boj.BFS_DFS;
import java.io.*;
import java.util.StringTokenizer;

public class Main_미세먼지안녕_17144 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int R,C,T;
    private static int[][] map;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static int cleaner;

    private static class Dust {
        int x;
        int y;

        public Dust(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void input() throws IOException {
        StringTokenizer rct = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(rct.nextToken());
        C = Integer.parseInt(rct.nextToken());
        T = Integer.parseInt(rct.nextToken());
        cleaner = -1;
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < C; j++) {
                map[i][j] = Integer.parseInt(line.nextToken());
                if(cleaner == -1 && map[i][j] == -1) { // cleaner 에 한번만 담아줌 -> 윗 부분의 행 값만 담긴다.
                   cleaner = i;
                }
            }
        }
    }

    private static void solve() throws IOException {
        int t = 0;
        while (t < T) {
            spreadDusts();
            cleanse();
            t++;
        }
        System.out.println(getResult());
    }

    private static int getResult() {
        int sum = 0;
        for(int i = 0 ; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] > 0) {
                    sum += map[i][j];
                }
            }
        }
        return sum;
    }

    private static boolean canGo(int x, int y) {
        return !(x < 0 || y < 0 || x >= R || y >= C);
    }
    private static void spreadDusts() {
        int[][] willChangeValue = new int[R][C]; // 각 위치의 미세먼지의 변화량을 담는다
        for(int sx = 0 ; sx < R; sx++) {
            for(int sy = 0 ; sy < C; sy++) {
                if(map[sx][sy] > 0) { // 미세 먼지가 있다면
                    int spreadCount = 0;
                    for(int i = 0 ; i < 4; i++) {
                        int nx = sx + dx[i];
                        int ny = sy + dy[i];
                        if(canGo(nx, ny) && map[nx][ny] != -1) { // 도달 가능하며, 공기청정기 위치가 아닌 경우
                            willChangeValue[nx][ny] += map[sx][sy] / 5;
                            spreadCount++;
                        }
                    }
                    willChangeValue[sx][sy] -= (map[sx][sy] / 5) * spreadCount;
                }
            }
        }
        changeDustsAmount(willChangeValue);
    }

    private static void changeDustsAmount(int[][] willChangeValue) {
        for(int i = 0 ; i < R; i++) {
            for(int j = 0; j < C; j++) {
                map[i][j] += willChangeValue[i][j];  // 변화량을 실제로 반영한다.
            }
        }
    }

    private static void cleanse() {
        // top 은 반시계방향
        int top = cleaner;
        int down = cleaner + 1;
        // 맨 왼쪽 열 아래로 당기기
        for (int i = top - 1; i > 0; i--) {
            map[i][0] = map[i-1][0];
        }
        // 맨 위쪽 행 왼쪽으로 당기기
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i+1];
        }
        // 맨 오른쪽 열 위로 당기기
        for (int i = 0; i < top; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        // top 행 오른쪽으로 당기기
        for (int i = C - 1; i > 1; i--) {
            map[top][i] = map[top][i - 1];
        }
        // cleanser 에서 나오는 바람은 미세먼지 X
        map[top][1] = 0;

        // down은 시계방향
        // 맨 왼쪽 열 위로 당기기
        for (int i = down + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        // 맨 아래 행 왼쪽으로 당기기
        for (int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }
        // 맨 오른쪽 열 아래로 당기기
        for (int i = R - 1; i > down; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        // down 행 오른쪽으로 당기기
        for (int i = C - 1; i > 1; i--) {
            map[down][i] = map[down][i - 1];
        }
        // cleanser 에서 나오는 바람은 미세먼지 X
        map[down][1] = 0;
    }

    private static void printMap() {
        for(int i = 0 ; i < R; i++) {
            for(int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=============");
    }


}
