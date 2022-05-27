package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ10816 {
	
	static ArrayList<Integer> list;
	
	private static int lowerBound(int queryNum) {
		int left = 0;
		int right = list.size(); // 범위에 대하여 생각을 잘해야한다
		
		while(left < right) {
			int mid = left + (right - left) / 2;
			
			if(list.get(mid) < queryNum) left = mid+1;
			else right = mid;
		}
		
		return right;
	}
	
	private static int upperBound(int queryNum) {
		int left = 0;
		int right = list.size();
		
		while(left < right) {
			int mid = left + (right - left) / 2;
			
			if(list.get(mid) <= queryNum) left = mid+1;
			else right = mid;
		}
		
		return left;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		int M = Integer.parseInt(br.readLine());
		int[] input = new int [M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		//Arrays.sort(arr);
		Collections.sort(list);
		StringBuilder answerBuilder = new StringBuilder(); // 정답 출력
		
		for(int i=0; i<M; i++) {
			int query = input[i];
			
			int lowerIdx = lowerBound(query);
			int upperIdx = upperBound(query);
			
			answerBuilder.append(upperIdx - lowerIdx).append(" ");
		}
		
		answerBuilder.setLength(answerBuilder.length()-1);
		System.out.println(answerBuilder.toString());
	}

}
