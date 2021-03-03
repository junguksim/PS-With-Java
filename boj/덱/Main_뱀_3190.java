package boj.덱;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_뱀_3190 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N,K,L, snakeDir, count;
    static short[][] map;
    static Deque<Node> snake;
    static Deque<Movement> movements;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1}; // 상, 우, 하, 좌

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Movement {
        int time;
        char dir;

        public Movement(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        K = Integer.parseInt(bufferedReader.readLine());
        map = new short[N][N];
        movements = new ArrayDeque<>();
        snake = new ArrayDeque<>();
        snake.addLast(new Node(0,0));
        count = 0;
        snakeDir = 1; // 오른쪽 방향을 보고 시작
        for(int i = 0; i < K; i++) {
            StringTokenizer xy = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(xy.nextToken()) - 1;
            int y = Integer.parseInt(xy.nextToken()) - 1; // input 이 1,1 부터 시작하는 기준이라서 1씩 빼준다.
            map[x][y] = 1;
        }
        L = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            movements.offer(new Movement(Integer.parseInt(st.nextToken()),  st.nextToken().charAt(0)));
        }
    }

    static boolean isOut(int nx, int ny) {
        if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
            return true;
        }
        return false;
    }

    static boolean isSelfBody(int nx, int ny) {
        for(Node n : snake) {
            if(n.x == nx && n.y == ny) {
                return true;
            }
        }
        return false;
    }

    static void checkChangeDir() {
        if(!movements.isEmpty() && movements.peekFirst().time + 1 == count) {
            Movement m = movements.pollFirst();
            if(m.dir == 'D') {
                snakeDir = (snakeDir + 1) % 4;
            } else {
                if(snakeDir == 0) {
                    snakeDir = 3;
                } else {
                    snakeDir -= 1;
                }
            }
        }
    }

    static boolean isApple(int nx, int ny) {
        return map[nx][ny] == 1;
    }

    static void solve() {
        while (true) {
            ++count;
            Node head = snake.peekLast();
            Node tail = snake.peekFirst();
            int hx = head.x;
            int hy = head.y;
            checkChangeDir();
            int nx = hx + dx[snakeDir];
            int ny = hy + dy[snakeDir];
            if(isOut(nx, ny) || isSelfBody(nx, ny)) {
                break;
            }
            snake.addLast(new Node(nx, ny));
            if(!isApple(nx, ny)) {
                snake.pollFirst();
            } else {
                map[nx][ny] = 0;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
