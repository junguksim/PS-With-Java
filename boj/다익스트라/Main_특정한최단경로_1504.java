package boj.다익스트라;

import java.io.*;
import java.util.*;

public class Main_특정한최단경로_1504 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,E;
    private static List<List<Node>> graph;
    private static final int MAX = 987654321;
    private static int[] mustGo;

    private static class Node implements Comparable<Node> {
        int idx;
        int distance;

        public Node(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
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
        StringTokenizer ne = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(ne.nextToken());
        E = Integer.parseInt(ne.nextToken());
        graph = new ArrayList<>();
        for(int i = 0 ; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0 ; i < E; i++) {
            StringTokenizer edge = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(edge.nextToken());
            int to = Integer.parseInt(edge.nextToken());
            int cost = Integer.parseInt(edge.nextToken());
            graph.get(from).add(new Node(to, cost));
            graph.get(to).add(new Node(from, cost));
        }
        StringTokenizer mg = new StringTokenizer(bufferedReader.readLine());
        mustGo = new int[2];
        mustGo[0] = Integer.parseInt(mg.nextToken());
        mustGo[1] = Integer.parseInt(mg.nextToken());
    }

    private static int dijkstra(int start, int end) {
        int[] distance = new int[N+1];
        Arrays.fill(distance, MAX);
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            int idx = pq.poll().idx;
            if(visited[idx]) continue;
            visited[idx] = true;

            for(Node node : graph.get(idx)) {
                if(distance[node.idx] > distance[idx] + node.distance) {
                    distance[node.idx] = distance[idx] + node.distance;
                    pq.add(new Node(node.idx, distance[node.idx]));
                }
            }
        }
        return distance[end];
    }

    private static void solve() throws IOException {
        int d1 = dijkstra(1, mustGo[0]) + dijkstra(mustGo[0], mustGo[1]) + dijkstra(mustGo[1], N);
        int d2 = dijkstra(1, mustGo[1]) + dijkstra(mustGo[1], mustGo[0]) + dijkstra(mustGo[0], N);
        //System.out.println(d1 + " " + d2);
        int result = Math.min(d1, d2);
        result = result >= MAX || result < 0 ? -1 : result;
        bufferedWriter.write(result + "\n");
    }
}
