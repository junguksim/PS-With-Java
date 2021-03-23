package programmers;

import java.util.*;

public class Solution_N으로표현 {
    public int solution(int N, int number) {
        if(N == number) {
            return 1;
        }
        TreeSet<Integer>[] possibleNumbers = new TreeSet[9];
        for(int i = 1; i <= 8; i++) {
            possibleNumbers[i] = new TreeSet<>();
        }
        possibleNumbers[1].add(N);
        for(int i = 2; i <= 8; i++) {
            String nn = "";
            for(int o = 0; o < i; o++) {
                nn += N;
            }
            possibleNumbers[i].add(Integer.parseInt(nn));
            if(Integer.parseInt(nn) == number) {
                return i;
            }
            for(int k = 1; k < i; k++) {
                Iterator<Integer> kIter = possibleNumbers[k].iterator();
                Iterator<Integer> pIter = possibleNumbers[i-k].iterator();
                while (kIter.hasNext()) {
                    Integer n1 = kIter.next();
                    HashSet<Integer> possibles = new HashSet<>();
                    while (pIter.hasNext()) {
                        Integer n2 = pIter.next();
                        possibles.add(n1 + n2);
                        possibles.add(n1 - n2);
                        possibles.add(n1 * n2);
                        if(n2 != 0) {
                            possibles.add(n1 / n2);
                        }
                    }
                    if(possibles.contains(number)) {
                        return i;
                    }
                    possibleNumbers[i].addAll(possibles);
                    pIter = possibleNumbers[i-k].iterator();
                }
            }
        }
        System.out.println(Arrays.toString(possibleNumbers));
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_N으로표현().solution(4, 17));
        //System.out.println(new Solution_N으로표현().solution(2, 11));
    }
}
