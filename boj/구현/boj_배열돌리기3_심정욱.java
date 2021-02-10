package boj.구현;

import java.io.*;
import java.util.StringTokenizer;

public class boj_배열돌리기3_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,M,R;
    static int[][] arr, copies;
    static int[] ops;

    static void upsideDown() {
        copies = new int[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++) {
            copies[i] = arr[arr.length-1-i];
        }
        arr = copies;
    }

    static void leftRightReverse() {
        copies = new int[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0 ; j < arr[0].length; j++) {
                copies[i][j] = arr[i][arr[0].length-1-j];
            }
        }
        arr = copies;
    }

    static void turnRight() {
        copies = new int[arr[0].length][arr.length];
        for (int i =0; i < arr[0].length; i++) {
            for(int j = 0; j < arr.length; j++) {
                copies[i][j] = arr[arr.length-1-j][i];
            }
        }
        arr = copies;
    }

    static void turnLeft() {
        copies = new int[arr[0].length][arr.length];
        for (int i =0; i < arr[0].length; i++) {
            for(int j = 0; j < arr.length; j++) {
                copies[i][j] = arr[j][arr[0].length-1-i];
            }
        }
        arr = copies;
    }
    static void clockWise() {
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] result = new int[rows][cols];

        //1사분면->2사분면
        for(int r = 0 ; r < rows/2; r++) {
            for(int c = 0; c < cols/2; c++) {
                result[r][c+cols/2] = arr[r][c];
            }
        }
        //2사분면->3사분면
        for(int r = 0 ; r < rows/2; r++) {
            for(int c = cols/2; c < cols; c++) {
                result[r + rows/2][c] = arr[r][c];
            }
        }
        //3사분면->4사분면
        for(int r = rows/2 ; r < rows; r++) {
            for(int c = cols/2; c < cols; c++) {
                result[r][c-cols/2] = arr[r][c];
            }
        }
        //4사분면->1사분면
        for(int r = rows/2 ; r < rows; r++) {
            for(int c = 0; c < cols/2; c++) {
                result[r-rows/2][c] = arr[r][c];
            }
        }
        arr = result;
    }
    static void counterClockWise() {
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] result = new int[rows][cols];
        //1사분면->4사분면
        for(int r = 0 ; r < rows/2; r++) {
            for(int c = 0; c < cols/2; c++) {
                result[r+rows/2][c] = arr[r][c];
            }
        }
        //4사분면->3사분면
        for(int r = rows/2 ; r < rows; r++) {
            for(int c = 0; c < cols/2; c++) {
                result[r][c+cols/2] = arr[r][c];
            }
        }
        //3사분면->2사분면
        for(int r = rows/2 ; r < rows; r++) {
            for(int c = cols/2; c < cols; c++) {
                result[r-rows/2][c] = arr[r][c];
            }
        }
        //2사분면->1사분면
        for(int r = 0 ; r < rows/2; r++) {
            for(int c = cols/2; c < cols; c++) {
                result[r][c-cols/2] = arr[r][c];
            }
        }
        arr = result;
    }

    static void solve() throws IOException {
        for(int i = 0 ; i < R; i++) {
            switch (ops[i]) {
                case 1:
                    upsideDown();
                    break;
                case 2:
                    leftRightReverse();
                    break;
                case 3:
                    turnRight();
                    break;
                case 4:
                    turnLeft();
                    break;
                case 5:
                    clockWise();
                    break;
                case 6:
                    counterClockWise();
                    break;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for(int j = 0 ; j < arr[i].length; j++) {
                bufferedWriter.write(arr[i][j] + " ");
            }
            bufferedWriter.write("\n");
        }
    }

    static void input() throws IOException {
        StringTokenizer nmr = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nmr.nextToken());
        M = Integer.parseInt(nmr.nextToken());
        R = Integer.parseInt(nmr.nextToken());
        arr = new int[N][M];
        ops = new int[R];
        for (int i = 0; i < N; i++) {
            StringTokenizer data = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(data.nextToken());
            }
        }
        StringTokenizer opLine = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < R; i++) {
            ops[i] = Integer.parseInt(opLine.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
