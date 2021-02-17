package boj.브루트포스;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_야구_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, answer;
    static int[][] nums;
    static int[] batterNumbers;
    static int[] temp;
    static boolean[] select;
    static int[] lineUp;

    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        nums = new int[N][9];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < 9; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        batterNumbers= new int[]{0,1,2,3,4,5,6,7,8};
        answer = Integer.MIN_VALUE;
    }

    public static void perm(int num) {
        if (num == 9) {
            int sum =calc();
            answer = Math.max(sum ,answer);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (select[i]) {
                continue;
            }
            select[i] = true;
            lineUp[i] = num;
            perm(num + 1);
            select[i] = false;
        }
    }

    static int calc() {
        int sum = 0, i = 0;
        for(int in = 0; in < N; in++) {
            int out = 0;
            int[] field = new int[4];
            while(out < 3) {
                if(nums[in][lineUp[i]] == 0) {
                    out++;
                } else {
                    field[0]++;
                    sum += bat(field, nums[in][lineUp[i]]);
                }
                i = (i + 1) % 9;
            }
        }
        return sum;
    }

    static int bat(int[] field, int num) {
        int sum = 0;
        if(num == 1) {
            sum += field[3];
            for(int i = 2; i >= 0; i--) {
                field[i+1] = field[i];
            }
            field[0] = 0;
        } else if(num == 2) {
            sum += field[2] + field[3];
            for(int i = 1; i >= 0; i--) {
                field[i+2] = field[i];
            }
            field[1] = field[0] = 0;
        } else if(num == 3) {
            sum += field[1] + field[2] + field[3];
            field[3] = field[0];
            field[2] = field[1] = field[0] = 0;
        } else {
            sum += field[0] + field[1] + field[2] + field[3];
            field[3] = field[2] = field[1] = field[0] = 0;
        }
        return sum;
    }

    static void solve() {
        select = new boolean[9];
        lineUp = new int[9];
        select[3] = true;
        lineUp[3] = 0;
        perm(1);
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
