package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11399 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] guests = new int [N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			guests[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(guests);
		int prevSum = 0, totalTime = 0;
		
		for(int i=0; i<N; i++) {
			prevSum += guests[i];
			totalTime += prevSum;
		}
		
		System.out.println(totalTime);
	}

}
