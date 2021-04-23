package swea;
import java.io.*;
import java.util.StringTokenizer;

public class Solution_특이한자석 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int K;
    private static final int MAGNET_COUNT = 4, MAGNETISM_COUNT = 8, CW = 1, CCW = -1, RIGHT = 2, LEFT = 6, TOP = 0, N = 0, S = 1;
    private static Magnet[] magnets;
    private static Rotate[] rotates;
    private static boolean[] alreadyRotated;

    private static class Magnet {
        int no;
        int[] magnetism;

        public Magnet(int no, int[] magnetism) {
            this.no = no;
            this.magnetism = magnetism;
        }
    }

    private static class Rotate {
        int rotateMagnetNo;
        int rotateDir;

        public Rotate(int rotateMagnetNo, int rotateDir) {
            this.rotateMagnetNo = rotateMagnetNo;
            this.rotateDir = rotateDir;
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }

    private static void input() throws IOException {
        K = Integer.parseInt(bufferedReader.readLine());
        magnets = new Magnet[MAGNET_COUNT + 1];
        rotates = new Rotate[K];
        alreadyRotated = new boolean[MAGNET_COUNT+1];
        for(int i = 1; i <= MAGNET_COUNT; i++) {
            StringTokenizer mag = new StringTokenizer(bufferedReader.readLine());
            int[] magnetism = new int[MAGNETISM_COUNT];
            for(int j = 0; j < MAGNETISM_COUNT; j++) {
                magnetism[j] = Integer.parseInt(mag.nextToken());
            }
            magnets[i] = new Magnet(i, magnetism);
        }
        for(int i = 0; i < K; i++) {
            StringTokenizer r = new StringTokenizer(bufferedReader.readLine());
            rotates[i] = new Rotate(Integer.parseInt(r.nextToken()), Integer.parseInt(r.nextToken()));
        }
    }

    private static void solve(int t) throws IOException {
        for(int i = 0; i < K; i++) {
            alreadyRotated = new boolean[MAGNET_COUNT+1]; // 수동 회전이 끝나면 회전 여부를 초기화
            Rotate r = rotates[i];
            rotate(r.rotateMagnetNo, r.rotateDir);
        }
        bufferedWriter.write("#"+t+" "+getPoint()+"\n");
    }

    private static void rotate(int rotateMagnetNo, int rotateDir) {
        if(alreadyRotated[rotateMagnetNo]) {
            return;
        }; // 이미 한번 회전했다면 멈춤.
        alreadyRotated[rotateMagnetNo] = true;
        checkRotateByMagnetism(rotateMagnetNo, rotateDir);
        Magnet rotateMagnet = magnets[rotateMagnetNo];
        int[] magnetism = rotateMagnet.magnetism;
        int[] newMagnetism = new int[MAGNETISM_COUNT];
        if(rotateDir == CW) {
            newMagnetism[0] = magnetism[MAGNETISM_COUNT - 1];
            for(int i = 1; i < MAGNETISM_COUNT; i++) {
                newMagnetism[i] = magnetism[i-1];
            }
        } else {
            newMagnetism[MAGNETISM_COUNT - 1] = magnetism[0];
            for(int i = 0; i < MAGNETISM_COUNT - 1; i++) {
                newMagnetism[i] = magnetism[i+1];
            }
        }
        magnets[rotateMagnetNo] = new Magnet(rotateMagnetNo, newMagnetism);
    }

    private static void checkRotateByMagnetism(int rotatedMagnetNo, int rotatedDir) {
        Magnet before, now, next;
        now = magnets[rotatedMagnetNo];
        if(rotatedMagnetNo > 1) {
            before = magnets[rotatedMagnetNo - 1];
            if(now.magnetism[LEFT] != before.magnetism[RIGHT]) {
                if(rotatedDir == CW) {
                    rotate(rotatedMagnetNo-1, CCW);
                } else {
                    rotate(rotatedMagnetNo-1, CW);
                }
            }
        }
        if(rotatedMagnetNo < MAGNET_COUNT) {
            next = magnets[rotatedMagnetNo+1];
            if(now.magnetism[RIGHT] != next.magnetism[LEFT]) {
                if(rotatedDir == CW) {
                    rotate(rotatedMagnetNo+1, CCW);
                } else {
                    rotate(rotatedMagnetNo+1, CW);
                }
            }
        }
    }

    private static int getPoint() {
        int points = 0;
        for(int m = 1; m <= MAGNET_COUNT; m++) {
            Magnet magnet = magnets[m];
            if(magnet.magnetism[TOP] == S) {
                points += Math.pow(2, m - 1);
            }
        }
        return points;
    }
}