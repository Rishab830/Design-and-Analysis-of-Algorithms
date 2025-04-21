// import java.util.*;

class LongestIncreasingSubsequenceDP{

    static Object[] lisDP(int[] arr){
        int[] traceback = new int[arr.length];
        int[] dp = new int[arr.length];
        dp[0] = 1;
        traceback[0] = -1;
        dp[arr.length - 1] = lisRecDP(arr, dp, traceback, arr.length - 1);
        return new Object[] {dp, traceback};
    }

    static int lisRecDP(int[] arr, int[] dp, int[] traceback, int i){
        if(dp[i] == 0){
            for(int j=0;j<i;j++){
                if(dp[j] == 0){
                    dp[j] = lisRecDP(arr, dp, traceback, j);
                }
                if(arr[j]<arr[i]){
                    if(dp[j]+1>dp[i]){
                        dp[i] = dp[j] + 1;
                        traceback[i] = j;
                    }
                }
                else{
                    dp[i] = Math.max(1, dp[i]);
                }
            }
        }
        return dp[i];
    }

    public static void main(String[] args) {
        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        Object[] output = lisDP(arr);
        int[] dp = (int[])output[0];
        int[] traceback = (int[])output[1];
        for(int i: dp){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i: traceback){
            System.out.print(i+" ");
        }
        System.out.println();
        int max = 0;
        int maxi = 0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>max){
                max = arr[i];
                maxi = i;
            }
        }
        int i = maxi;
        while(i!=-1){
            System.out.print(arr[i] + " ");
            i = traceback[i];
        }
    }
}