package boj.BFS_DFS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_늑대와양_심정욱 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer RC = new StringTokenizer(bufferedReader.readLine());
        int R = Integer.parseInt(RC.nextToken());
        int C = Integer.parseInt(RC.nextToken());
        char[][] field = new char[R][C];
        for(int i = 0 ; i < R; i++) {
            char[] chars = bufferedReader.readLine().toCharArray();
            for(int j = 0; j < C; j++) {
                if(chars[j] == '.') {
                    chars[j] = 'D';
                }
                field[i][j] = chars[j];
            }
        }
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,1,-1};
        boolean over = false;
        for(int i = 0 ; i < R; i++) {
            for(int j = 0 ; j < C; j++) {
                int idx = 0;
                boolean flag = false;
                while(idx < 4) {
                    if(field[i][j] == 'S' || field[i][j] == 'W') {
                        flag = true;
                    }

                    if(j + dx[idx] >= 0 && j + dx[idx] < C && i + dy[idx] >= 0 && i + dy[idx] < R) {
                        if(field[i+dy[idx]][j+dx[idx]] == 'W') {
                            if(field[i][j] == 'S') {
                                over = true;
                                flag = true;
                            } else if (field[i][j] == 'D'){
                                flag = true;
                            }
                        }
                    }
                    idx++;
                }
                if(!flag) {
                    field[i][j] = '.';
                }
            }
        }

        if(over) {
            bufferedWriter.write("0\n");
        }
        else {
            bufferedWriter.write("1\n");
            for(int i = 0 ; i < R; i++) {
                for(int j = 0 ; j < C; j++) {
                    bufferedWriter.write(field[i][j]);
                }
                bufferedWriter.write("\n");
            }
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
