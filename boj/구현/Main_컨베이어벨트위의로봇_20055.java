package boj.구현;
import java.io.*;
import java.util.*;

public class Main_컨베이어벨트위의로봇_20055 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,K,cnt;
    static ArrayList<Integer> topA;
    static ArrayList<Integer> bottomA;
    static ArrayList<Boolean> topRobot;
    static void input() throws IOException {
        StringTokenizer nk = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nk.nextToken());
        K = Integer.parseInt(nk.nextToken());
        topA = new ArrayList<>();
        bottomA = new ArrayList<>();
        topRobot = new ArrayList<>();
        StringTokenizer a = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0; i < 2; i++) {
            for(int j = 0 ; j < N;j++) {
                if(i == 0) {
                    topA.add(Integer.parseInt(a.nextToken()));
                    topRobot.add(false);
                } else {
                    bottomA.add(Integer.parseInt(a.nextToken()));
                }
            }
        }
        Collections.reverse(bottomA);
    }

    static void addRobot() {
        //System.out.println("3. 로봇 놓기 단계");
        if(topA.get(0) > 0 && !topRobot.get(0)) {
            topRobot.set(0, true);
            topA.set(0, topA.get(0) - 1);
        }
    }

    static void printRobots() {
        System.out.println("==========로봇 상태==========");
        for(int j = 0 ; j < N;j++) {
            if(topRobot.get(j)) System.out.print("R ");
            else System.out.print("X ");
        }
        System.out.println("\n=========내구도 상태============");
        for(int i = 0; i < 2; i++) {
            for(int j = 0 ; j < N;j++) {
                if(i == 0) System.out.print(topA.get(j) + " ");
                else System.out.print(bottomA.get(j) + " ");
            }
            System.out.println();
        }
        System.out.println("=================");
    }

    static void rotate() {
        //System.out.println("1. 회전 단계");
        topRobot.add(0, false); // 처음에 robot을 없애주고
        topRobot.remove(topRobot.size() - 1); // 마지막에 robot은 땅으로
        topA.add(0, bottomA.get(0));
        bottomA.add(bottomA.size() , topA.get(topA.size() - 1));
        topA.remove(topA.size()-1);
        bottomA.remove(0);
        if(topRobot.get(topRobot.size() - 1)) {
            topRobot.set(topRobot.size() - 1, false);
        }
    }

    static void move() {
        //System.out.println("2. 움직임 단계");

        for(int i = N-1; i > 0; i--) {
            int now = topA.get(i);
            boolean isNowRobot = topRobot.get(i);
            boolean isBeforeRobot = topRobot.get(i-1);
            if(now > 0 && !isNowRobot && isBeforeRobot) {
                topA.set(i, topA.get(i) - 1);
                topRobot.set(i, true);
                topRobot.set(i-1, false);
            }
            if(topRobot.get(topRobot.size() - 1)) {
                topRobot.set(topRobot.size() - 1, false);
            }
        }
    }

    static boolean isZeroMoreThanK() {
        int zeroCnt = 0;
        for(int j = 0 ; j < N; j++) {
            if(topA.get(j) <= 0) {
                zeroCnt++;
            }
        }
        for(int j = 0 ; j < N; j++) {
            if(bottomA.get(j) <= 0) {
                zeroCnt++;
            }
        }
//        System.out.println("4. 체크 단계 : 현재 0인 칸의 갯수 : " + zeroCnt);
//        printRobots();
        if(zeroCnt >= K) {
            return true;
        }
        else {
            return false;
        }
    }

    static void solve() {
        while (!isZeroMoreThanK()) {
            rotate();
            move();
            addRobot();
            cnt++;
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
