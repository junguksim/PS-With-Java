package boj.BFS_DFS;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_게리맨더링_17471 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] populations, area;
    private static boolean[] visited;
    private static int N, answer, s1Population, s2Population;
    private static int[][] adjMatrix;


    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        populations = new int[N+1];
        area = new int[N+1];
        StringTokenizer po = new StringTokenizer(bufferedReader.readLine());
        for(int i = 1 ; i <= N; i++) {
            populations[i] = Integer.parseInt(po.nextToken());
        }
        answer = Integer.MAX_VALUE;
        adjMatrix = new int[N+1][N+1];
        for(int i = 1; i <= N; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            int linked = Integer.parseInt(line.nextToken());
            for(int j = 0 ; j < linked; j++) {
                adjMatrix[i][Integer.parseInt(line.nextToken())] = 1;
            }
        }
    }

    private static void dfs(int count) {
        if(count == N+1) {
            int area1= 0, area2= 0;
            for(int i = 1; i <= N; i++) {
                if(area[i] == 1) {
                    area1 += populations[i];
                } else {
                    area2 += populations[i];
                }
            }
            visited = new boolean[N+1];
            int rs = 0;
            for(int i=1; i<=N; i++) {
                if(!visited[i]) {
                    checkArea(i, area[i]);
                    rs++;
                }
            }
            if(rs == 2) {
                if(answer > Math.abs(area1 - area2))
                    answer =  Math.abs(area1 - area2);
            }
            return;
        }

        area[count] = 1;
        dfs(count+1);

        area[count] = 2;
        dfs(count+1);
    }

    private static void checkArea(int index, int num) {
        visited[index] = true;
        for(int i=1; i<=N; i++) {
            if(adjMatrix[index][i] == 1 && !visited[i] && area[i]==num)
                checkArea(i, num);
        }
    }

    private static void solve() throws IOException {
        dfs(1);
        if(answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
