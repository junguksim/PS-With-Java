package boj.BFS_DFS;
import java.io.*;
import java.util.StringTokenizer;

public class Main_내리막길_1520 {
    /**
     * 1. 오르막은 올라갈 수 없다!
     * 2. (0,0) 부터 (M-1,N-1) 까지 가야한다.
     * 3. 가는데, 내리막길로만 이루어진 경로여야 한다.
     */
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); // 입력받기 위한 br
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력하기 위한  bw
    private static int M,N; // 행,열
    private static int[][] map, memo; // 지형을 나타내는 배열
    private static int[] dx = {-1,1,0,0}; // 행의 변화
    private static int[] dy = {0,0,-1,1}; // 열의 변화

    private static class Node { // (행, 열) 을 나타내는 Node 클래스
        int x; // 행
        int y; // 열

        public Node(int x, int y) { // Node 생성자
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // input 처리 메서드
        solve(); // 실제 로직 담당 메서드
        bufferedWriter.close(); // bw close
        bufferedReader.close(); // br close
    }

    private static void input() throws IOException {
        StringTokenizer mn = new StringTokenizer(bufferedReader.readLine()); // M, N 입력받기
        M = Integer.parseInt(mn.nextToken()); // M 입력
        N = Integer.parseInt(mn.nextToken()); // N 입력
        map = new int[M][N]; // map 초기화
        memo = new int[M][N];
        for(int r = 0; r < M; r++) { // map 에 높이값을 하나씩 넣어준다.
            StringTokenizer row = new StringTokenizer(bufferedReader.readLine()); // 행을 받아주고
            for (int c = 0; c < N; c++) { // 열 하나하나 돌면서
                map[r][c] = Integer.parseInt(row.nextToken()); // 행의 값 하나씩을 (행,열) 에 넣어준다.
                memo[r][c] = -1;
            }
        }
    }

    private static void solve() throws IOException {
        bufferedWriter.write(dfs(0,0)+"\n"); // answer 를 출력한다.
    }

    private static int dfs(int x, int y) {
        if(x == M-1 && y == N-1) { // 도착지에 도착했다면
            return 1;
        }
        if(memo[x][y] == -1) {
            memo[x][y] = 0;
            for(int i = 0 ; i < 4; i++) { // 사방 탐색을 위한 for문
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if(map[x][y] > map[nx][ny]) {
                    memo[x][y] += dfs(nx, ny);
                }
            }
        }
        return memo[x][y];
    }

    private static boolean[][] cloneVisited(boolean[][] visited) { // 2차원 배열 deep copy method
        boolean[][] result = new boolean[M][N]; // 결과를 나타내는 배열
        for(int i = 0 ; i < M; i++) { // 각 행을 돌면서
            result[i] = visited[i].clone(); // 복사해준다
        }
        return result; // 리턴
    }
}
