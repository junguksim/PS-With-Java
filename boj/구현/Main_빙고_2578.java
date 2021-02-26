package boj.구현;
import java.io.*;
import java.util.StringTokenizer;

public class Main_빙고_2578 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static int[][] deletes;
    static int count;
    static void input() throws IOException {
        map = new int[5][5];
        deletes = new int[5][5];
        count = 0;
        for(int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < 5; j++) {
                deletes[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void deleteNumber(int n) {
        for(int i = 0; i < 5; i++) {
            for(int j = 0 ; j < 5; j++) {
                if(map[i][j] == n) {
                    map[i][j] = -1;
                }
            }
        }
    }

    static void checkIsDeletedLine() {
        int deleteLineCount = 0;
        for(int i = 0; i < 5; i++) {
            int deleteCount = 0;
            for(int j = 0 ; j < 5; j++) {
                if(map[i][j] == -1) {
                    deleteCount++;
                }
            }
            if(deleteCount == 5) {
                deleteLineCount++;
            }
        }
        for(int i = 0; i < 5; i++) {
            int deleteCount = 0;
            for(int j = 0 ; j < 5; j++) {
                if(map[j][i] == -1) {
                    deleteCount++;
                }
            }
            if(deleteCount == 5) {
                deleteLineCount++;
            }
        }
        int deleteCount1 = 0;
        for(int i = 0 ; i < 5; i++) {
            if(map[i][i] == -1) {
                deleteCount1++;
            }
        }
        if(deleteCount1 == 5) {
            deleteLineCount++;
        }
        int deleteCount2 = 0;
        for(int i = 0 ; i < 5; i++) {
            if(map[4-i][i] == -1) {
                deleteCount2++;
            }
        }
        if(deleteCount2 == 5) {
            deleteLineCount++;
        }
        count += deleteLineCount - count;
    }
    static void solve() {
        for(int i = 0; i < 5; i++) {
            for(int j = 0 ; j < 5; j++) {
                deleteNumber(deletes[i][j]);
                checkIsDeletedLine();
                if(count >= 3) {
                    System.out.println(i * 5 + j + 1);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }

}
