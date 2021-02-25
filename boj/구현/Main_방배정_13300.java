package boj.구현;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_방배정_13300 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N,K, ans;
    static Student[] students;

    static class Student implements  Comparable<Student>{
        int sex;
        int grade;

        public Student(int sex, int grade) {
            this.sex = sex;
            this.grade = grade;
        }

        @Override
        public int compareTo(Student o) {
            return this.grade - o.grade;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "sex=" + sex +
                    ", grade=" + grade +
                    '}';
        }
    }

    static void input() throws IOException {
        StringTokenizer nk = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nk.nextToken());
        K = Integer.parseInt(nk.nextToken());
        students = new Student[N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            students[i] = new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(students);
        //System.out.println(Arrays.toString(students));
    }

    static void solve() {
        // 6 * 2 개의 방 종류배열 여자 1학년방 - 남자 1학년방 - ...
        int[][] counts = new int[6][2];
        for(int i = 0; i < N; i++) {
            counts[students[i].grade-1][students[i].sex]++;
        }
        for(int i = 0; i < 6; i++) {
            counts[i][0] = counts[i][0] / K +  (counts[i][0] % K == 0 ? 0 : 1);
            counts[i][1] = counts[i][1] / K +  (counts[i][1] % K == 0 ? 0 : 1);
            ans += counts[i][0] + counts[i][1];
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
