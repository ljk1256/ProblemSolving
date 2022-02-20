package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ2812 {
	
	// 순서가 있거나 현재 값과 비교해서 이전 값들을 없애거나 할때 스택의 자료구조를 활용한다
	// 완탐이 안될경우 그리디 >> 그 다음 dp bfs 방법으로 확장해서 나가본다
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		char[] input = br.readLine().toCharArray();
		
		Stack<Integer> stack = new Stack<>();
		
		int prev = -1;
		for(int i=0; i<N; i++) {
			if(K > 0) {
				int temp = input[i] - '0';
				if(temp > prev) {
					while(!stack.isEmpty() && K > 0 && input[stack.peek()] - '0' < temp) {
						stack.pop();
						K--;
					}
				}
				
				stack.push(i);
				prev = temp;
			}
			else stack.push(i);
		}
		
		if(K > 0) { // 만약 제거 횟수가 남아있다면
			while(K-- > 0) stack.pop();
		}
		
		boolean[] isRemove = new boolean [N];
		while(!stack.isEmpty()) isRemove[stack.pop()] = true;
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			if(isRemove[i]) sb.append(input[i]);
		}
		
		System.out.println(sb.toString());
	}

}
