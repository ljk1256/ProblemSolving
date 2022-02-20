package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2437 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int [N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		if(arr[0] != 1) {
			System.out.println(1);
			return;
		}
		
		else {
			int tempsum = arr[0];
			for(int i=1; i<N; i++) { // 그 다음 수가 현재의 정렬된 수의 합보다 작을 경우 그 수 까지 조합이 가능하다
				if(tempsum+1 < arr[i]) break; // arr[i] 가 클수도 있다 +1 만큼 커서 연속된 수 일 경우 조합이 가능하기에 1보다 더 큰수가 오는 순간 조합이 불가능
				tempsum += arr[i];
			}
			
			System.out.println(tempsum+1);
		}
	}

}
