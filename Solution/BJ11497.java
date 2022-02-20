package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ11497 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Integer> list = new ArrayList<>(N);
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			Collections.sort(list, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			
			int[] Arr = new int [N];
			int mid = (Arr.length-1)/2, left = mid-1, right = mid+1;
			Arr[mid] = list.get(0);
			
			for(int j=1; j<N; j++) {
				if(j%2 == 1) Arr[right++] = list.get(j);
				else Arr[left--] = list.get(j);
			}
			
			int Ans = Integer.MIN_VALUE;
			for(int j=0; j<N; j++) {
				if(j == N-1) {
					Ans = Math.max(Ans, Math.abs(Arr[N-1] - Arr[0]));
				}	
				else {
					Ans = Math.max(Ans, Math.abs(Arr[j+1] - Arr[j]));
				}
			}
			
			sb.append(Ans).append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
