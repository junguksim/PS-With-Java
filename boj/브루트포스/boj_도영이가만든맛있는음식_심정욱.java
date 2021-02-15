package boj.브루트포스;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_도영이가만든맛있는음식_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, min;
    static Flavor[] flavors;
    static boolean[] visited;

    static class Flavor {
        int S;
        int B;

        public Flavor(int s, int b) {
            S = s;
            B = b;
        }

        @Override
        public String toString() {
            return "Flavor{" +
                    "S=" + S +
                    ", B=" + B +
                    '}';
        }
    }

    static void generateSubset(int cnt) {
        if(cnt == N) {
            int sSum = 1;
            int bSum = 0;
            for(int i = 0 ; i < N; i++) {
                if(visited[i]) {
                    sSum *= flavors[i].S;
                    bSum += flavors[i].B;
                    //System.out.print(flavors[i] + " ");
                }
            }
            //System.out.println();
            if(sSum == 1 && bSum == 0) {
                return;
            }
            //System.out.println(Math.abs(sSum - bSum));
            min = Math.min(min, Math.abs(sSum - bSum));
            return;
        }
        visited[cnt] = false;
        generateSubset(cnt + 1);
        visited[cnt] = true;
        generateSubset(cnt + 1);
    }

    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        min = Integer.MAX_VALUE;
        flavors = new Flavor[N];
        visited = new boolean[N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            flavors[i] = new Flavor(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        generateSubset(0);
        System.out.println(min);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
