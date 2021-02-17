
public class GoodMorning {

    static int[] num = {1, 2, 3, 4};
    static int N = 2;
    static boolean[] isSelected = new boolean[num.length];

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
        for(int i = 0; i < num.length; i++) {
            if(visited[cnt]) continue;
            arr[cnt] = num[i];
            visited[cnt] = true;
            makePermutation(cnt+1, arr, visited);
            visited[cnt]= false;
        }
    }

    static void makeCombination(int cnt, int[] arr, int start) {
        if(cnt == N) {
            for(int i = 0 ; i < N; i++) {
                System.out.print(arr[i]+ " ");
            }
            System.out.println();
            return;
        }
        for(int i = start; i < num.length; i++) {
            arr[cnt] = num[i];
            makeCombination(cnt+1, arr, i+1); // i + 1 인거에 주의하자. start+1 아님
        }
    }

    static void makeSubset(int cnt) {
        if(cnt == num.length) {
            for(int i = 0 ; i < num.length; i++) {
                if(isSelected[i]) System.out.print(num[i]+ " ");
            }
            System.out.println();
            return;
        }
        isSelected[cnt] = true;
        makeSubset(cnt+1);
        isSelected[cnt] = false;
        makeSubset(cnt+1);
    }

    public static void main(String[] args) {

//      1. num에서 N개를 뽑아서 만들 수 있는 순열을 모두 출력하시오.
        System.out.println("----- 순열 -----");
        makePermutation(0, new int[N], new boolean[num.length]);

//      2. num에서 N개를 뽑아서 만들 수 있는 조합을 모두 출력하시오.
        System.out.println("----- 조합 -----");
        makeCombination(0, new int[N], 0);

//      3. num으로 구성할 수 있는 모든 부분집합을 출력하시오.
        System.out.println("----- 부분집합 -----");
        makeSubset(0);
    }
}