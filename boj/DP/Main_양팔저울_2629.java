package boj.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_양팔저울_2629 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int weightsCount, ballsCount, weightsSum; // 추와 구슬들의 갯수를 나타내는 int 형 변수
    private static int[] weights, balls; // 추와 구슬들의 무게를 나타내는 배열
    private static TreeSet<Possible> possibles;
    private static boolean[] possibleWeights;

    private static class Possible {
        int no;
        int weight;
        int usedCount;

        public Possible(int no, int weight, int usedCount) {
            super();
            this.no = no;
            this.weight = weight;
            this.usedCount = usedCount;
        }

        @Override
        public String toString() {
            return "Possible [weight=" + weight + ", usedCount=" + usedCount + "]";
        }

    }

    private static void input() throws Exception {
        weightsCount = Integer.parseInt(bufferedReader.readLine());
        weights = new int[weightsCount];
        weightsSum = 0;
        StringTokenizer weightsInput = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0 ; i < weightsCount; i++) {
            weights[i] = Integer.parseInt(weightsInput.nextToken());
            weightsSum += weights[i];
        }
        possibles = new TreeSet<>((o1, o2) -> o1.weight - o2.weight);
        possibleWeights = new boolean[weightsSum+1];
        int possibleCount = 0;
        for(int i = 0; i < weightsCount ; i++) {
            Possible possible = new Possible(possibleCount++, weights[i], 1);
            possibles.add(possible);
            possibleWeights[possible.weight] = true;
            ArrayList<Possible> willAddPossible = new ArrayList<>();
            Iterator<Possible> possibleIter = possibles.iterator();
            while(possibleIter.hasNext()) {
                Possible comparePossible = possibleIter.next();
                if(possible.equals(comparePossible)) continue;
                int compareWeight = comparePossible.weight;
                int compareCount = comparePossible.usedCount;
                if(compareCount + 1 <= weightsCount){
                    if(compareWeight + possible.weight <= weightsSum) {
                        possibleWeights[compareWeight + possible.weight] = true;
                        willAddPossible.add(new Possible(possibleCount++,compareWeight + possible.weight, compareCount + 1));
                    }
                    if(compareWeight - possible.weight >= 1 && compareWeight - possible.weight <= weightsSum) {
                        possibleWeights[compareWeight - possible.weight] = true;
                        willAddPossible.add(new Possible(possibleCount++,compareWeight - possible.weight, compareCount + 1));
                    }
                    if(possible.weight - compareWeight >= 1 && possible.weight - compareWeight <= weightsSum) {
                        possibleWeights[possible.weight - compareWeight] = true;
                        willAddPossible.add(new Possible(possibleCount++,possible.weight - compareWeight, compareCount + 1));
                    }
                }
            }
            possibles.addAll(willAddPossible);
        }
        ballsCount = Integer.parseInt(bufferedReader.readLine());
        balls = new int[ballsCount];
        StringTokenizer ballsInput = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0 ; i < ballsCount; i++) {
            balls[i] = Integer.parseInt(ballsInput.nextToken());
        }
    }

    private static void solve() throws Exception {
        for(int i = 0 ; i < ballsCount; i++) {
            if(balls[i] > weightsSum) {
                bufferedWriter.write("N ");
                continue;
            }
            if(possibleWeights[balls[i]]) {
                bufferedWriter.write("Y ");
            } else {
                bufferedWriter.write("N ");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
