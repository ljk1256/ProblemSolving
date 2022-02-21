package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ17298 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int [N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> stack = new Stack<>();
		int[] ansArr = new int [N];
		
		for(int i=N-1; i>=0; i--) {
			int temp = arr[i];
			boolean flag = false;
			
			while(!stack.isEmpty()) {
				int prev = stack.pop();
				
				if(temp < prev) {
					stack.push(prev);
					stack.push(temp);
					ansArr[i] = prev;
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				ansArr[i] = -1;
				stack.push(temp);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(ansArr[i]).append(" ");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
