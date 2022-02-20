package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ1208 {
	
	static int[] arr;
	static int S;
	
	private static void getSum(int start, int end, int sum, ArrayList<Integer> list) {
		if(start == end) {
			list.add(sum);
			return;
		}
		
		getSum(start+1, end, sum+arr[start], list); // 포함 한 것
		getSum(start+1, end, sum, list); // 해당 값을 포함 하지 않은것
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int [N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<Integer> leftList = new ArrayList<>();
		ArrayList<Integer> rightList = new ArrayList<>();
		int left = 0, right = 0, mid = N/2;
		
		getSum(left, mid, 0, leftList);
		getSum(mid, N, 0, rightList);
		
		Collections.sort(leftList);
		Collections.sort(rightList);
		
		long answer = 0; // 항상 답의 범위를 생각해야한다. 설계시 모든 경우를 볼 줄 알아야한다
		left = 0; 
		right = rightList.size()-1; // 기준은 반대여도 상관없음 어차피 모든 조합을 볼거고, 한쪽은 크고 다른쪽은 작은 곳에서 시작해야한다
		
		while(left < leftList.size() && right >= 0) {
			int leftVal = leftList.get(left);
			int rightVal = rightList.get(right);
			
			if(leftVal + rightVal == S) {
				long tempLeftCnt = 0;
				long tempRightCnt = 0;
				
				while(left < leftList.size() && leftList.get(left) == leftVal) {
					left++; // 중복된 걸 모두 체크 해줘야 다른 조합을 볼 수 있다
					tempLeftCnt++;
				}
				
				while(right >= 0 && rightList.get(right) == rightVal) { // 중복된 값 체크
					right--;
					tempRightCnt++;
				}
				
				answer += tempLeftCnt * tempRightCnt; // 경우의 수 
			}
			
			else if(leftVal + rightVal < S) left++;
			else right--;
		}
		
		if(S == 0) answer--;
		
		System.out.println(answer);
	}

}
