package boj.그래프;
import java.io.*;
import java.util.*;

public class Main_최단경로_1753 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int V,E,K;
    private static List<Node>[] graph;
    private static int[] distance;
    private static boolean[] visited;

    private static class Node implements Comparable<Node>{
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    private static void input() throws IOException {
        StringTokenizer ve = new StringTokenizer(bufferedReader.readLine());
        V = Integer.parseInt(ve.nextToken());
        E = Integer.parseInt(ve.nextToken());
        K = Integer.parseInt(bufferedReader.readLine());
        graph = new ArrayList[V+1];
        for(int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        distance = new int[V+1];
        visited = new boolean[V+1];
        for(int i = 0 ; i < E; i++) {
            StringTokenizer uvw = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(uvw.nextToken());
            int to = Integer.parseInt(uvw.nextToken());
            int weight = Integer.parseInt(uvw.nextToken());
            graph[from].add(new Node(to, weight));
        }
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[K] = 0;
    }

    private static void solve() throws IOException {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(K, 0));
        distance[K] = 0;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int cur = curNode.to;

            if(visited[cur]) continue;
            visited[cur] = true;

            for(Node node : graph[cur]) {
                if(distance[node.to] > distance[cur] + node.weight) {
                    distance[node.to] = distance[cur] + node.weight;
                    queue.add(new Node(node.to, distance[node.to]));
                }
            }
        }
        for(int i = 1; i <= V; i++) {
            if(distance[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(distance[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
