package jungol;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class jungol_냉장고_심정욱 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, cnt;
    static ArrayList<Temp> temps;

    static class Temp implements Comparable<Temp>{
        int min;
        int max;

        public Temp(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public int compareTo(Temp o) {
            if (this.min > o.min) {
                return 1;
            }
            return -1;
        }
    }


    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        temps = new ArrayList<>();
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            temps.add(new Temp(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(temps);
    }

    static void solve() {
        while(!temps.isEmpty()) {
            Temp t = temps.remove(0);
            int tMax = t.max;
            cnt++;
            while(!temps.isEmpty()) {
                Temp k = temps.get(0);
                if(k.min > tMax) {
                    break;
                }
                temps.remove(0);
            }
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
