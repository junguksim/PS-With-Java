package swea;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_정사각형방_D4 {
    static int[] dx = {-1, 1, 0 ,0};
    static int[] dy = {0,0,1,-1};
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] rooms;
    static Stack<Room> results;

    static void input() throws IOException {
        N =Integer.parseInt(bufferedReader.readLine());
        rooms = new int[N][N];
        results = new Stack<>();
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < N; j++) {
                rooms[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class Room {
        int roomNo;
        int howMany;
        public Room(int roomNo, int howMany) {
            this.roomNo = roomNo;
            this.howMany = howMany;
        }
    }

    static void dfs(int sx, int sy) {
        Stack<Integer> stack = new Stack<>();
        stack.push(rooms[sy][sx]);
        int di = 0;
        int x = sx;
        int y = sy;
        while(!stack.isEmpty() && di < 4) {
            int nx = x + dx[di];
            int ny = y + dy[di];
            di++;
            if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
            if(rooms[ny][nx] - stack.peek() != 1) {
                continue;
            } // rooms[sy][sx] = 1 -> stack 담았음
            // rooms[ny][nx] = 2;
            // stack.peek() = 1;
            stack.add(rooms[ny][nx]); //stack : [1, 2];
            x = nx; // 0, 1
            y = ny; //
            di = 0;
        }
        //System.out.printf("(%d, %d) : %s\n", sy, sx, stack.toString());
        Room result = new Room(rooms[sy][sx], stack.size());
        //1 2
        //3 4
        // 1 : Room(1, 2);
        // 2 : Room(2, 0);
        // 3 : Room(3, 2);
        // 4 : Room(4, 0);
        // (1,2) (3,2) - > 1,2

        if(results.isEmpty()) {
            results.add(result);
        } else if(results.peek().howMany < result.howMany || (results.peek().howMany == result.howMany && results.peek().roomNo >= result.roomNo)) {
            results.pop();
            results.add(result);
        }
    }

    static void solve(int idx) throws IOException {
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < N; j++) {
                dfs(i, j);
            }
        }
        bufferedWriter.write("#"+idx+" "+results.peek().roomNo+ " " + results.peek().howMany+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            input();
            solve(i+1);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
