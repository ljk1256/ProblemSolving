package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ3066 {
	
	private static int binarySearch(int[] memo, int number, int lastIdx) {
		int start = 0, end = lastIdx, mid = 0;
		
		while(start < end) {
			mid = (start + end) / 2;
			
			if(number < memo[mid]) end = mid;
			else start = mid+1;
		}
		
		return end;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] arr = new int [N];
			for(int j=0; j<N; j++) {
				arr[j] = Integer.parseInt(br.readLine());
			}
			
			int[] memo = new int [N];
			int len = 0, lastNum = Integer.MIN_VALUE;
			
			for(int j=0; j<N; j++) {
				if(arr[j] > lastNum) {
					lastNum = arr[j];
					memo[len++] = lastNum;
				}
				
				else if(arr[j] == lastNum) continue;
				
				else {
					int index = binarySearch(memo, arr[j], len-1);
					
					if(index == len-1) lastNum = arr[j];
					memo[index] = arr[j];
				}
			}
			
			sb.append(len).append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
