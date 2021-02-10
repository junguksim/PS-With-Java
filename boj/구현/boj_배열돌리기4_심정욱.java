package boj.구현;

import java.io.*;
import java.util.StringTokenizer;

public class boj_배열돌리기4_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[] dy = {1,0,-1,0};
    static int[] dx = {0,1,0,-1};
    static int N,M,K, min;
    static int[][] map;
    static Rotate[] rt;

    private static class Rotate {
        int r,c,s;

        public Rotate(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    public static int rsum(int[][] copies) {
        int min = 987654321;
        for(int i = 0; i<N; i++) {
            int sum = 0;
            for(int j = 0; j<M; j++) {
                sum += copies[i][j];
            }
            min = Math.min(min, sum);    //삼항연산자
        }
        return min;
    }
    public static int[][] rot(Rotate[] x) {
        int[][] copies = new int[N][M];
        for(int i = 0; i<N; i++) {
            copies[i] = map[i].clone();
        }

        for (Rotate rotate : x) {
            for (int size = 1; size <= rotate.s; size++) {
                int r = rotate.r - size;
                int c = rotate.c - size;
                int last = copies[r][c];
                //상
                for (int i = 0; i < (size * 2); i++) {
                    copies[r][c] = copies[r + 1][c];
                    r++;
                }
                //좌
                for (int i = 0; i < (size * 2); i++) {
                    copies[r][c] = copies[r][c + 1];
                    c++;
                }
                //하
                for (int i = 0; i < (size * 2); i++) {
                    copies[r][c] = copies[r - 1][c];
                    r--;
                }

                //우
                for (int i = 0; i < (size * 2); i++) {
                    copies[r][c] = copies[r][c - 1];
                    c--;
                }
                copies[r][c + 1] = last;
            }//ROTATION END
        }

        return copies;
    }

    static void perm(int depth, int start) {
        if(depth == K) {
            min = Math.min(rsum(rot(rt)), min);
            return;
        }
        for(int i = start; i < K; i++) {
            Rotate tmp = rt[i];
            rt[i] = rt[start];
            rt[start] = tmp;

            perm(depth+1, start+1);

            tmp = rt[i];
            rt[i] = rt[start];
            rt[start] = tmp;
        }
    }

    static void input() throws IOException {
        StringTokenizer nmk = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nmk.nextToken());
        M = Integer.parseInt(nmk.nextToken());
        K = Integer.parseInt(nmk.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        rt = new Rotate[K];
        for(int i = 0 ; i < K; i++) {
            StringTokenizer rcs = new StringTokenizer(bufferedReader.readLine());
            int r = Integer.parseInt(rcs.nextToken()) -1;
            int c = Integer.parseInt(rcs.nextToken()) - 1;
            int s = Integer.parseInt(rcs.nextToken());
            rt[i] = new Rotate(r,c,s);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        min = Integer.MAX_VALUE;
        perm(0,0);
        System.out.println(min);
        bufferedReader.close();
    }
}
