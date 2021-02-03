package swea;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_상호의배틀필드_D3 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int H,W,N,x,y,dir;
    static char[][] field;
    static char[] userInput;

    /**
     * dir = 0 -> 좌
     *       1 -> 우
     *       2 -> 위
     *       3 -> 아래
     */
    static void initialization() {
        H = 0;
        W = 0;
        N = 0;
        x = 0;
        y = 0;
        dir = 0;
        field = new char[H][W];
        userInput = new char[N];
    }

    static void input() throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        H = Integer.parseInt(stringTokenizer.nextToken());
        W = Integer.parseInt(stringTokenizer.nextToken());
        field = new char[H][W];
        for(int i = 0 ; i < H; i++) {
            String s = bufferedReader.readLine();
            if(s.contains("<")) {
                y = i;
                x = s.indexOf("<");
                dir = 0;
            } else if(s.contains("^")) {
                y = i;
                x = s.indexOf("^");
                dir = 2;
            } else if(s.contains(">")) {
                y = i;
                x = s.indexOf(">");
                dir = 1;
            } else if(s.contains("v")) {
                y = i;
                x = s.indexOf("v");
                dir = 3;
            }
            field[i] = s.toCharArray();
        }
        int N = Integer.parseInt(bufferedReader.readLine());
        userInput = bufferedReader.readLine().toCharArray();
    }

    static void shoot() {
        switch (dir) {
            case 0:
                for(int i = x; i >= 0; i--) {
                    if(field[y][i] == '#') {
                        break;
                    }
                    else if(field[y][i] == '*') {
                        field[y][i] = '.';
                        break;
                    }
                }
                break;
            case 1:
                for(int i = x; i < W; i++) {
                    if(field[y][i] == '#') {
                        break;
                    }
                    else if(field[y][i] == '*') {
                        field[y][i] = '.';
                        break;
                    }
                }
                break;
            case 2:
                for(int i = y; i >= 0; i--) {
                    if(field[i][x] == '#') {
                        break;
                    }
                    else if(field[i][x] == '*') {
                        field[i][x] = '.';
                        break;
                    }
                }
                break;
            case 3:
                for(int i = y; i < H; i++) {
                    if(field[i][x] == '#') {
                        break;
                    }
                    else if(field[i][x] == '*') {
                        field[i][x] = '.';
                        break;
                    }
                }
                break;
        }
    }

    static void userAct(char op) throws IOException {
        switch (op) {
            case 'S':
                shoot();
                break;
            case 'U':
                field[y][x] = '^';
                dir = 2;
                if(y > 0 && field[y - 1][x] == '.') {
                    field[y][x] = '.';
                    field[y-1][x] = '^';
                    y -= 1;
                }
                break;
            case 'D':
                field[y][x] = 'v';
                dir = 3;
                if(y < H - 1 && field[y + 1][x] == '.') {
                    field[y][x] = '.';
                    field[y+1][x] = 'v';
                    y += 1;
                }
                break;
            case 'L':
                field[y][x] = '<';
                dir = 0;
                if(x > 0 && field[y][x-1] == '.') {
                    field[y][x] = '.';
                    field[y][x-1] = '<';
                    x -= 1;
                }
                break;
            case 'R':
                field[y][x] = '>';
                dir = 1;
                if(x < W-1 && field[y][x+1] == '.') {
                    field[y][x] = '.';
                    field[y][x+1] = '>';
                    x += 1;
                }
                break;
        }
    }
    static void solve(int i) throws IOException {
        for(char op : userInput) {
            userAct(op);
        }
        bufferedWriter.write("#"+i + " ");
        for(int j = 0 ; j < H; j++) {
            for(int k = 0; k < W; k++) {
                bufferedWriter.write(field[j][k]);
            }
            bufferedWriter.write("\n");
        }
    }


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            initialization();
            input();
            solve(i+1);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
