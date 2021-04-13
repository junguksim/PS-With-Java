package swea;
import java.io.*;
import java.util.StringTokenizer;

public class Solution_활주로건설 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,X;
    private static int[][] map;
    private static int[] dx = {0,0,1,-1};
    private static int[] dy = {1,-1,0,0};
    private static final boolean ROW = true, COL = false;

    private static void input() throws IOException {
        StringTokenizer nx = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nx.nextToken());
        X = Integer.parseInt(nx.nextToken());
        map = new int[N][N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < N; j++) {
                map[i][j] = Integer.parseInt(line.nextToken());
            }
        }
    }
    
    private static boolean canConstruct(int idx, boolean isRow) {
        boolean[] visited = new boolean[N];
        int cnt = 0;
        int start = -1;
        if(isRow) {
            start = map[idx][0];
            for(int c = 0 ; c < N; c++) {
                int cur = map[idx][c];
                if(cur == start) {
                    cnt++;
                } else {
                    if(cur == start + 1) {
                        // 높아지는 경우
                        if(cnt >= X) {
                            //설치 가능
                            for(int i = c - X; i < c; i++) {
                                if(visited[i]) return false;
                                visited[i] = true;
                            }
                            cnt = 1;
                        } else {
                            return false;
                        }
                    } else if(cur == start - 1) {
                        // 낮아지는 경우
                        if(c + X - 1 < N) {
                            for(int i = c; i < c + X; i++) {
                                if(visited[i] || map[idx][i] != cur) return false;
                                visited[i] = true;
                            }
                            cnt = 0;
                            c = c + X - 1;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                    start = cur;
                }
            }
        } else {
            start = map[0][idx];
            for(int r = 0 ; r < N ; ++r) {
                int cur = map[r][idx];
                if(cur == start) {
                    cnt++;
                } else {
                    if(cur == start + 1) {
                        if(cnt >= X) {
                            for(int i = r - X ; i < r ; ++i) {
                                if(visited[i]) return false;
                                visited[i] = true;
                            }
                            cnt = 1;
                        } else {
                            return false;
                        }
                    } else if(cur == start - 1) {
                        if(r + X - 1 < N) {
                            for(int i = r ; i < r + X ; ++i) {
                                if(visited[i] || map[i][idx] != cur) return false;
                                visited[i] = true;
                            }
                            r = r + X - 1;
                            cnt = 0;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                    start = cur;
                }
            }
        }
        return true;
    }

    private static boolean isPossible(int idx, boolean isRow) {
        int start = -1;
        if(isRow) {
            start = map[idx][0];
            for(int c = 1; c < N; c++) {
                if(map[idx][c] != start) return false;
            }
        } else {
            start = map[0][idx];
            for(int r = 1; r < N; r++) {
                if(map[r][idx] != start) return false;
            }
        }
        return true;
    }

    private static void solve(int t) throws IOException {
        int answer = 0;
        for(int i = 0 ; i < N; i++) {
            if(isPossible(i, ROW)) {
                answer++;
            } else{
                if(canConstruct(i, ROW)) {
                    answer++;
                }
            }
        }
        for(int i = 0 ; i < N; i++) {
            if(isPossible(i, COL)) {
                answer++;
            } else{
                if( canConstruct(i, COL)) {
                    answer++;
                }
            }
        }
        bufferedWriter.write("#"+t+" "+answer+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 1; i <= T; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
