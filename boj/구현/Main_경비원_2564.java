package boj.구현;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_경비원_2564 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int R,C,N, last;
    private static int[] street;
    private static int[] houses;
    private static int startIndex;

    private static void input() throws IOException {
        StringTokenizer rc = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(rc.nextToken());
        C = Integer.parseInt(rc.nextToken());
        last = (R+C)*2;
        street = new int[(R+C) * 2];
        N = Integer.parseInt(bufferedReader.readLine());
        houses = new int[N+1];
        for(int i = 0; i < N; i++) {
            StringTokenizer n = new StringTokenizer(bufferedReader.readLine());
            int line = Integer.parseInt(n.nextToken());
            int pos = Integer.parseInt(n.nextToken());
            // 두 개의 자연수로 표현
            setNode(line, pos, i+1, false);
        }
        StringTokenizer s = new StringTokenizer(bufferedReader.readLine());
        int line = Integer.parseInt(s.nextToken());
        int pos = Integer.parseInt(s.nextToken());
        setNode(line, pos, -1, true);
    }

    private static void setNode(int line, int pos, int n, boolean isStart) {
        if(isStart) {
            switch (line) {
                case 1:
                    street[pos] = n;
                    startIndex = pos;
                    break;
                case 2:
                    street[R + C + (R - pos)] = n;
                    startIndex = R + C + (R - pos);
                    break;
                case 3:
                    street[R + C + R+ (C - pos)] = n;
                    startIndex = R + C + R+ (C - pos);
                    break;
                case 4:
                    street[R + pos] = n;
                    startIndex = R + pos;
                    break;
            }
        } else {
            switch (line) {
                case 1:
                    street[pos] = n;
                    houses[n] = pos;
                    break;
                case 2:
                    street[R + C + (R - pos)] = n;
                    houses[n] = R + C + (R - pos);
                    break;
                case 3:
                    street[R + C + R+ (C - pos)] = n;
                    houses[n] = R + C + R+ (C - pos);
                    break;
                case 4:
                    street[R + pos] = n;
                    houses[n] = R + pos;
                    break;
            }
        }
    }

    private static int getMinDis(int i) {
        int goEndAndStartDis = (last-startIndex + houses[i]);
        int goThroughDis = Math.abs(houses[i] - startIndex);
        int goStartAndReturnDis = startIndex + last - houses[i];
        //System.out.println(goThroughDis + " " + goStartAndReturnDis + " " + goEndAndStartDis);
        return Math.min(goEndAndStartDis, Math.min(goThroughDis, goStartAndReturnDis));
    }
    private static void solve() throws IOException {
        int answer = 0;
//        System.out.println(Arrays.toString(houses));
//        System.out.println(startIndex);
        for(int i = 1; i <= N; i++) {
            answer += getMinDis(i);
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
