package boj.기하학;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_블록껍질_1708 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Point[] points;
    private static Point start;

    private static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        points = new Point[N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            points[i] = new Point(x,y);
        }

    }

    private static int ccw(Point a, Point b, Point c) {
        long ccwR = (a.x * b.y + b.x * c.y + c.x * a.y) - (b.x * a.y + c.x * b.y + a.x * c.y);
        if (ccwR > 0)
            return 1;
        if (ccwR < 0)
            return -1;
        return 0;
    }

    private static long getDist(Point a, Point b) {
        return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
    }

    private static void setStart() {
        start = new Point(40001, 40001);
        for(int i = 0 ; i < N; i++) {
            if(points[i].y < start.y) start = points[i];
            else if(points[i].y == start.y) {
                if(points[i].x == start.x) {
                    start = points[i];
                }
            }
        }
    }

    private static void sortByCCW() {
        Arrays.sort(points, (o1, o2) -> {
            int ccwR = ccw(start, o1, o2);
            if(ccwR > 0) return -1;
            else if(ccwR < 0) return 1;
            else if(ccwR == 0){
                long distWithO1 = getDist(start, o1);
                long distWithO2 = getDist(start, o2);
                if(distWithO1 > distWithO2) return 1;
            }
            return -1;
        }); // ccw 에 따라 정렬
    }
    private static void solve() throws IOException {
        setStart();
        sortByCCW();
        Stack<Point> stack = new Stack<>();
        stack.add(start);
        for(int i = 1; i < N; i++) {
            while (stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1) , points[i]) <= 0) {
                stack.pop();
            }
            stack.add(points[i]);
        }
        bufferedWriter.write(stack.size() + "\n");
    }
}