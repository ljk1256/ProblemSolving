package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ6236 {
	
	static int[] spendMoney;
	static int spendCnt;
	static int withdrawCnt;
	
	private static boolean binarySearch(int cost) { // 이분탐색시 매개변수가 필요한지 필요하다면 어떤 것을 기준으로 잡고 상관관계를 만들지 부터 생각하자
		int tempCnt = 1; // 돈을 인출하고 시작해야 하니까
		int tempMoney = cost;
		
		for(int i=0; i<spendCnt; i++) {
			
			if(tempMoney < spendMoney[i]) {
				tempCnt++;
				tempMoney = cost;
				
				if(tempMoney < spendMoney[i]) return false; // 애초에 인출된 금액으로 사용할 수 없는 금액이라면 더 이상 볼필요 없다
			}
			tempMoney -= spendMoney[i];
		}
		
		if(tempCnt <= withdrawCnt) return true;
		else return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		spendCnt = Integer.parseInt(st.nextToken());
		withdrawCnt = Integer.parseInt(st.nextToken());
		
		spendMoney = new int [spendCnt];
		int minSpend = 0, maxSpend = 1000000000; // 만약 최소 인출은 단 한번의 인출만 있을 경우다 한번에 최대금액
		
		for(int i=0; i<spendCnt; i++) {
			spendMoney[i] = Integer.parseInt(br.readLine());
		}
		
		while(minSpend <= maxSpend) { // 배열의 인덱스 일 경우는 등호를 안넣지만 가격이나 어떤 수치적인 것들은 등호를 넣어주는 경우를 고려한다(인덱스 중복값은 등호 고려해줘야함)
			int mid = minSpend + (maxSpend - minSpend) / 2;
			
			if(binarySearch(mid)) maxSpend = mid - 1;
			else minSpend = mid + 1;
		}
		
		System.out.println(minSpend);
	}

}
