package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10025 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] zoos = new int [1000001];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int ice = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			zoos[x] = ice;
		}
		
		int maxIce = 0, tempIce = 0;
		int windowSize = K*2+1;
		
		for(int i=0; i<1000001; i++) {
			if(i >= windowSize) tempIce -= zoos[i-windowSize];
			
			tempIce += zoos[i];
			maxIce = Math.max(maxIce, tempIce);
		}
		
		System.out.println(maxIce);
	}

}
