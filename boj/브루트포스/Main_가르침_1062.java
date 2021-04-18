package boj.브루트포스;
import java.io.*;
import java.util.StringTokenizer;

public class Main_가르침_1062 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int N,K, answer;
    private static String[] words;
    private static boolean[] pickedChars;
    private static char[] defaults = new char[]{'a','c','n','t','i'};

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void input() throws IOException {
        StringTokenizer nk = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nk.nextToken());
        K = Integer.parseInt(nk.nextToken());
        if(K < 5) { // 가르칠 수 있는 게 5개 미만이면 어차피 아무것도 못읽는다.
            System.out.println(0);
            System.exit(0);
        }
        K -= 5; // 디폴트로 뽑을 것이므로
        words = new String[N];
        for(int i = 0 ; i < N; i++) {
            String word = bufferedReader.readLine();
            words[i] = word;
        }
    }

    private static void solve() {
        makeCombination(0,0,new int[K]);
        System.out.println(answer);
    }

    private static void makeCombination(int start, int cnt, int[] picked) {
        if(cnt == K) {
            pickedChars = new boolean[26];
            setDefault();
            for(int i = 0 ; i < K; i++) {
                char c = (char)(picked[i] + 97);
                pickedChars[picked[i]] = true;
            }
            checkReadable();
            return;
        }
        for(int i = start; i < 26; i++) { // 0번부터 25번 까지(a부터 z까지)
            boolean alreadyPicked = false; // 미리 가르친 a,c,n,t,i 중에서 또 가르치면 낭비이므로,
            for(char aDefault : defaults) {
                if(aDefault-'a' == i) { // 만약 i 번이 이미 가르친 a,c,n,t,i 의 code값과 같다면
                    alreadyPicked = true; // 낭비임을 체크해준다.
                    break;
                }
            }
            if(alreadyPicked) { // 만약 낭비라면, 조합을 만들어줄 필요가 없다.
                continue;
            }
            picked[cnt] = i;
            makeCombination(i+1, cnt+1, picked);
        }
    }

    private static void setDefault() {
        for (char aDefault : defaults) {
            pickedChars[aDefault - 'a'] = true;
        }
        // a,c,n,t,i 를 미리 가르친다.
    }

    private static void checkReadable() {
        int sum = 0;
        for(int i = 0 ; i < N; i++) {
            boolean readable = true; // 해당 단어를 읽을 수 있는지
            for(int j = 0 ; j < words[i].length(); j++) {
                char c  = words[i].charAt(j); // 단어의 알파벳 하나하나 중에
                if(!pickedChars[(int)c - 97]) { // 안가르친게 있다면
                    readable = false; // 읽을 수 없음을 체크해준다.
                    break;
                }
            }
            if(readable) { // 읽을 수 있다면
                sum++; // 갯수를 더해주고
            }
        }
        answer = Math.max(answer, sum); // 최대값을 저장해준다.
    }
}