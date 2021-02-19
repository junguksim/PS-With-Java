package jungol;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class jungol_냉장고_심정욱 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, cnt = 1;
    static Temp[] temps;

    static class Temp implements Comparable<Temp>{
        int min;
        int max;

        public Temp(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public int compareTo(Temp o) {
            return this.max - o.max;
        }
    }


    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        temps = new Temp[N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            temps[i] = new Temp(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(temps);
    }

    static void solve() {
        ArrayList<Temp> list = new ArrayList<>();
        list.add(temps[0]);
        for(int i = 1; i < N; i++) {
            if(list.get(list.size() - 1).max < temps[i].min) {
                list.add(temps[i]);
                cnt++;
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
