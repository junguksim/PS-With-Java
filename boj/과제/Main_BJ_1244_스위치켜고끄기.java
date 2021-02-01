package boj.과제;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1244_스위치켜고끄기 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static String[] switches;


    private static void setSwitchCount() throws IOException {
        int switchCount = Integer.parseInt(bufferedReader.readLine());
        switches = new String[switchCount +1];
    }

    private static void setSwitches() throws IOException {
        String[] input = bufferedReader.readLine().split(" ");
        for(int i = 1 ; i <= input.length; i++) {
            switches[i] = input[i-1];
        }
    }

    private static void setStudents() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
    }

    private static void changeNum(int i) {
        if(switches[i].equals("0")) {
            switches[i] = "1";
        }
        else {
            switches[i] = "0";
        }
    }
    private static void solve() throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        if(stringTokenizer.nextToken().equals("1")) {
            // 남학생
            int num = Integer.parseInt(stringTokenizer.nextToken());
            int index = 1;
            while(num * index < switches.length) {
                changeNum(num * index);
                index++;
            }
        }
        else {
            int num = Integer.parseInt(stringTokenizer.nextToken());
            int index = 1;
            changeNum(num);
            while(num - index > 0 && num + index < switches.length && switches[num - index].equals(switches[num+index])) {
                changeNum(num-index);
                changeNum(num+index);
                index++;
            }
        }
    }

    private static void output() throws IOException {
        for(int i = 1 ; i < switches.length; i++) {
            if(i % 20 == 0) {
                bufferedWriter.write(switches[i]+" \n");
            }
            else {
                bufferedWriter.write(switches[i]+" ");
            }
        }
    }
    public static void main(String[] args) throws IOException {
        setSwitchCount();
        setSwitches();
        setStudents();
        for(int i = 0 ; i < N; i++) {
            solve();
        }
        output();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
