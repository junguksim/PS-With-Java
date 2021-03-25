package swea;
import java.io.*;
import java.util.StringTokenizer;

public class Solution_사람네트워크2_D6 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static final int INF = Integer.MAX_VALUE;
    private static int[][] distance;
    private static void input() throws IOException {
        StringTokenizer input = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(input.nextToken());
        distance = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0 ; j < N; j++) {
                distance[i][j] = Integer.parseInt(input.nextToken());
                if(distance[i][j] == 0 && i != j) {
                    distance[i][j] = INF;
                }
            }
        }
    }

    private static void solve(int t) throws IOException {
        for(int stopOver = 0; stopOver < N; stopOver++) {
            for(int start = 0 ; start < N; start++) {
                if(start == stopOver) continue; // 출발지와 경유지가 같다면 -> 다음 출발지
                for(int destination = 0 ; destination < N; destination++) {
                    if(stopOver == destination || start == destination) continue; // 도착지가 출발지 or 경유지랑 같다면
                    if(distance[start][stopOver] != INF && distance[stopOver][destination] != INF && distance[start][destination] > distance[start][stopOver] + distance[stopOver][destination]) {
                        // 1. 출발지 -> 경유지 가능
                        // 2. 경유지 -> 목적지 가능
                        // 3. 출발지 -> 목적지 거리 > 출발지->경유지 + 경유지->목적지 라면 경유하는 걸로 바꿔준다.
                        distance[start][destination] = distance[start][stopOver] + distance[stopOver][destination];
                    }
                }
            }
        }
        int min = INF;
        for(int i = 0 ; i < N; i++) {
            int cc = 0;
            for(int j = 0 ; j < N; j++) {
                if(i != j) {
                    cc += distance[i][j];
                }
            }
            min = Math.min(min, cc);
        }
        bufferedWriter.write("#"+t+" "+min+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
