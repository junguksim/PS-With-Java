package boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_빵집_3109 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int R,C, ans;
    static int[] rows;
    static int[][] walls;
    static boolean[][] visited;
    static int[] dx = {-1,0,1};

    static void input() throws IOException {
        StringTokenizer rc = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(rc.nextToken());
        C = Integer.parseInt(rc.nextToken());
        rows = new int[C];
        walls = new int[R][C];
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++) {
            char[] line = bufferedReader.readLine().toCharArray();
            for(int j = 0 ; j < C; j++) {
                if(line[j] == 'x') {
                    walls[i][j] = -1;
                }
            }
        }
    }

    static boolean setPipe(int r, int c) {
        if(c == C -1) {
            ans++;
            return true; // 마지막 행까지 왔다면 답을 증가
        }
        int nr, nc= c + 1; // nc는 다음 행으로 고정
        for(int d = 0 ; d < 3; d++) {
            nr = r + dx[d]; // 위, 옆, 아래를 봄
            if(nr < 0 || nr >= R) continue; // 바깥으로 간다면 제외
            if(walls[nr][nc] == -1 || visited[nr][nc]) continue; // 다음에 벽이 있거나 이미 파이프를 설치한 곳이라면 제외
            visited[nr][nc] = true; //파이프 설치
            if(setPipe(nr, nc)) return true; // 다음 위치에서 다시 setPipe 실행을 재귀로 호출
        }
        return false;
    }

    static void solve() {
        for(int i = 0 ; i < R; i++) {
            visited[i][0] = true;
            setPipe(i, 0); // 모든 열의 첫 행에서 setPipe 를 실행한다.
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
