package swea;

import java.io.*;
import java.util.*;

public class Solution_Contact_D4 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int len, start;
    static LinkedList<Node>[] graph;
    static Node[] visited;

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.x==o.x? this.x - o.x : this.count - o.count;
        }
    }


    static void input() throws IOException {
        StringTokenizer lenStart = new StringTokenizer(bufferedReader.readLine());
        len = Integer.parseInt(lenStart.nextToken());
        start = Integer.parseInt(lenStart.nextToken());
        graph = new LinkedList[101];
        visited = new Node[101];
        for(int i = 0 ; i < 101; i++) {
           graph[i] = new LinkedList<>();
           visited[i] = new Node(i,0,0);
        }
        StringTokenizer nums = new StringTokenizer(bufferedReader.readLine());
        while (nums.countTokens() >= 2) {
            int from = Integer.parseInt(nums.nextToken());
            int to = Integer.parseInt(nums.nextToken());
            graph[from].add(new Node(to, from, 0));
        }
    }

    static void dfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int v;
        Node next;
        Iterator<Node> link;

        while(!queue.isEmpty()) {
            v = queue.poll();
            link = graph[v].iterator();
            while (link.hasNext()) {
                next = link.next();

                if(visited[next.x].count == 0) {
                    queue.offer(next.x);
                    visited[next.x].count = visited[next.y].count + 1;
                }
            }
        }
    }


    static void solve(int i) throws IOException {
        dfs();
        Arrays.sort(visited);
        bufferedWriter.write("#"+i+" "+visited[100].x+"\n");
    }

    public static void main(String[] args) throws IOException {
        for(int i = 1; i <= 10; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
