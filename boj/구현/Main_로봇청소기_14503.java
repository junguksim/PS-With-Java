package boj.구현;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_로봇청소기_14503 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,M, dir, r, c, count = 0;
    static int[][] map;

    static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        StringTokenizer rcd = new StringTokenizer(bufferedReader.readLine());
        r = Integer.parseInt(rcd.nextToken()) ;
        c = Integer.parseInt(rcd.nextToken()) ;
        dir = Integer.parseInt(rcd.nextToken());
        map = new int[N][M];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line.nextToken());
            }
        }
    }

    static int[] move(int r, int c, int dir) {
        switch (dir) {
            case 0:
                r--;
                break;
            case 1:
                c++;
                break;
            case 2:
                r++;
                break;
            case 3:
                c--;
                break;
        }
        return new int[]{r,c};
    }

    static boolean isOut(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    static int rotate(int dir) {
        if(dir == 0) dir = 3;
        else {
            dir--;
        }
        return dir;
    }

    static int checkLeftCleanable() {
        int nr = r;
        int nc = c;
        int nDir = dir;
        nDir = rotate(nDir);
        int[] afterMovePos = move(nr, nc, nDir);
        nr = afterMovePos[0];
        nc = afterMovePos[1];
        dir = nDir;
        if(isOut(nr, nc)) {
            return -1;
        }; // 왼쪽으로 갔을 때 이미 나간 경우
        if(map[nr][nc] == 0) {
            r = nr;
            c = nc;
            return 0;
        } else if(map[nr][nc] == 2) {
            return 2;
        } else {
            return 1;
        }
    }

    static boolean moveBack() {
        switch (dir) {
            case 0:
                r++;
                break;
            case 1:
                c--;
                break;
            case 2:
                r--;
                break;
            case 3:
                c++;
                break;
        }
        if(isOut(r,c) || map[r][c] == 1) {
            return false;
        }
        return true;
    }

    static void solve() {
        while (true) {
            if(map[r][c] == 1) break; // 만약 현재 지점이 벽이라면 break
            if(map[r][c] == 0) { // 청소할 수 있다면 청소하기
                map[r][c] = 2;
                count++;
            }
            boolean isFourImpossible = false;
            int wallCnt = 0;
            for(int i = 0 ;i < 4; i++) {
                int checkLeftResult = checkLeftCleanable();
                if(checkLeftResult == 0) break; // 왼쪽이 청소할 수 있는 곳이라면 이동시켜놓고 break
                if(checkLeftResult == -1 || checkLeftResult == 1) { // 왼쪽이 벽이거나, 바깥이라면
                    wallCnt++;
                }
                if(i == 3) {
                    isFourImpossible = true; // 마지막까지 했는데 break가 안됐다면 flag to true
                }
            }
            if(isFourImpossible) {
                if(wallCnt == 4) break; // 만약 벽 + 바깥의 개수가 4라면 ? 갇혀버린 것이므로 끝이다.
                if(!moveBack()) { // 후진을 할 수가 없다면 break
                    break;
                }
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
