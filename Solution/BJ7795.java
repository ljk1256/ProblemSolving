package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ7795 {
	
	static int[] B;
	static int bSize;
	
	private static int binarySearch(int number) {
		int left = 0, right = bSize-1;
		int count = 0;
		
		while(left < right) {
			int mid = left + (right - left) / 2;
			
			if(number < B[mid]) right = mid;
			else left = mid + 1;
		}
		
		for(int i=0; i<right+1; i++) {
			if(number > B[i]) count++;
		}

		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder builder = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int i=0; i<TC; i++) {
			st = new StringTokenizer(br.readLine());
			int aSize = Integer.parseInt(st.nextToken());
			bSize = Integer.parseInt(st.nextToken());
			
			int[] A = new int [aSize]; // A 입력
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<aSize; j++) {
				A[j] = Integer.parseInt(st.nextToken());
			}
			
			B = new int [bSize]; // B 입력
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<bSize; j++) {
				B[j] = Integer.parseInt(st.nextToken());
			}
			
			int answer = 0;
			
			Arrays.sort(A);
			Arrays.sort(B);
			
			for(int j=0; j<aSize; j++) {
				answer += binarySearch(A[j]);
			}
			
			builder.append(answer).append("\n");
		}
		
		builder.setLength(builder.length()-1);
		System.out.println(builder.toString());
	}

}
