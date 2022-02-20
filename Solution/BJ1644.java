package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ1644 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> list = new ArrayList<>();
		boolean[] isPrime = new boolean [N+1];
		int answer = 0;
		
		isPrime[0] = isPrime[1] = true;
		
		for(int i=2; i*i<=N; i++) {
			if(!isPrime[i]) {
				for(int j=i*i; j<= N; j+=i) isPrime[j] = true;
			}
		}
		
		for(int i=2; i<=N; i++) {
			if(!isPrime[i]) list.add(i);
		}
		
		int left = 0, right = left+1;
		
		while(left < right && right < list.size()) {
			long tempSum = 0L;
			
			for(int i=left; i<=right; i++) {
				tempSum += list.get(i);
			}
			
			if(tempSum == N) {
				answer++;
				left++;
			}
			
			else if(tempSum < N) right++;
			else left++;
		}
		
		if(!isPrime[N]) answer++;
		
		System.out.println(answer);
	}

}
