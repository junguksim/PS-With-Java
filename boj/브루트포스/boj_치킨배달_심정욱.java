package boj.브루트포스;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_치킨배달_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N,M, answer;
    static int[][] map;
    static ArrayList<Node> chickens, houses;
    static Node[] temp;

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        map = new int[N+1][N+1];
        chickens = new ArrayList<>();
        houses = new ArrayList<>();
        temp = new Node[M];
        answer = Integer.MAX_VALUE;
        for(int i = 1 ; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    chickens.add(new Node(i,j));
                } else if(map[i][j] == 1) {
                    houses.add(new Node(i,j));
                }
            }
        }
    }

    static void makeCombination(int cnt, int start) {
        if(cnt == M) {
            answer = Math.min(answer, getCityChickenDis());
            return;
        }
        for(int i = start; i < chickens.size(); i++) {
            temp[cnt] = chickens.get(i);
            makeCombination(cnt+1, i + 1);
        }
    }
    static int getHouseChickenDis(int x, int y) {
        int dis = Integer.MAX_VALUE;
        for(int i = 0 ; i < temp.length; i++) {
            int xDis = Math.abs(temp[i].x - x);
            int yDis = Math.abs(temp[i].y - y);
            dis = Math.min(xDis+yDis, dis);
        }
        return dis;
    }

    static int getCityChickenDis() {
        int sum = 0;
        for(int i = 0 ; i < houses.size(); i++) {
            Node house = houses.get(i);
            sum += getHouseChickenDis(house.x, house.y);
        }
        return sum;
    }

    static void solve() {
        makeCombination(0, 0);
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
