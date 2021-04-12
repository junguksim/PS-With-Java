package boj.다익스트라;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_최소비용구하기_1916 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, start, end;
    private static ArrayList<Bus>[] buses;
    private static final int MAX = 987654321;

    private static class Bus {
        int to;
        int cost;

        public Bus(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Bus{" +
                    "to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }

    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        M = Integer.parseInt(bufferedReader.readLine());
        buses = new ArrayList[M+1];
        for(int i = 1 ; i <= M; i++) {
            buses[i] = new ArrayList<>();
        }
        for(int i = 1 ; i <= M; i++) {
            StringTokenizer busInfo = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(busInfo.nextToken());
            int to = Integer.parseInt(busInfo.nextToken());
            int cost = Integer.parseInt(busInfo.nextToken());
            buses[from].add(new Bus(to, cost));
        }
        StringTokenizer se = new StringTokenizer(bufferedReader.readLine());
        start = Integer.parseInt(se.nextToken());
        end = Integer.parseInt(se.nextToken());
        //System.out.println(Arrays.toString(buses));
    }

    private static void solve() throws IOException {
        boolean[] visited = new boolean[N+1];
        int[] distance = new int[N+1];
        Arrays.fill(distance, MAX);
        distance[start] = 0;

        int min = 0, current = start;
        for(int i = 1; i <= N; i++) {
            min = MAX;
            for(int j = 1 ; j <= N; j++) {
                if(!visited[j] && distance[j] < min) {
                    min = distance[j];
                    current = j;
                }
            }
            visited[current] = true;
            if(current == end) break;
            ArrayList<Bus> canGoBus = buses[current];
            for (Bus bus : canGoBus) {
                int to = bus.to;
                int cost = bus.cost;
                if (!visited[to] && distance[to] > min + cost) {
                    distance[to] = min + cost;
                }
            }
        }
        //System.out.println(Arrays.toString(distance));
        System.out.println(distance[end]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
