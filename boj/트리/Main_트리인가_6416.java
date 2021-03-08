package boj.트리;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Main_트리인가_6416 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<Integer> set;
        int[] cnt; // idx : 노드 번호 value : 들어오는 간선 의 갯수
        int tc = 0;
        int root;
        boolean hasTwoMoreIncomeVector;
        int v; // 간선의 갯수

        while(true) {
            set = new HashSet<>();
            cnt = new int[1001];
            root = 0;
            tc++;
            hasTwoMoreIncomeVector = false;
            v = 0;

            while(true) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                if (a == 0 && b == 0) break;
                if (a == -1 && b == -1) return;

                set.add(a);
                set.add(b);
                cnt[b]++;
                v++;
            }
            if(set.size()==0) {
                System.out.println("Case "+ tc +" is a tree."); // 아무 것도 없는 트리도 트리임.
                continue;
            }

            Iterator iter = set.iterator();
            while(iter.hasNext()) {
                int num = (int)iter.next();
                if(cnt[num]==0) root++; // 들어오는 간선의 갯수가 0인 경우 root
                if(cnt[num]>1) {
                    hasTwoMoreIncomeVector = true; // 들어오는 간선의 갯수가 2개 이상인 노드가 있는 경우
                    break;
                }
            }

            if(hasTwoMoreIncomeVector || root!=1 || set.size()-v!=1) // 들어오는 간선의 갯수가 2개 이상 or 루트 노드가 유일하지 않음 or 노드의 개수 - 간선의 개수 가 1이 아님
                System.out.println("Case "+ tc +" is not a tree.");

            else
                System.out.println("Case "+ tc +" is a tree.");
        }
    }
}
