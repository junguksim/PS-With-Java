package boj.구현;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 4x4 고정
 * 0,0 에 있던 물고기를 먹고 시작, 첫 방향은 0,0에 있던 물고기의 방향
 * 물고기 이동- 상어이동 반복
 * 물고기 이동
 * 1. 번호가 작은 물고기부터
 * 2. 한 칸만 이동 가능
 * 3. 이동 가능 -> 빈칸, 다른물고기가 있는 칸
 * 4. 이동 불가 -> 바깥이나, 상어가 있는 칸
 * 5. 이동할 수 있는 칸으로 향할 수 있을 때 까지 방향을 45도, "반시계"
 * 6. 이동할 수 있는 칸이 없다면 이동하지 않음.
 * 7. 다른 물고기가 있는 칸으로 이동할 때는 해당 물고기와 위치를 바꿈
 *
 * 상어 이동
 * 1. 방향에 있는 칸으로 이동 가능.
 * 2. 여러 개의 칸 이동 가능.
 * 3. 물고기가 있다면 그 칸의 물고기를 먹고, 그 물고기의 방향을 가지게 된다.
 * 4. 이동 중에 지나가는 칸의 물고기는 먹지 않는다.
 * 5. 물고기가 없는 칸으로는 이동 불가
 * 6. 이동 가능한 칸이 없다면 집으로 간다.
 * 
 * ANSWER : 먹을 수 있는 물고기 번호의 합의 최대
 *
 */
public class Main_청소년상어_19236 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,-1,-1,-1,0,1,1,1};
    static Fish[][] map;
    static ArrayList<Fish> fishes;
    static Shark shark;
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Fish extends Node implements Comparable<Fish>{
        int number;
        int dir;

        public Fish(int x, int y, int number, int dir) {
            super(x, y);
            this.number = number;
            this.dir = dir;
        }

        @Override
        public int compareTo(Fish o) {
            return this.number - o.number;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "x=" + x +
                    ", y=" + y +
                    ", number=" + number +
                    ", dir=" + dir +
                    '}';
        }
    }

    private static class Shark extends Node {
        int dir;
        int sum;

        public Shark(int x, int y, int dir, int sum) {
            super(x, y);
            this.dir = dir;
            this.sum = sum;
        }
    }

    private static void swapFish(Fish f1, Fish f2) {
        int f1Idx = 0;
        int f2Idx = 0;
        for(int i = 0 ; i < fishes.size(); i++) {
            if(fishes.get(i).equals(f1)) {
                f1Idx = i;
            } else if(fishes.get(i).equals(f2)) {
                f2Idx = i;
            }
        }
        fishes.set(f1Idx, new Fish(f1.x, f1.y, f2.number, f2.dir));
        fishes.set(f2Idx, new Fish(f2.x, f2.y, f1.number, f1.dir));
        int x1 = f1.x;
        int y1 = f1.y;
        int x2 = f2.x;
        int y2 = f2.y;
        map[x2][y2] = f1;
        map[x1][y1] = f2;
    }

    private static void fishMove() {
        for(int i = 0; i < fishes.size(); i++) { // 번호가 작은 물고기 부터
            Fish fish = fishes.get(i);
            int nx = fish.x + dx[fish.dir];
            int ny = fish.y + dy[fish.dir];
            int rotateCount = 0;
            while (nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || (nx == shark.x && ny == shark.y) || rotateCount < 8) {
                //다음 칸이 바깥이거나, 상어가 아니게 될 때까지
                fish.dir = (fish.dir + 1) % 8;
                rotateCount++;
            }
            if(rotateCount > 8) {
                continue;
            }
            if(map[nx][ny] == null) {
                // 빈 칸이면 그냥 이동
                map[nx][ny] = fish;
                fish.x = nx;
                fish.y = ny;
            }{
                swapFish(fish, map[nx][ny]);
            }
        }
    }

    private static void eatFish(Fish[][] mapClone, ArrayList<Fish> fishes, Shark shark, int x, int y) {
        shark.dir = mapClone[x][y].dir;
        shark.sum += mapClone[x][y].number;
        fishes.remove(map[x][y]);
        mapClone[x][y] = null;
    }

    private static void sharkMove(Fish[][] mapClone, ArrayList<Fish> fishes, Shark shark, int sum) {
        int sx = shark.x;
        int sy = shark.y;
        int nx = sx + dx[shark.dir];
        int ny = sy + dy[shark.dir];

        while (!(nx < 0 || ny < 0 || nx >= 4 || ny >= 4)) {
            sx = nx;
            sy = ny;
            if(mapClone[sx][sy] != null) {
                // 다음 위치에 물고기가 있다면,
                // 먹고 가는 경우로 하나 보낸다.
                Shark ateShark = new Shark(sx, sy, mapClone[sx][sy].dir, shark.sum + mapClone[sx][sy].number);
                eatFish(mapClone, fishes, sx, sy);

            }

            nx = sx + dx[shark.dir];
            ny = sy + dy[shark.dir];
        }
    }


    private static void input() throws IOException {
        map = new Fish[4][4];
        fishes = new ArrayList<>();
        for(int i = 0 ; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < 4; j++) {
                Fish fish = new Fish(i,j,Integer.parseInt(st.nextToken()) , Integer.parseInt(st.nextToken()) - 1);
                map[i][j] = fish;
                fishes.add(fish);
            }
        }
        Collections.sort(fishes);
        System.out.println(fishes);
    }

    private static void printMap() {
        for(int i = 0 ; i < 4; i++) {
            for(int j = 0 ; j < 4; j++) {
                if(map[i][j] == null) {
                    System.out.print("0 ");
                }
                else {
                    System.out.print(map[i][j].number + " ");
                }
            }
            System.out.println();
        }
    }

    private static void solve() throws IOException {
        shark = new Shark(0,0, 0, 0);
        eatFish(0,0);
        System.out.println(fishes);
        printMap();
        swapFish(map[0][1], map[1][1]);
        System.out.println(fishes);
        printMap();
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
