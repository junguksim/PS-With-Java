package boj.정렬;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_좌표정렬하기_11650 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Node[] nodes;
    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.x==o2.x ? o1.y - o2.y : o1.x - o2.x;
        }
    }

    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            nodes[i] = new Node(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
        }
        Arrays.sort(nodes, new NodeComparator());
        for (int i = 0; i < N; i++) {
            System.out.println(nodes[i].x + " " + nodes[i].y);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        bufferedReader.close();
    }
}
