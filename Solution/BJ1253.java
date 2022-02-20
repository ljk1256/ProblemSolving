package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1253 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long [N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(arr);
		int answer = 0;
		
		for(int i=0; i<N; i++) {
			int left = 0, right = N-1;
			long target = arr[i];
			
			while(left < right) {
				long tempSum = arr[left] + arr[right];
				
				if(tempSum < target) left++;
				else if(tempSum > target) right--;
				else {
					if(left != i && right != i) {
						answer++;
						break; // 내가 선택한 두 수로 만들 수 있는수 역시 한개 이기에 다음 수 탐색을 해야한다
					}
					
					else if(left == i) left++;
					else if(right == i) right--;
				}
			}
		}
		
		System.out.println(answer);
	}

}
