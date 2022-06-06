package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BJ2012 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> rankList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			rankList.add(Integer.parseInt(br.readLine()));
		}
		
		Collections.sort(rankList);
		
		long answer = 0L;
		
		for(int i=0; i<N; i++) {
			answer += Math.abs(rankList.get(i) - (i+1));
		}
		
		System.out.println(answer);
	}

}
