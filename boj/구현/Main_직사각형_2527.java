package boj.구현;

import java.io.*;
import java.util.StringTokenizer;

public class Main_직사각형_2527 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        int x3 = Integer.parseInt(st.nextToken());
        int y3 = Integer.parseInt(st.nextToken());
        int x4 = Integer.parseInt(st.nextToken());
        int y4 = Integer.parseInt(st.nextToken());

        if((x2==x3 && y2 == y3) || (x1==x4 && y2 == y3) || (x2==x3 && y1 == y4) || (x1 == x4 && y1 == y4)) {
            bufferedWriter.write("c\n");
        } else if(
                (x2 == x3 && y2 != y3) ||
                (x1 == x4 && y2 != y3) ||
                (x2 != x3 && y1 == y4) ||
                (x1 != x4 && y1 == y4)
        ) {
            bufferedWriter.write("b\n");
        } else if(x2 < x3 || x4 < x1 || y2 < y3 || y4 < y1) bufferedWriter.write("d\n");
         else {
             bufferedWriter.write("a\n");
        }
    }


    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 4; i++) {
            input();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
