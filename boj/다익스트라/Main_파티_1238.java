package boj.다익스트라;

import java.io.*;
import java.util.*;

public class Main_파티_1238 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,M,X;
    private static List<List<Node>> list, reverseList;
    private static final int MAX = 987654321;
    private static int[] dist, reverseDist;

    private static class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
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
    }

    private static void input() throws IOException {
        StringTokenizer nmx = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nmx.nextToken());
        M = Integer.parseInt(nmx.nextToken());
        X = Integer.parseInt(nmx.nextToken());
        list = new ArrayList<>();
        reverseList = new ArrayList<>();
        dist = new int[N+1];
        reverseDist = new int[N+1];
        Arrays.fill(dist, MAX);
        Arrays.fill(reverseDist, MAX);

        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }

        for(int i = 0 ; i < M; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(line.nextToken());
            int to = Integer.parseInt(line.nextToken());
            int cost = Integer.parseInt(line.nextToken());
            list.get(from).add(new Node(to, cost));
            reverseList.get(to).add(new Node(from, cost));
        }
    }

    private static void dijkstra(List<List<Node>> list, int[] distance, int start) {
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        distance[start] = 0;
        while (!pq.isEmpty()) {
            int idx = pq.poll().index;
            if(visited[idx]) continue;
            visited[idx] = true;

            for(Node node : list.get(idx)) {
                if(distance[node.index] > distance[idx] + node.distance) {
                    distance[node.index] = distance[idx] + node.distance;
                    pq.add(new Node(node.index, distance[node.index]));
                }
            }
        }
    }

    private static void solve() throws IOException {
        int answer = 0;
        dijkstra(list, dist, X);
        dijkstra(reverseList, reverseDist, X);
        for(int i = 1; i <= N; i++) {
            answer = Math.max(answer, dist[i] + reverseDist[i]);
        }
        System.out.println(answer);
    }
}