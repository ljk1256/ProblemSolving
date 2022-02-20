package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1654 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		long[] lines = new long [K];
		
		long max = 0;
		for(int i=0; i<K; i++) {
			lines[i] = Long.parseLong(br.readLine());
			max = Math.max(max, lines[i]);
		}
		
		long left = 1;
		long right = max;
		
		long answer = 0;
		while(left <= right) { // 배열의 인덱스면 같으면 안되겠지만 이건 길이로 자르기때문에 해당 길이도 봐야한다
			long mid = (left + right)/2;
			int cnt = 0;
			
			for(long l : lines) cnt += l / mid;
			
			if(cnt < N) right = mid-1;
			else {
				left = mid+1;
				answer = Math.max(answer, mid);
			}
		}
		
		System.out.println(answer);
	}

}
