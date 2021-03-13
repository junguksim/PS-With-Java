
import java.util.Scanner;

public class GoodMorning {
    static int N, R = 2;
    static int[] nums;
    public static void main(String[] args) {
        System.out.print("입력할 갯수 : ");
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        nums = new int[N];
        for(int i = 0 ; i < N; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println("1. 조합의 결과");
        makeCombination(0,0,new int[R]);
        System.out.println("2. 순열의 결과");
        makePermutation(0, new int[N], new boolean[N]);
        System.out.println("3. 부분집합의 결과");
        makeSubset(0, new boolean[N]);
    }

    private static void makeCombination(int cnt, int start, int[] arr) {
        if(cnt == R) {
            for(int i = 0 ; i < R; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for(int i = start; i < N; i++) {
            arr[cnt] = nums[i];
            makeCombination(cnt+1, start+1, arr);
        }
    }

    private static void makePermutation(int cnt, int[] arr, boolean[] visited) {
        if(cnt == N) {
            for(int i = 0 ; i < N; i++) {
                if(visited[i]) System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for(int i = 0 ; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            arr[cnt] = nums[i];
            makePermutation(cnt + 1, arr, visited);
            visited[i] = false;
        }
    }

    private static void makeSubset(int cnt, boolean[] isSelected) {
        if(cnt == N) {
            for(int i = 0 ; i < N; i++) {
                if(isSelected[i]) System.out.print(nums[i] + " ");
            }
            System.out.println();
            return;
        }
        isSelected[cnt] = true;
        makeSubset(cnt+1, isSelected);
        isSelected[cnt] = false;
        makeSubset(cnt + 1, isSelected);
    }
}