package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_경주로건설 {
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};

    private static class Node {
        int x;
        int y;
        int d;
        int cost;

        public Node(int x, int y, int d, int cost) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cost = cost;
        }
    }

    public static int solution(int[][] board) {
        int answer = 987654321;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,0,-1, 0));
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            int sx = n.x;
            int sy = n.y;
            int sd = n.d;
            int sc = n.cost;
            if(sx == board.length - 1 && sy == board.length-1) {
                answer = Math.min(answer, sc);
                continue;
            }
            for(int i = 0 ; i < 4; i++) {
                int d = i;
                int nx = sx + dx[d];
                int ny = sy + dy[d];
                if(nx < 0 || ny < 0 || nx >= board.length || ny >= board.length) continue;
                if(board[nx][ny] == 1) continue;
                int nextCost = sc + 100;
                if(sd != -1 && sd != d) {
                    nextCost += 500;
                }
                if(board[nx][ny] == 0 || nextCost <= board[nx][ny]) {
                    board[nx][ny] = nextCost;
                    queue.add(new Node(nx, ny, i, nextCost));
                }
            }
        }
        return board[board.length-1][board.length-1];
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0}, {0,0,0,0,1,0,0,0}, {0,0,0,1,0,0,0,1}, {0,0,1,0,0,0,1,0}, {0,1,0,0,0,1,0,0}, {1,0,0,0,0,0,0,0}}));
        //System.out.println(solution(new int[][]{{0,0,0,1,0}, {0,0,0,1,0},{1,0,0,0,0},{1,0,1,1,0},{1,0,0,1,0}}));
        //System.out.println(solution(new int[][]{{0,0,0}, {0,0,0},{0,0,0}}));
    }
}
