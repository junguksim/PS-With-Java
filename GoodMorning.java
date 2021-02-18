
import java.util.Scanner;

public class GoodMorning {


    static int N,R; //N:입력받을 데이터 수 R:선택할 데이터 수
    static int[] input,res; //input:입력데이터저장    res:결과데이터저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        input = new int[N];
        res = new int[R];

        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }
//      1. input배열에서 R개의 수를 뽑아서 만들 수 있는 순열을 모두 출력하시오.
        System.out.println("----- 순열 -----");
        makePermutation(0, new int[N], new boolean[N]);

//      2. input배열에서 R개의 수를 뽑아서 만들 수 있는 조합을 모두 출력하시오.
        System.out.println("----- 조합 -----");
        makeCombination(0,0, new int[N]);


//      3. input배열로 구성할 수 있는 모든 부분집합을 출력하시오.
        System.out.println("----- 부분집합 -----");
        makeSubset(0, new boolean[N]);

        sc.close();
    }//main
    private static void makeSubset(int cnt, boolean[] isSelected) {
        if(cnt == N) {
            for(int i = 0 ; i < N; i++) {
                if(isSelected[i]) {
                    System.out.print(input[i] + " ");
                }
            }
            System.out.println();
            return;
        }
        isSelected[cnt] = true;
        makeSubset(cnt+1, isSelected);
        isSelected[cnt] = false;
        makeSubset(cnt+1, isSelected);
    }

    private static void makeCombination(int cnt, int start ,int[] arr) {
        if(cnt == R) {
            for(int i = 0 ; i < R; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for(int i = start; i < N; i++) {
            arr[cnt] = input[i];
            makeCombination(cnt + 1, i + 1, arr);
        }
    }

    static void makePermutation(int cnt, int[] arr, boolean[] visited) {
        if(cnt == N) {
            for(int i = 0 ; i < N; i++) {
                if(visited[i]) {
                    System.out.print(arr[i]+ " ");
                }
            }
            System.out.println();
            return;
        }
        for(int i = 0; i < N; i++) {
            if(visited[cnt]) continue;
            arr[cnt] = input[i];
            visited[cnt] = true;
            makePermutation(cnt+1, arr, visited);
            visited[cnt]= false;
        }
    }

}