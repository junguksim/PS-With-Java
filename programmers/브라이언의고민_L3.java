package programmers;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class 브라이언의고민_L3 {
    static class Solution {
        static boolean isOrigin(char c) {
            return (int)c - 97 < 0;
        }

        public String solution(String sentence) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
            // 대문자 원래문구, 소문자 기호
            String answer = "";
            Deque<Character> chars = new ArrayDeque<>();

            return answer;
        }
    }

    public static void main(String[] args) throws IOException {
        //System.out.println(new Solution().solution("HaEaLaLaObWORLDb"));
        System.out.println(new Solution().solution("SpIpGpOpNpGJqOqA"));
        //System.out.println(new Solution().solution("AxAxAxAoBoBoB"));
    }
}
