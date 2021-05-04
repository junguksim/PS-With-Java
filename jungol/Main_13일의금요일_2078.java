package jungol;

import java.io.*;

public class Main_13일의금요일_2078 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static  int[] totalWeekDay;
    private static int weekday;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
    
    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
    }

    private static boolean isYoonNyeon(int n) {
        int year = 1900 + n - 1;
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    private static void setWeekDay(int n, int[] weekdays) {
        int month = 0;
        int day = 1;
        while (month < 12 && day <= 31) {
            //System.out.printf("%d 년 %d 월 %d 일은 : %d 요일\n", 1900 + n -1 ,month, day, weekday);
            if(day == 13) {
                totalWeekDay[weekday]++;
            }
            if(day < weekdays[month]) {
                day++;
            } else {
                month++;
                day = 1;
            }
            weekday = (weekday + 1) % 7;
        }
    }

    private static void solve() {
        int start = 1;
        int[] yoondays = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
        int[] normaldays = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        totalWeekDay = new int[7];
        weekday = 0;
        while (start <= N) {
            if(isYoonNyeon(start)) {
                setWeekDay(start, yoondays);
            } else {
                setWeekDay(start, normaldays);
            }
            start++;
        }
        for(int i = 0; i < totalWeekDay.length ; i++) {
            System.out.print(totalWeekDay[i] + " ");
        }

    }
}
