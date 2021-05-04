package swea;
import java.io.*;
import java.util.StringTokenizer;

public class Solution_2016년요일맞추기_5515 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int m,d;

    public static void main(String[] args) throws IOException  {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T; i++) {
            input();
            solve(i);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
    
    private static void input() throws IOException {
        StringTokenizer md = new StringTokenizer(bufferedReader.readLine());
        m = Integer.parseInt(md.nextToken());
        d = Integer.parseInt(md.nextToken());
    }

    private static void solve(int t) throws IOException {
        int weekday = 4;
        int month = 0;
        int day = 1;
        int[] days = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
        while (month+1 != m || day != d) {
            //System.out.printf("%d 월 %d 일은 : " + weekday + "요일\n", month, day, weekday);
            // 31, 29, 31, 30, 31, 30, 31, 30, 31, 30, 31, 30, 31
            if(day < days[month]) {
                day++;
            } else {
                month++;
                day = 1;
            }
            weekday = (weekday + 1) % 7;
        }
        bufferedWriter.write("#"+t+" "+weekday+"\n");
    }
}