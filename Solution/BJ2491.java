package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ2491 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] data = new int [N];
		ArrayList<Integer> max_list = new ArrayList<>();
		ArrayList<Integer> min_list = new ArrayList<>();
		int min_count = 1, max_count = 1;
		int before;
		
		for(int i=0; i<N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		//증가 탐색
		before = data[0];
		max_list.add(1);
		for(int i=1; i<N; i++) {
			if(before <= data[i]) {
				before = data[i];
				max_count++;
			}
			else {
				before = data[i];
				max_count = 1;
			}
			max_list.add(max_count);
		}
		
		//감소 탐색
		before = data[0];
		min_list.add(1);
		for(int i=1; i<N; i++) {
			if(before >= data[i]) {
				before = data[i];
				min_count++;
			}
			else {
				before = data[i];
				min_count = 1;
			}
			min_list.add(min_count);
		}
		
		Collections.sort(max_list);
		Collections.sort(min_list); 
		
		System.out.println(Math.max(max_list.get(max_list.size()-1), min_list.get(min_list.size()-1)));
	}

}
