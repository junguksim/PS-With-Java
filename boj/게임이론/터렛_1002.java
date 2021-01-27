package boj.게임이론;

import java.io.*;
import java.util.StringTokenizer;

public class 터렛_1002 {
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void getInput() throws IOException {
        String[] numbers = bufferedReader.readLine().split(" ");
        int[] numbersArray = new int[numbers.length];
        for(int i = 0 ; i < numbers.length; i++) {
            numbersArray[i] = Integer.parseInt(numbers[i]);
        }
        int distance = (int) (Math.pow((numbersArray[3] - numbersArray[0]), 2) + Math.pow((numbersArray[4] - numbersArray[1]), 2));
        int rSum = (int) Math.pow((numbersArray[2] + numbersArray[5]), 2);
        int rMinus = (int) Math.pow((numbersArray[2] - numbersArray[5]), 2);
        if(numbersArray[0] == numbersArray[3] && numbersArray[1] == numbersArray[4] && numbersArray[2] == numbersArray[5]) {
            bufferedWriter.write("-1\n");
        } else if (distance > rSum || distance < rMinus) {
            bufferedWriter.write("0\n");
        } else if (distance == rSum || rMinus == distance){
            bufferedWriter.write("1\n");
        } else {
            bufferedWriter.write("2\n");
        }
    }
    public static void main(String[] args) throws IOException {
        int T =Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            getInput();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
