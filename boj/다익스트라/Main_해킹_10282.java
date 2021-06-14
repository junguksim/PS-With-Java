package boj.다익스트라;

import java.io.*;
import java.util.*;

public class Main_해킹_10282 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int T,N,D,C;
    private static final int MAX = 987654321;
    private static List<List<Node>> graph;

    private static class Node implements Comparable<Node> {
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
        T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            input();
            solve();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
    
    private static void input() throws IOException {
        StringTokenizer ndc = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(ndc.nextToken());
        D = Integer.parseInt(ndc.nextToken());
        C = Integer.parseInt(ndc.nextToken());
        graph = new ArrayList<>();
        for(int i = 0 ; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0 ; i < D; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, cost));
        }
    }

    private static void solve() throws IOException {
        int[] costs = new int[N+1];
        Arrays.fill(costs, MAX);
        boolean[] visited = new boolean[N+1];

        costs[C] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(C, 0));
        while (!pq.isEmpty()) {
            int idx = pq.poll().idx;
            if(visited[idx]) continue;
            visited[idx] = true;

            for(Node node : graph.get(idx)) {
                if(costs[node.idx] > costs[idx] + node.cost) {
                    costs[node.idx] = costs[idx] + node.cost;
                    pq.add(new Node(node.idx, costs[node.idx]));
                }
            }
        }
        //System.out.println(Arrays.toString(costs));
        int count = 0;
        int max = 0;
        for(int i = 1; i <= N; i++) {
            if(costs[i] < MAX) {
                count++;
                max = Math.max(max, costs[i]);
            }

        }
        bufferedWriter.write(count + " " + max+"\n");
    }
}