package boj.구현;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_청소년상어_19236 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] dx = {0,-1,-1,0,1,1,1,0,-1};
    private static int[] dy = {0,0,-1,-1,-1,0,1,1,1};
    private static int[][] fishNums;
    private static int[][] fishDirs;
    private static Node shark;
    private static ArrayList<Fish> fishes;
    private static int ans;

    private static class Node {
        int x;
        int y;
        int d;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", d=" + d +
                    '}';
        }
    }

    private static class Fish extends Node implements Comparable<Fish>{
        int no;
        boolean alive;

        public Fish(int x, int y, int d, int no, boolean alive) {
            super(x, y, d);
            this.no = no;
            this.alive = alive;
        }

        @Override
        public int compareTo(Fish o) {
            return this.no - o.no;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "x=" + x +
                    ", y=" + y +
                    ", d=" + d +
                    ", no=" + no +
                    ", alive=" + alive +
                    '}';
        }
    }
    // 0,0 물고기 먹고 시작 -> 0,0 의 물고기의 방향을 가짐
    // 물고기 이동
    // 물고기는 한 칸 이동, (빈칸, 다른 물고기가 있는 칸은 이동 가능, 상어 있는 칸 이동 불가)
    // 이동할 수 있는 칸을 향할 때 까지 45도 반시계 회전
    // 이동할 수 있는 칸이 없으면 이동하지 않는다.
    // 다른 물고기 있는 칸으로 이동할 때 서로의 위치 바꿈

    // 상어 이동
    // 방향에 있는 칸으로, 여러 개의 칸 이동 가능
    // 물고기가 있다면 먹고, 그 물고기의 방향을 가짐.
    // 지나가는 칸의 물고기는 먹지 않고, 물고기가 없는 칸으로 이동 불가
    // 이동할 수 있는 칸이 없으면 끝!

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void input() throws IOException {
        fishNums = new int[4][4];
        fishDirs = new int[4][4];
        fishes = new ArrayList<>();
        for(int i = 0 ; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < 8; j++) {
                if(j % 2 == 0) {
                    fishNums[i][j / 2] = Integer.parseInt(st.nextToken());
                } else {
                    fishDirs[i][j / 2] = Integer.parseInt(st.nextToken());
                }
            }
        }
        for(int i = 0 ; i < 4; i++) {
            for(int j = 0 ; j < 4; j++) {
                fishes.add(new Fish(i,j,fishDirs[i][j], fishNums[i][j], true));
            }
        }
        Collections.sort(fishes);
    }

    private static boolean inRange(int nx, int ny) {
        return nx >= 0 && nx < 4 && ny >= 0 && ny < 4;
    }

    private static ArrayList<Fish> getNextCanEatFishes(Node shark, int[][] fishNums, int[][] fishDirs, ArrayList<Fish> fishes) {
        int sx = shark.x;
        int sy = shark.y;
        int sd = shark.d;
        int nx = sx;
        int ny = sy;
        ArrayList<Fish> nextFishes = new ArrayList<>();
        while (inRange(nx, ny)) {
            nx += dx[sd];
            ny += dy[sd];
            if(inRange(nx, ny) && fishNums[nx][ny] > 0) {
                nextFishes.add(new Fish(nx, ny, fishDirs[nx][ny], fishNums[nx][ny], fishes.get(fishNums[nx][ny] - 1).alive));
            }
        }
        return nextFishes;
    }

    private static void swap(int[][] arr, int ax, int ay, int bx, int by) {
        int temp = arr[ax][ay];
        arr[ax][ay] = arr[bx][by];
        arr[bx][by] = temp;
    }

    private static void moveFishes(int[][] fishNums, int[][] fishDirs, ArrayList<Fish> fishes) {
        for(Fish fish : fishes) {
            if(!fish.alive) continue;
            int fx = fish.x;
            int fy = fish.y;
            int fd = fish.d;
            int rotateCount = 0;
            while (rotateCount < 8) {
                int nx = fx + dx[fd];
                int ny = fy + dy[fd];
                if(!inRange(nx, ny) || fishNums[nx][ny] == -1) {
                    fd = (fd + 1) % 9;
                    if(fd == 0) fd = 1;
                    rotateCount++;
                } else {
                    int swapFishNum = fishNums[nx][ny];
                    if(swapFishNum != 0) {
                        fishes.set(swapFishNum - 1, new Fish(fx, fy, fishDirs[nx][ny], swapFishNum, fishes.get(swapFishNum - 1).alive));
                    }
                    fishes.set(fish.no - 1, new Fish(nx, ny, fd, fish.no, fish.alive));
                    fishDirs[fx][fy] = fd;
                    swap(fishNums, fx, fy, nx, ny);
                    swap(fishDirs, fx, fy, nx, ny);
                    break;
                }
            }
        }
    }

    private static int sharkEats(Node shark, Fish nextFish, int[][] fishNums, int[][] fishDirs, ArrayList<Fish> fishes) {
        int sum = 0;
        sum += nextFish.no;
        int sx = shark.x;
        int sy = shark.y;
        int nx = nextFish.x;
        int ny = nextFish.y;
        fishes.set(nextFish.no-1, new Fish(nextFish.x, nextFish.y, fishDirs[nx][ny], fishNums[nx][ny], false));
        fishNums[nx][ny] = -1;
        fishNums[sx][sy] = 0;
        return sum;
    }

    private static int[][] clone(int[][] arr) {
        int[][] next = new int[4][4];
        for(int i = 0 ; i < 4; i++) {
            next[i] = arr[i].clone();
        }
        return next;
    }

    private static void dfs(Node shark, int[][] fishNums, int[][] fishDirs, ArrayList<Fish> fishes, int answer) {
        ArrayList<Fish> nextFishes = getNextCanEatFishes(shark, fishNums, fishDirs, fishes);
        if(nextFishes.size() == 0) {
            ans = Math.max(ans, answer);
            return;
        }
        for(Fish fish : nextFishes) {
            int[][] fNums = clone(fishNums);
            int[][] fDirs = clone(fishDirs);
            ArrayList<Fish> cFishes = new ArrayList<>(fishes);
            Node nShark = new Node(shark.x, shark.y, shark.d);
            int eatNo = sharkEats(nShark, fish, fNums, fDirs, cFishes);
            nShark = new Node(fish.x, fish.y, fDirs[fish.x][fish.y]);
            moveFishes(fNums, fDirs, cFishes);
            dfs(nShark, fNums, fDirs, cFishes, answer + eatNo);
        }
    }

    private static void solve() throws IOException {
        int eatNo = fishNums[0][0];
        fishes.set(fishNums[0][0] - 1, new Fish(0,0,fishDirs[0][0], fishNums[0][0], false));
        fishNums[0][0] = -1;
        shark = new Node(0,0, fishDirs[0][0]);
        moveFishes(fishNums, fishDirs, fishes);
        dfs(shark, fishNums, fishDirs, fishes, eatNo);
        System.out.println(ans);
    }
}