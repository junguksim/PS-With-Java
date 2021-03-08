package programmers;
import java.io.*;
import java.util.*;

public class 브라이언의고민_L3 {
    static class Solution {
        public String solution(String sentence) {
            List<Integer> pos;
            List<String> words = new ArrayList<>();

            char ch = ' ';
            while(sentence.length() > 0) {
                if(sentence.length() < 3) return "invalid"; // aA, Ac, 등 이 남았다면 invalid
                if(checkLetter(sentence, 0)) {
                    // 규칙 2
                    ch = sentence.charAt(0);
                    int endIdx = 0;
                    for(int i = 1; i < sentence.length(); i++) {
                        if(sentence.charAt(i) == ch) {
                            endIdx = i;
                            break;
                        }
                    }
                    if(endIdx == 0) return "invalid";
                    words.add(sentence.substring(0, endIdx + 1));
                    sentence = sentence.substring(endIdx + 1);
                } else {
                    // 규칙 1
                    if(!checkLetter(sentence, 1)) return "invalid";  // 첫 째 문자가 대문자인 경우인데 둘 째도 대문자라면 invalid
                    ch = sentence.charAt(1);
                    int endIdx = -1;
                    for(int i = 2; i < sentence.length(); i++) {
                        if(checkLetter(sentence, i)) {
                            endIdx = i;
                            if(sentence.charAt(endIdx) != ch) break;
                        }
                    }
                    if(endIdx == -1) return "invalid";

                    while(endIdx >= 1) {
                        if(ch == sentence.charAt(endIdx)) break;
                        endIdx--;
                    }

                    words.add(sentence.substring(0, endIdx + 2));
                    sentence = sentence.substring(endIdx + 2);
                }
            }
            String answer = "";
            for(String s : words) {
                answer += convertToString(s) + " ";
            }
            return answer.trim();
        }
        private static String convertToString(String s) {

            if(checkLetter(s, 0)) s = s.replace(s.charAt(0), ' ');
            if(checkLetter(s, 1)) s = s.replace(s.charAt(1), ' ');
            if(checkLetter(s, 2)) s = s.replace(s.charAt(2), ' ');
            s = s.replace(" ", "");

            return s;
        }

        private static boolean checkLetter(String str, int i) {
            return str.charAt(i) >= 'a' && str.charAt(i) <= 'z';
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution("HaEaLaLaObWORLDb"));
        System.out.println(new Solution().solution("SpIpGpOpNpGJqOqA"));
        //System.out.println(new Solution().solution("AxAxAxAoBoBoB"));
    }
}
