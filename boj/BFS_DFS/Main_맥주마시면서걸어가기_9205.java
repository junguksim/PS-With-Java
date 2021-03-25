package boj.BFS_DFS;

import java.io.*;
import java.util.*;

public class Main_맥주마시면서걸어가기_9205 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static Node home, festival;
    static Node[] locations;
    static ArrayList<Node>[] adjList;
    static boolean[] visited;

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        if(N == 0) {
            StringTokenizer homeSt = new StringTokenizer(bufferedReader.readLine());
            home = new Node(Integer.parseInt(homeSt.nextToken()), Integer.parseInt(homeSt.nextToken()));
            StringTokenizer festivalSt = new StringTokenizer(bufferedReader.readLine());
            festival = new Node(Integer.parseInt(festivalSt.nextToken()), Integer.parseInt(festivalSt.nextToken()));
            return;
        }
        StringTokenizer homeSt = new StringTokenizer(bufferedReader.readLine());
        home = new Node(Integer.parseInt(homeSt.nextToken()), Integer.parseInt(homeSt.nextToken()));
        locations = new Node[N+2];
        visited = new boolean[N+2];
        adjList = new ArrayList[N+2];
        for(int i = 1 ; i <= N; i++) {
            StringTokenizer storeSt = new StringTokenizer(bufferedReader.readLine());
            locations[i] = new Node(Integer.parseInt(storeSt.nextToken()) , Integer.parseInt(storeSt.nextToken()));
        }
        StringTokenizer festivalSt = new StringTokenizer(bufferedReader.readLine());
        festival = new Node(Integer.parseInt(festivalSt.nextToken()), Integer.parseInt(festivalSt.nextToken()));
        locations[0] = home;
        locations[N+1] = festival;
        for(int i = 0 ; i < N+2; i++) {
            adjList[i] = new ArrayList<>();
            for(int j = 0 ; j < N + 2; j++) {
                adjList[i].add(locations[j]);
            }
        }

    }

    static int getDistance(Node from, Node to) {
        return Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
    }

    static boolean bfs() {
        if(getDistance(home, festival) <= 1000) {
            return true;
        } else if(N == 0) {
            return false;
        }
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(home);
        while (!deque.isEmpty()) {
            Node now = deque.poll();
            if(getDistance(now, festival) <= 1000) {
                return true;
            }
            int idx = -1;
            for(int i = 0; i < locations.length; i++) {
                if(locations[i].equals(now)) {
                    idx = i;
                    break;
                }
            }
            if(idx == -1 || visited[idx]) continue;
            for(Node adjNode : adjList[idx]) {
                if(getDistance(now, adjNode) <= 1000) {
                    deque.add(adjNode);
                }
            }
            visited[idx] = true;
        }
        return false;
    }

    static void solve() throws IOException {
        if(bfs()) {
            bufferedWriter.write("happy\n");
        } else {
            bufferedWriter.write("sad\n");
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T; i++) {
            input();
            solve();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
