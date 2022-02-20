package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1637 {
	
	static int N;
	static long[] A;
	static long[] B;
	static long[] C;
	
	private static long sectionSum(long mid) {
		long sum = 0L;
		for(int i=0; i<N; i++) {
			if(mid >= A[i]) { // 작은 값을 해줘야 포함되지 않는 수가 없음
				sum += (Math.min(mid, C[i]) - A[i]) / B[i] + 1; // 마지막 1은 첫번째 수를 포함하기 위함
			} // 최소 ~ 구간최대 사이 길이를 공차로 나눠 주면 해당 구간에 수의 개수가 나온다
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		A = new long [N];
		B = new long [N];
		C = new long [N];
		
		long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Long.parseLong(st.nextToken());
			C[i] = Long.parseLong(st.nextToken());
			B[i] = Long.parseLong(st.nextToken());
			
			if(A[i] < min) min = A[i];
			if(C[i] > max) max = C[i];
		}
		
		long left = min;
		long right = max;
		long ans = 0L;
		while(left <= right) { // 닫힌구간에서 확인은 이런식으로 포함 해준다 
			long mid = left + (right - left) / 2;
			long leftCnt = sectionSum(mid);
			
			if(leftCnt % 2 == 0) left = mid+1; // 왼쪽 부분에 짝수개 라면 오른쪽에 홀수개가 존재 할 수 있다
			else {
				ans = mid;
				right = mid-1;
			}
		}
		
		if(ans == 0L) System.out.println("NOTHING");
		else {
			StringBuilder sb = new StringBuilder();
			long oddCnt = sectionSum(left) - sectionSum(left-1);
			sb.append(ans).append(" ").append(oddCnt);
			System.out.println(sb.toString());
		}
	}

}
