package codility;

public class Solution_PrefixSums_PassingCars {
    public int solution(int[] A) {
        int result = 0;
        int[] counts = new int[A.length];
        int[] zeros = new int[A.length];
        if(A[0] == 0) zeros[0] = 1;
        //0, 1, 1, 4, 5
        for(int i = 1 ; i < A.length; i++) {
            if(A[i] == 0) {
                zeros[i] = zeros[i-1] + 1;
                counts[i] = counts[i-1];
            } else {
                zeros[i] = zeros[i-1];
                counts[i] = counts[i-1] + zeros[i-1];
            }
        }
        result = counts[counts.length - 1];
        if(result > 1000000000 || result < 0) return -1;
        else return result;
    }
}