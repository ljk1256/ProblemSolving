package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14501 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		CS[] arr = new CS [N+1];
		int[] dp = new int [N+2]; // 인덱스 일에 잡혀있는 상담을 무조건 했을때 최대 수입을 계산
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			arr[i] = new CS(day, cost);
		}
		
		for(int i=N; i>0; i--) { // 거꾸러 계산하면서 나간다.
			if(arr[i].day == 1) { // 상담이 하루만에 끝낼수 있다면 비용을 더한다.
				dp[i] = dp[i+1] + arr[i].cost;
			}
			else if(arr[i].day + i <= N + 1) { // 하루 이상이라면, 그 전에 더했던 상담과 현재 가능한 상담과 비용 비교
				dp[i] = Math.max(dp[i+1], dp[i + arr[i].day] + arr[i].cost);
			}
			else { // 상담을 아예 진행 할 수 없다면, 값을 그대로 가져온다.
				dp[i] = dp[i+1];
			}
		}
		
		System.out.println(dp[1]);
	}

}

class CS {
	
	int day;
	int cost;
	
	public CS(int day, int cost) {
		super();
		this.day = day;
		this.cost = cost;
	}
	
}
