package swea;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_Flatten_D3 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, min, max;
    static int[] boxes;

    static void dump(int i) throws IOException {
        Arrays.sort(boxes);
        System.out.println(Arrays.toString(boxes));
        max = boxes[boxes.length-1];
        min = boxes[0];
        if(N == 0 || max - min <= 1) {
            Arrays.sort(boxes);
            System.out.println(Arrays.toString(boxes));
            bufferedWriter.write("#"+(i+1)+" "+(max-min)+"\n");
        }
        else {
            boxes[0]++;
            boxes[boxes.length-1]--;
            N--;
            dump(i);
        }
    }

    static void input(int i) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        boxes = new int[100];
        for(int j = 0 ; j < boxes.length; j++) {
            boxes[j] = Integer.parseInt(stringTokenizer.nextToken());
        }
        dump(i);
    }



    public static void main(String[] args) throws IOException {
        for(int i = 0 ; i < 10; i++) {
            input(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
