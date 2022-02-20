package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2230 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] Arr = new int [N];
		for(int i=0; i<N; i++) {
			Arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(Arr);
		
		int minAns = Integer.MAX_VALUE, left = 0, right = 0;
		
		while(left < N && right < N) { // 양쪽에서 줄여갈 수 없다면 반대로 같은 곳에서 시작하는것도 투포인터
			int temp = Math.abs(Arr[right] - Arr[left]);
			if(temp < M) right++;
			else {
				left++;
				minAns = Math.min(minAns, temp);
			}
		}
		
		System.out.println(minAns);
	}

}
