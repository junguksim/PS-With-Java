package swea;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_규영이와인영이의카드게임_D3 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int CARD_COUNT = 9;
    static int[] gyuYeong, inYeong, permutation;
    static boolean[] isSelected, isPickedByGyuYeong;
    static int win, lose;

    static void input() throws IOException {
        gyuYeong = new int[CARD_COUNT];
        inYeong = new int[CARD_COUNT];
        isPickedByGyuYeong = new boolean[19];
        permutation = new int[9];
        isSelected = new boolean[9];
        win = 0;
        lose = 0;
        StringTokenizer numInput = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0 ; i < CARD_COUNT; i++) {
            gyuYeong[i] = Integer.parseInt(numInput.nextToken());
            isPickedByGyuYeong[gyuYeong[i]] = true;
        }
        int idx = 0;
        for(int i = 1; i <= CARD_COUNT * 2 ; i++) {
            if(!isPickedByGyuYeong[i]) {
                inYeong[idx] = i;
                idx++;
            }
        }
    }

    static void end() {
        int inYeongSum = 0;
        int gyuYeongSum = 0;
        for(int i = 0 ; i < CARD_COUNT; i++) {
            if(gyuYeong[i] > permutation[i]) {
                gyuYeongSum += gyuYeong[i] + permutation[i];
            } else {
                inYeongSum += gyuYeong[i] + permutation[i];
            }
        }
        if(gyuYeongSum > inYeongSum) {
            win++;
        } else {
            lose++;
        }
    }

    static void perm(int cnt) {
        if(cnt == CARD_COUNT) {
            end();
            return;
        }
        for(int i = 0 ; i < 9; i++) {
            if(isSelected[i]) continue;
            permutation[cnt] = inYeong[i];
            isSelected[i] = true;
            perm(cnt+1);
            isSelected[i] = false;
        }
    }

    static void solve(int idx) throws IOException {
        perm(0);
        StringBuilder sb = new StringBuilder();
        sb.append("#").append(idx).append(" ").append(win).append(" ").append(lose).append("\n");
        bufferedWriter.write(sb.toString());
    }
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i<= T; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
