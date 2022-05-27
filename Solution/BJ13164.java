package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ13164 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int [N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i=0; i<N-1; i++) { // 구간 값을 구한다
			list.add(arr[i+1] - arr[i]);
		}
		
		Collections.sort(list); // 구간의 값이 작은 순서로 정렬 >> 구간 값을 선택 한다는건 해당 구간을 선택한다는 것과 같은 의미
		
		if(N-K <= 0) System.out.println(0); // 구간 값을 몇개를 선택하는 것이 몇개의 구간으로 나누어 지는지 생각
		else {
			int answer = 0;
			
			for(int i=0; i<N-K; i++) {
				answer += list.get(i);
			}
			
			System.out.println(answer);
		}
	}

}
