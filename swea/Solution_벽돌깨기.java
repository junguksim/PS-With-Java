package swea;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_벽돌깨기 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, W, H, answer;
    private static int[][] map;
    private static boolean[][] willBeDeleted;
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 1; i <= T ; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }

    private static void input() throws IOException {
        StringTokenizer nwh = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nwh.nextToken());
        W = Integer.parseInt(nwh.nextToken());
        H = Integer.parseInt(nwh.nextToken());
        map = new int[H][W];
        answer = Integer.MAX_VALUE;
        for(int i = 0; i < H; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < W; j++) {
                map[i][j] = Integer.parseInt(line.nextToken());
            }
        }
    }

    private static void solve(int t) throws IOException {
        makeCombination(0, new int[N]);
        bufferedWriter.write("#"+t+" "+answer+"\n");
    }

    private static void makeCombination(int cnt, int[] shootingLane) {
        if(cnt == N) {
            int[][] mapCopy = copyMap();
            shoot(mapCopy, shootingLane);
            getMinBrickCount(mapCopy);
            return;
        }
        for(int i = 0; i < W; i++) {
            shootingLane[cnt] = i;
            makeCombination(cnt+1, shootingLane);
        }
    }

    private static int[][] copyMap() {
        int[][] mapCopy = new int[H][W];
        for(int i = 0 ; i < H; i++) {
            mapCopy[i] = map[i].clone();
        }
        return mapCopy;
    }

    private static void delete(int[][] mapCopy) {
        for(int i = 0 ; i < H; i++) {
            for(int j = 0 ; j < W; j++) {
                if(willBeDeleted[i][j]) mapCopy[i][j] = 0;
            }
        }
    }
    private static void shoot(int[][] mapCopy, int[] shootingLane) {
        for(int lane : shootingLane) {
            for(int r = 0; r < H; r++) {
                if(mapCopy[r][lane] > 0) {
                    willBeDeleted = new boolean[H][W]; // 삭제될 칸을 저장해두는 배열
                    splash(mapCopy, r, lane); // 재귀로 연쇄폭발을 일으키는 메서드
                    delete(mapCopy);
                    shiftToDown(mapCopy);
                    break;
                }
            }
        }
    }


    private static void splash(int[][] map, int r, int c) {
        //System.out.println(r + " " + c + " 폭파 예정");
        int brickValue = map[r][c];
        willBeDeleted[r][c] = true;
        for(int i = c + 1; i < c + brickValue && i < W; i++) {
            //System.out.println(r + " " + i );
            if(!willBeDeleted[r][i]) {
                //System.out.println(r + " " + i + "부숴질 예정");
                willBeDeleted[r][i] = true;
                if(map[r][i] > 0) {
                    splash(map, r, i);
                }
            }
        }
        for(int i = c - 1; i > c - brickValue && i >= 0; i--) {
            if(!willBeDeleted[r][i]) {
                //System.out.println(r + " " + i + "부숴질 예정");
                willBeDeleted[r][i] = true;
                if(map[r][i] > 0) {
                    splash(map, r, i);
                }
            }
        }
        for(int i = r - 1; i > r - brickValue && i >= 0; i--) {
            if(!willBeDeleted[i][c]) {
                //System.out.println(i + " " + c + "부숴질 예정");
                willBeDeleted[i][c] = true;
                if(map[i][c] > 0) {
                    splash(map, i, c);
                }
            }
        }
        for(int i = r + 1; i < r + brickValue && i < H; i++) {
            if(!willBeDeleted[i][c]) {
                //System.out.println(i + " " + c + "부숴질 예정");
                willBeDeleted[i][c] = true;
                if(map[i][c] > 0) {
                    splash(map, i, c);
                }
            }
        }
    }

    private static void shiftToDown(int[][] map) {
        for(int j = 0 ; j < W; j++) {
            for(int i = H-1; i >= 1; i--) {
                int now = map[i][j];
                if(now == 0) {
                    for(int k = i - 1; k >= 0; k--) {
                        int next = map[k][j];
                        if(next > 0) {
                            map[i][j] = map[k][j];
                            map[k][j] = 0;
                            break;
                        }
                    }
                }
            }
        }

    }

    private static void getMinBrickCount(int[][] map) {
        int sum = 0;
        for(int i = 0 ; i < H; i++) {
            for(int j = 0 ; j < W; j++) {
                if(map[i][j] > 0) {
                    sum++;
                }
            }
        }
        answer = Math.min(answer, sum);
    }

}
