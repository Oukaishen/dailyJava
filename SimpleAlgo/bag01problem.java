package algorithm;

public class bag01problem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// test case
		int n = 3;
		int w = 5;
		int [] weight = new int[]{1,2,3};
		int [] val  = new int[]{60,100,120};
		int [] ans = solution(weight,val, n,w);
		for(int i = 0; i< n ; i++)
			System.out.print(ans[i] +" ");
	}
	
	public static int[] solution(int[] weight, int[] val, int n, int w) {

	//step 1 find the maximun value
		//1.1 initialize dp array
		int[][] dp = new int[n+1][w+1];
		for(int i = 0; i<= n; i++){
			dp[i][0] = 0;
		}
		for(int i = 0; i<= w; i++){
			dp[0][i] = 0;
		}
		for(int i = 1; i<= n; i++){
			for(int j = 1; j <= w; j++){
				if(weight[i-1] > j){
					dp[i][j] = dp[i-1][j];
				}
				else{
					dp[i][j] = dp[i-1][j] > dp[i-1][j - weight[i-1]] + val[i-1] ? dp[i-1][j] : dp[i-1][j - weight[i-1]] + val[i-1];
				}
			}
		}
		//the maximum value is at dp[n][w]
		System.out.println(String.format("The maximun value is %s", dp[n][w]));
	// use back-prop to trace back the track
		// 0 means not choose and 1 mean choose
		int [] track  = new int[n];
		for(int i = 0; i <n; i++) track[i] = 0;
		
		for(int i = n, j = w; i >= 1 && j>=1; i--){
			// no choose
			if(dp[i][j] == dp[i-1][j])
				track[i-1] = 0;
			else
			{
				track[i-1] = 1;
				j = j - weight[i-1];
			}
		}

        return track;
    }
}
