package boj.BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_퍼즐_1525 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static final String standard = "123456780";
    static StringBuilder s = new StringBuilder();
    static HashMap<String, Integer> map = new HashMap<>();
    static Queue<String> queue = new LinkedList<>();

    static void input() throws IOException {
        for(int i = 0; i < 3; i++) {
            s.append(bufferedReader.readLine().replace(" ", ""));
        }
        if(s.toString().equals(standard)) {
            System.out.println(0);
            System.exit(0);
        } else {
            map.put(s.toString(), 0);
            queue.offer(s.toString());
        }
    }

    static int bfs() {
        while(!queue.isEmpty()) {
            String str = queue.poll();
            int zeroIdx = str.indexOf("0");
            int row = zeroIdx / 3;
            int col = zeroIdx % 3;

            for(int i = 0 ; i < 4; i++) {
                int nx = row + dx[i];
                int ny = col + dy[i];
                if(nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;
                int swapIdx = 3 * nx + ny;
                StringBuilder sb =new StringBuilder(str);
                char ch = sb.charAt(swapIdx);
                sb.setCharAt(swapIdx, '0');
                sb.setCharAt(zeroIdx, ch);

                if(sb.toString().equals(standard)) return map.get(str) + 1;

                if(!map.containsKey(sb.toString())) {
                    queue.offer(sb.toString());
                    map.put(sb.toString(), map.get(str) + 1);
                }
            }
        }
        return -1;
    }

    static void solve() {
        System.out.println(bfs());
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
