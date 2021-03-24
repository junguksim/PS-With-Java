package swea;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_하나로_D4 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static double E;
    static Island[] islands;
    static Double[] costs;
    static boolean[] visited;

    static class Island {
        int x;
        int y;

        public Island(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void input() throws IOException
    {
        N = Integer.parseInt(bufferedReader.readLine());
        islands = new Island[N];
        costs = new Double[N];
        visited = new boolean[N];
        Arrays.fill(costs, Double.MAX_VALUE);
        StringTokenizer xLine = new StringTokenizer(bufferedReader.readLine());
        StringTokenizer yLine = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0; i < N; i++) {
            islands[i] = new Island(Integer.parseInt(xLine.nextToken()), Integer.parseInt(yLine.nextToken()));
        }
        E = Double.parseDouble(bufferedReader.readLine());
    }

    static double calc(Island from, Island to) {
        return (Math.pow(from.x-to.x, 2) + Math.pow(from.y-to.y, 2)) * E;
    }

    static void solve(int t) throws IOException {
        int minVertex = 0;
        double min, result = 0;
        costs[0] = 0.0;
        for(int c = 0; c < N; c++) {
            min = Double.MAX_VALUE;
            minVertex = 0;
            for(int i = 0; i < N; i++) {
                if(!visited[i] && min > costs[i]) {
                    min = costs[i];
                    minVertex = i;
                }
            }
            result += min;
            visited[minVertex] = true;
            for(int i = 0 ; i < N; i++) {
                if(!visited[i]) {
                    Double dis =  calc(islands[minVertex], islands[i]);
                    if(costs[i] > dis) {
                        costs[i] = dis;
                    }
                }
            }
        }
        bufferedWriter.write("#"+t+" "+(long)Math.round(result)+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T ; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}