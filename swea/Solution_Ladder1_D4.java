package swea;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_Ladder1_D4 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] ladders;
    static boolean[][] visited = new boolean[100][100];
    static int startX, startY;

    static void input() throws IOException {
        ladders = new int[100][100];
        for(int i = 0 ; i < 100; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < 100; j++) {
                ladders[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if(ladders[i][j] == 2) {
                    startX = j;
                    startY = i;
                }
            }
        }
    }

    static void solve(int idx) throws IOException {
        //System.out.println(Arrays.deepToString(ladders));
        int x = startX;
        int y = startY;
        while(x < 100 && y < 100 && x >= 0 && y >= 0) {
            if(y == 0) {
                bufferedWriter.write("#"+idx+" "+x+"\n");
                break;
            }
            visited[y][x] = true;
            if(x + 1 < 100 && !visited[y][x+1] &&  ladders[y][x+1] == 1) {
                x += 1;
                continue;
            }
            if(x - 1 >= 0 && !visited[y][x-1] && ladders[y][x-1] == 1) {
                x -= 1;
                continue;
            }
            if(ladders[y-1][x] == 1) {
                y -= 1;
                continue;
            }
            break;
        }

    }

    public static void main(String[] args) throws IOException {
        for(int i = 0 ; i < 10; i++) {
            int T = Integer.parseInt(bufferedReader.readLine());
            input();
            solve(T);
            for(int j = 0 ; j < 100; j++) {
                Arrays.fill(visited[j], false);
            }
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
