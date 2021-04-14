package boj.수학;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Main_골드바흐의추측_6588 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static ArrayList<Integer> primes;
    private static HashSet<Integer> possibleNumbers;
    private static boolean[] isFiltered;
    private static int[][] nums;

    private static void getPrimes() {
        for(int i = 2 ; i <= 1000000; i++) {
            if(isFiltered[i]) continue;
            int num = i;
//            for(int b : primes) {
//                int result = b + num;
//                if(Math.abs(nums[result][0] - nums[result][1]) < Math.abs(b - num)) {
//                    nums[result][0] = b;
//                    nums[result][1] = num;
//                }
//                //possibleNumbers.add(result);
//            }
            primes.add(num);
            int idx = 2;
            while (num * idx <= 1000000) {
                isFiltered[num * idx] = true;
                idx++;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        primes = new ArrayList<>();
        isFiltered = new boolean[1000001];
        nums = new int[1000001][2];
        possibleNumbers = new HashSet<>();
        System.out.println("시작");
        getPrimes();
        System.out.println(primes.toString());
        //System.out.println(possibleNumbers.toString());
        while (true) {
            int n = Integer.parseInt(bufferedReader.readLine());
            if(n == 0) break;
            System.out.println(nums[n][0] + " " + nums[n][1]);
        }
    }
}
