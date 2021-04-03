package boj.그래프;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_거짓말_1043 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int peopleCount , partyCount, knowsTruthCount;
    private static Party[] parties;
    private static ArrayList<Integer> knowsTruthPeople;
    private static int[] parents;

    private static class Party {
        int partyPeopleCount;
        int[] partyPeopleList;

        public Party(int partyPeopleCount, int[] partyPeopleList) {
            this.partyPeopleCount = partyPeopleCount;
            this.partyPeopleList = partyPeopleList;
        }
    }

    private static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return;

        parents[bRoot] = aRoot;
    }

    private static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        peopleCount = Integer.parseInt(nm.nextToken());
        parents = new int[peopleCount+1];
        for(int i = 1; i <= peopleCount; i++) {
            parents[i] = i;
        }
        partyCount = Integer.parseInt(nm.nextToken());
        parties = new Party[partyCount];
        StringTokenizer knowsTruthCountLine = new StringTokenizer(bufferedReader.readLine());
        knowsTruthCount = Integer.parseInt(knowsTruthCountLine.nextToken());
        knowsTruthPeople = new ArrayList<>();
        for(int i = 0 ; i < knowsTruthCount; i++) {
            knowsTruthPeople.add(Integer.parseInt(knowsTruthCountLine.nextToken())); // contains 쓰려고 arraylist 썼다.
        }
        for(int i = 0; i < partyCount; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            int partyPeopleCount = Integer.parseInt(line.nextToken());
            parties[i] = new Party(partyPeopleCount, new int[partyPeopleCount]);
            for(int j = 0; j < partyPeopleCount; j++) {
                parties[i].partyPeopleList[j] = Integer.parseInt(line.nextToken());
            }
        }
        for(int i = 0; i < partyCount; i++) {
            int partyPeopleCount = parties[i].partyPeopleCount;
            int[] partyPeopleList = parties[i].partyPeopleList;
            Arrays.sort(partyPeopleList);
            for(int j = 0 ; j < partyPeopleCount - 1; j++) {
                for(int k = j+1; k < partyPeopleCount; k++) {
                    union(partyPeopleList[j], partyPeopleList[k]);
                }
            }
        }
    }

    private static void solve() throws IOException {
        if(knowsTruthCount == 0) {
            System.out.println(partyCount);
            return;
        }
        int ans = 0;
        for(int i = 0; i < partyCount; i++) {
            int partyPeopleCount = parties[i].partyPeopleCount;
            int[] partyPeopleList = parties[i].partyPeopleList;
            boolean canLie = true;
            for(int j = 0 ; j < partyPeopleCount; j++) {
                if(knowsTruthPeople.contains(partyPeopleList[j])) {
                    canLie = false;
                    break;
                }
                for(int knowsTruthPersonNumber : knowsTruthPeople) {
                    if(find(knowsTruthPersonNumber) == find(partyPeopleList[j])) {
                        canLie = false;
                        break;
                    }
                }
            }
            if(canLie) {
                //System.out.println(i + "번 파티에서 구라 가능");
                ans++;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
