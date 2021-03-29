package boj.정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_숫자놀이_1755 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out)); // 입출력
    private static int N,M,size; // N, M 과 M-N+1 을 담는 size 변수 선언
    private static Number[] numbers; // Number class 의 배열인 numbers 선언

    private static class Number implements Comparable<Number>{
        int num; // Number 클래스의 필드 : 실제 숫자
        String pronunciation; // 필드 : 발음

        public Number(int num, String pronounciation) {
            super();
            this.num = num;
            this.pronunciation = pronounciation;
        } // 생성자

        @Override
        public int compareTo(Number o) {
            for(int i = 0; i < Math.min(this.pronunciation.length(), o.pronunciation.length()) ; i++) {
                // Number 타입의 this, o 중 길이가 작은 것까지 배열을 돌면서
                if(this.pronunciation.charAt(i) < o.pronunciation.charAt(i)) {
                    // 비교군인 o 의 ascii code 값이 크다면
                    return -1;
                } else if(this.pronunciation.charAt(i) > o.pronunciation.charAt(i)) {
                    //비교군인 o 의 ascii code 값이 작다면
                    return 1;
                }
            }
            return 0; // 위에서 해결하지 못했을 경우, 입력받은 그대로
        }

        @Override
        public String toString() {
            return "Number [num=" + num + ", pronounciation=" + pronunciation + "]"; // 디버깅을 위한 toString 메서드 오버라이드
        }


    }


    private static void input() throws Exception {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken()); // N, M의 입력 받기
        int from = Math.min(N, M); // N,M중 작은 값을 담는 from 변수
        int to = Math.max(N, M); // N,M중 큰 값을 담는 to 변수
        size = to - from + 1; // 큰 변수 - 작은 변수 + 1 이 배열의 size 이다.
        numbers = new Number[size]; // size 만큼의 Number 배열 선언
        for(int i = 0 ; i < size; i++) {
            numbers[i] = new Number(i + from, setPronounciation(i + from)); // 인덱스 + from 이 Number의 num 필드값이고, setPronounciation 의 결과값 string 을 pronounciation 필드에 담아준다.
        }
        Arrays.sort(numbers); // Comparable 인터페이스의 compareTo 메서드를 구현한 것에 따라 정렬해준다.
    }

    private static String setPronounciation(int num) {
        String n = String.valueOf(num); // int 로 들어온 num 을 String 으로 변환한다.
        StringBuilder result = new StringBuilder(); // 발음으로 바꿔주는 것의 결과를 담는다.
        for(int i = 0; i < n.length(); i++) {
            char c = n.charAt(i); // 각각의 숫자들마다 발음을 담기 위해, char 를 가져온다.
            switch (c) {
                case '1':
                    result.append("one ");
                    break;
                case '2':
                    result.append("two ");
                    break;
                case '3':
                    result.append("three ");
                    break;
                case '4':
                    result.append("four ");
                    break;
                case '5':
                    result.append("five ");
                    break;
                case '6':
                    result.append("six ");
                    break;
                case '7':
                    result.append("seven ");
                    break;
                case '8':
                    result.append("eight ");
                    break;
                case '9':
                    result.append("nine ");
                    break;
                case '0':
                    result.append("zero ");
                    break;
            }
        } // 각각의 숫자에 맞는 발음을 result 에 추가한다.
        return result.toString(); // result 를 toString 메서드로 변환해 반환한다.
    }

    private static void solve() throws Exception {
        for(int i = 0 ; i < size; i++) { // numbers 를 돌면서
            if((i + 1) % 10 == 0) { // 10번째일 경우 줄변환을,
                bufferedWriter.write(numbers[i].num + "\n");
            } else { // 아닐 경우 공백을 추가해 bufferedWriter 에 써준다.
                bufferedWriter.write(numbers[i].num + " ");
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
