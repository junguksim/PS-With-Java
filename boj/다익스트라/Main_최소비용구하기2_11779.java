package boj.다익스트라;

import java.io.*;
import java.util.*;

public class Main_최소비용구하기2_11779 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,M;
    private static List<List<Node>> graph;
    private static int start, end;
    private static final int MAX = 987654321;
    private static int[] route, costs;
    private static ArrayList<Integer> routes;

    private static class Node implements Comparable<Node>{
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        M = Integer.parseInt(bufferedReader.readLine());
        graph = new ArrayList<>();
        route = new int[N+1];
        routes = new ArrayList<>();
        costs = new int[N+1];
        for(int i = 0 ; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 1 ; i <= M ; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, cost));
        }
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    private static void dijkstra() {
        route[start] = 0;
        Arrays.fill(costs, MAX);
        boolean[] visited = new boolean[N+1];
        costs[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            int idx = pq.poll().idx;
            if(visited[idx]) continue;
            visited[idx] =  true;

            for(Node node : graph.get(idx)) {
                if(costs[node.idx] > costs[idx] + node.cost) {
                    costs[node.idx] = costs[idx] + node.cost;
                    pq.add(new Node(node.idx, costs[node.idx]));
                    route[node.idx] = idx;
                }
            }
        }
        int node = end;
        while (node != 0) {
            routes.add(node);
            node = route[node];
        }
    }

    private static void solve() throws IOException {
        dijkstra();
        System.out.println(costs[end]);
        System.out.println(routes.size());
        for(int i = routes.size() - 1 ; i >= 0; i--) {
            System.out.print(routes.get(i) + " ");
        }

    }
}