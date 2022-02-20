package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ20299 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int totalRating = Integer.parseInt(st.nextToken());
		int divRating = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		int[] tempRatings = new int [3];
		
	cp : while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int tempSum = 0;
			for(int i=0; i<3; i++) {
				int tempRating = Integer.parseInt(st.nextToken());
				
				if(tempRating < divRating) continue cp;
				
				tempRatings[i] = tempRating;
				tempSum += tempRating;
			}
			
			if(tempSum >= totalRating) {
				for(int i=0; i<3; i++) sb.append(tempRatings[i]).append(" ");
				cnt++;
			}
		}
		
		if(sb.length() > 0) sb.setLength(sb.length()-1);
		
		bw.write(String.valueOf(cnt));
		bw.write("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
