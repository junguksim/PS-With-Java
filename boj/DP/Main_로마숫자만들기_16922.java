package boj.DP;
import java.io.*;
import java.util.*;

public class Main_로마숫자만들기_16922 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static TreeSet<Integer> result;
    private static int[] numbers;

    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        numbers = new int[]{1,5,10,50};
        result = new TreeSet<>();
        for(int j = 0 ; j < 4; j++) {
            result.add(numbers[j]);
        }
    }

    private static void solve() throws IOException {
        for(int i = 1; i < N; i++) {
            Iterator<Integer> iterator = result.iterator();
            ArrayList<Integer> willAdd = new ArrayList<>();
            while (iterator.hasNext()) {
                int next = iterator.next();
                for(int j = 0 ; j < 4; j++) {
                    willAdd.add(next + numbers[j]);
                }
            }
            result = new TreeSet<>();
            result.addAll(willAdd);
        }

        System.out.println(result.size());
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
