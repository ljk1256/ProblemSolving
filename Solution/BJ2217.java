package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BJ2217 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Integer[] Arr = new Integer [N];
		
		for(int i=0; i<N; i++) {
			Arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(Arr, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		int cnt = 0, prevMax = 0;
		for(int i=0; i<N; i++) {
			cnt++;
			if(prevMax < Arr[i]*cnt) prevMax = Arr[i]*cnt;
		}
		
		System.out.println(prevMax);
	}

}
