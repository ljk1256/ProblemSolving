package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ4344 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] score = new int [n];
			
			double avg = 0;
			for(int i=0; i<n; i++) {
				score[i] = Integer.parseInt(st.nextToken());
				avg += score[i];
			}
			
			avg = avg / n;
			double cnt = 0;
			for(int i=0; i<n; i++) {
				if(score[i] > avg) {
					cnt++;
				}
			}
			
			double r = (cnt / n) * 100;
			System.out.printf("%.3f%%\n", r);
		}
	}

}
