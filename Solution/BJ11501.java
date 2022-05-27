package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11501 {

	public static void main(String[] args) throws IOException { // 그리디는 거꾸로 탐색해보는 방법도 하나의 방법
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answerBuilder = new StringBuilder();
		
		int testcase = Integer.parseInt(br.readLine());
		
		for(int i=0; i<testcase; i++) {
			int day = Integer.parseInt(br.readLine());
			
			int[] cost = new int [day];
			
			int maxCost = Integer.MIN_VALUE;
			int tempCnt = 0;
			
			long invest = 0L;
			long benefit = 0L;
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<day; j++) {
				cost[j] = Integer.parseInt(st.nextToken());
			}
			
			for(int j=day-1; j>=0; j--) {
				if(maxCost < cost[j]) {
					benefit += (maxCost * (long)tempCnt) - invest;
					maxCost = cost[j];
					tempCnt = 0;
					invest = 0;
				}
				else {
					tempCnt++;
					invest += cost[j];
				}
				
				if(j == 0 && tempCnt != 0) {
					
					benefit += (maxCost * (long)tempCnt) - invest;
				}
			}
			
			answerBuilder.append(benefit).append("\n");
		}
		
		answerBuilder.setLength(answerBuilder.length()-1);
		System.out.println(answerBuilder.toString());
	}

}
