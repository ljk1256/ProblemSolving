package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ1725 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		long[] histograms = new long [N];
		
		for(int i=0; i<N; i++) {
			histograms[i] = Long.parseLong(br.readLine());
		}
		
		Stack<Integer> stack = new Stack<>();
		long maxArea = 0L;
		
		for(int i=0; i<N; i++) {
			while(!stack.isEmpty() && histograms[i] <= histograms[stack.peek()]) {
				long height = histograms[stack.pop()];
				int width = stack.isEmpty() ? i : i - 1 - stack.peek(); // 하나를 pop 했으니 해당 인덱스가 없으니 -1 해줘야함
				maxArea = Math.max(maxArea, width*height);
			}
			stack.push(i);
		}
		
		while(!stack.isEmpty()) { // 낮은 높이가 있어 스택에 남은 값이 있을수 있다 모두 뽑아준다
			long height = histograms[stack.pop()];
			int width = stack.isEmpty() ? N : N - 1 - stack.peek();
			maxArea = Math.max(maxArea, width*height);
		}
		
		System.out.println(maxArea);
	}

}
