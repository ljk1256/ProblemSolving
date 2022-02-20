package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ2212 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[] sensors = new int [N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			sensors[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(sensors); // 오름차순 정렬
		
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0; i<N-1; i++) {
			list.add(sensors[i+1]-sensors[i]);
		}
		
		int answer = 0;
		Collections.sort(list); // 오름차순 정렬
		
		for(int i=0; i<N-K; i++) { // k개의 기지국 이면 구간을 나누는건 k-1개
			answer += list.get(i);
		}
		
		System.out.println(answer);
	}

}
