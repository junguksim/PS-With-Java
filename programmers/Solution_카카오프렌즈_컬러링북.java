package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [
 * [1, 1, 1, 0],
 * [1, 2, 2, 0],
 * [1, 0, 0, 1],
 * [0, 0, 0, 1],
 * [0, 0, 0, 3],
 * [0, 0, 0, 3]]
 */
public class Solution_카카오프렌즈_컬러링북 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static boolean[][] visited;

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int bfs(int m, int n, int[][] picture, Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);

        int areaColor = picture[start.x][start.y];
        int areaSize = 0;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int sx = node.x;
            int sy = node.y;
            for(int i = 0; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                    if(!visited[nx][ny] && picture[nx][ny] == areaColor) {
                        visited[nx][ny] = true;
                        queue.add(new Node(nx, ny));
                        areaSize++;
                    }
                }
            }
        }

        return areaSize;
    }


    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];

        for(int i = 0 ; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(picture[i][j] > 0 && !visited[i][j]) {
                    int size = bfs(m,n,picture, new Node(i,j));
                    numberOfArea++;
                    if(maxSizeOfOneArea <= size) {
                        maxSizeOfOneArea = size;
                    }
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(6, 4, new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}})));
    }
}
