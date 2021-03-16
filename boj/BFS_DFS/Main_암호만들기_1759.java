package boj.BFS_DFS;

import java.io.*;
import java.util.*;

public class Main_암호만들기_1759 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int L,C;
    static char[] chars;
    static List<Character> vowelTypes = Arrays.asList('a','e','i','o','u');

    static void input() throws IOException {
        StringTokenizer lc = new StringTokenizer(bufferedReader.readLine());
        L = Integer.parseInt(lc.nextToken());
        C = Integer.parseInt(lc.nextToken());
        chars = new char[C];
        StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0 ; i < C; i++) {
            chars[i] = line.nextToken().charAt(0);
        }
        Arrays.sort(chars);
    }

    static void checkString(char[] arr) throws IOException {
        int vowels = 0;
        int cons = 0;
        for(int i = 0 ; i < L; i++) {
            if(vowelTypes.contains(arr[i])) {
                vowels++;
            } else {
                cons++;
            }
        }
        if(vowels < 1 || cons < 2) {
            return;
        }
        Arrays.sort(arr);
        bufferedWriter.write(new String(arr) + " \n");
    }

    static void makeCombination(int cnt, int start, char[] arr) throws IOException {
        if(cnt == L) {

            checkString(arr);
            return;
        }
        for(int i = start; i < C; i++) {
            arr[cnt] = chars[i];
            makeCombination(cnt+1, i+1, arr);
        }
    }

    static void solve() throws IOException {
        makeCombination(0,0,new char[L]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
