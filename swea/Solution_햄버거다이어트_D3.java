package swea;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_햄버거다이어트_D3 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,L, best;
    static Ingredient[] ingredients;

    static class Ingredient {
        int like;
        int calories;

        public Ingredient(int like, int calories) {
            this.like = like;
            this.calories = calories;
        }
    }

    static void generateSubset(int cnt, int totalLike, int totalCal) {
        if(totalCal > L)
            return;
        best = Math.max(best, totalLike);
        if(cnt == N) {
            return;
        }
        generateSubset(cnt+1, totalLike + ingredients[cnt].like, totalCal + ingredients[cnt].calories);
        generateSubset(cnt+1, totalLike, totalCal);
    }
    static void solve(int idx) throws IOException {
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        best = 0;
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        ingredients = new Ingredient[N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer ing = new StringTokenizer(bufferedReader.readLine());
            ingredients[i] = new Ingredient(Integer.parseInt(ing.nextToken()), Integer.parseInt(ing.nextToken()));
        }
        generateSubset(0, 0,0 );
        bufferedWriter.write("#" + idx + " " + best + "\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            solve(i+1);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
