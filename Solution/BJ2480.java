package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2480 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int [7];
		
		for(int i=0; i<3; i++) {
			arr[Integer.parseInt(st.nextToken())]++;
		}
		
		int max = 0, idx = 0;
		
		for(int i=1; i<7; i++) {
			if(max <= arr[i]) {
				max = arr[i];
				idx = i;
			}
		}
		
		if(max == 1) {
			System.out.println(idx*100);
		}
		
		else if(max == 2) {
			System.out.println(idx*100 + 1000);
		}
		
		else System.out.println(idx*1000 + 10000);
	}

}
