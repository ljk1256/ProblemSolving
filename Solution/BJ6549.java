package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ6549 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long answer;
		
		while(n != 0) {
			answer = 0L;
			long[] Arr = new long [n];
			for(int i=0; i<n; i++) {
				Arr[i] = Long.parseLong(st.nextToken());
			}
			
			Stack<Integer> indexes = new Stack<>();
			
			for(int i=0; i<n; i++) {
				while(!indexes.isEmpty() && Arr[i] <= Arr[indexes.peek()]) { // 현재 높이보다 같거나 큰 것만 넓이를 구한다
					long height = Arr[indexes.pop()];
					long width = indexes.isEmpty() ? i : i - 1 - indexes.peek();
					
					answer = Math.max(answer, width*height);
				}
				indexes.push(i);
			}
			
			while(!indexes.isEmpty()) { // 스택에 남아있는 것이 있을 수 있기때문에 마지막까지 다 꺼내서 계산한다
				long height = Arr[indexes.pop()];
				long width = indexes.isEmpty() ? n : n - 1 - indexes.peek();
				
				answer = Math.max(answer, width*height);
			}
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			sb.append(answer).append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
