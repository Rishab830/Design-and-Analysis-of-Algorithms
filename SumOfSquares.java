public class SumOfSquares {

    static int coinChange(int amt){
        int[] coins = new int[(int)Math.sqrt(amt)];
        for(int i=1;i<coins.length+1;i++){
            coins[i-1] = (int)Math.pow(i, 2);
        }
        int[] dp = new int[amt+1];
        for(int i=0;i<coins.length;i++){
            for(int j=1;j<=amt;j++){
                if(coins[i]<=j){
                    dp[j] = Math.min(dp[j-1]+1,dp[j-coins[i]]+1);
                }
                else{
                    dp[j] = dp[j] == 0? dp[j-1]+1: Math.min(dp[j-1]+1,dp[j]);
                }
            }
            for(int k: dp){
                System.out.print(k+" ");
            }
            System.out.println();
        }
        return dp[amt];
    }

    public static void main(String[] args) {
        coinChange(13);
    }
}
