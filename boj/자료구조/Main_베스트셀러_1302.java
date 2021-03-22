package boj.자료구조;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main_베스트셀러_1302 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static HashMap<String, Integer> map;
    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        map = new HashMap<>();
        for(int i = 0 ; i < N; i++) {
            String bookName = bufferedReader.readLine();
            Integer bookSoldCount = map.get(bookName);
            if(bookSoldCount == null) {
                map.put(bookName, 1);
            } else {
                map.put(bookName, bookSoldCount + 1);
            }
        }
    }

    private static void solve() throws IOException {
        Iterator<String> bookNames = map.keySet().iterator();
        int count = 0;
        String answer = "";
        while(bookNames.hasNext()) {
            String bookName = bookNames.next();
            Integer bookSoldCnt = map.get(bookName);
            if(count < bookSoldCnt) {
                answer = bookName;
                count = bookSoldCnt;
            } else if(count == bookSoldCnt) {
                answer = answer.compareTo(bookName) < 0 ? answer : bookName;
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
