package boj.BFS_DFS;
import java.io.*;
import java.util.*;

public class Main_아기상어_16236 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, time;
    static int[][] map;
    static Shark shark;
    static ArrayList<Fish> fishes;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Node {
        int x;
        int y;
        int depth;

        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
    static class Fish extends Node implements Comparable<Fish>{
        int size;

        public Fish(int x, int y, int depth, int size) {
            super(x, y, depth);
            this.size = size;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "x=" + x +
                    ", y=" + y +
                    ", depth=" + depth +
                    ", size=" + size +
                    '}';
        }

        @Override
        public int compareTo(Fish o) {
            return this.x==o.x?this.y-o.y : this.x-o.x;
        }
    }

    static class Shark {
        int x;
        int y;
        int size;
        int eatFishCount;

        public Shark(int x, int y, int size, int eatFishCount) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.eatFishCount = eatFishCount;
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];
        fishes = new ArrayList<>();
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    shark = new Shark(i,j,2,0);
                } else if(map[i][j] >= 1 && map[i][j] <= 6) {
                    fishes.add(new Fish(i,j, map[i][j], 0));
                }
            }
        }
    }

    static boolean bfs(boolean[][] visited) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(shark.x, shark.y, 0));
        visited[shark.x][shark.y] = true;
        ArrayList<Fish> canEatFishes = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int sx = node.x;
            int sy = node.y;
            int sDepth = node.depth;
            for(int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] > 0 && map[nx][ny] != 9) {
                    if(shark.size > map[nx][ny]) {
                        if(canEatFishes.size() == 0) {
                            canEatFishes.add(new Fish(nx,ny,sDepth+1, map[nx][ny]));
                        } else if(canEatFishes.get(canEatFishes.size() - 1).depth == sDepth + 1) {
                            canEatFishes.add(new Fish(nx,ny,sDepth+1, map[nx][ny]));
                        }

                    } else if(shark.size < map[nx][ny]) {
                        continue;
                    }
                }
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny, sDepth+1));
            }
        }
        Collections.sort(canEatFishes);
        //System.out.println(canEatFishes.toString());
        if(canEatFishes.size() == 0) {
            return false;
        }
        Fish eatFish = canEatFishes.get(0);
        int idx = 0;
        for(int i = 0 ; i < fishes.size(); i++) {
            Fish fish = fishes.get(i);
            if(fish.x == eatFish.x && fish.y == eatFish.y) {
                idx = i;
                shark.eatFishCount++;
                time += eatFish.depth;
                shark.x = eatFish.x;
                shark.y = eatFish.y;
                map[eatFish.x][eatFish.y] = 0;
                break;
            }
        }
        if(shark.eatFishCount == shark.size) {
            shark.size++;
            shark.eatFishCount = 0;
        }
        fishes.remove(idx);
        //System.out.println(eatFish.x + " " + eatFish.y + " 먹음, time : " + time);
        return true;
    }

    static void solve() {
        while (!fishes.isEmpty()) {
            if(!bfs(new boolean[N][N])) {
                break;
            }
        }
        System.out.println(time);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
