package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ14002 {
	
	static int[] arr;
	static int[] memo;
	
	static class Memo {
		
		int idx;
		int number;
		
		public Memo(int idx, int number) {
			super();
			this.idx = idx;
			this.number = number;
		}
		
	}
	
	private static int binarySearch(int number, int lastIdx) {
		int start = 0, end = lastIdx, mid = 0;
		
		while(start < end) {
			mid = (start + end) / 2;
			
			if(memo[mid] < number) start = mid + 1;
			else end = mid;
		}
		
		return end;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		arr = new int [N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int len = 0, lastNum = Integer.MIN_VALUE;
		memo = new int [N];
		Stack<Memo> stack = new Stack<>();
		
		for(int i=0; i<N; i++) {
			
			if(arr[i] > lastNum) {
				memo[len++] = arr[i];
				lastNum = arr[i];
				stack.push(new Memo(len-1, lastNum));
			}
			
			else if(arr[i] == lastNum) continue;
			
			else {
				int index = binarySearch(arr[i], len-1);
				
				if(index == len-1) {
					lastNum = arr[i];
					memo[index] = arr[i];
					stack.push(new Memo(index, arr[i]));
				}
				
				else {
					memo[index] = arr[i];
					stack.push(new Memo(index, arr[i]));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int idx = len-1;
		lastNum = memo[len-1];
		
		while(!stack.isEmpty()) {
			Memo temp = stack.pop();
			
			if(temp.idx == idx) memo[idx--] = temp.number;
		}
		
		sb.append(len).append("\n");
		for(int i=0; i<len; i++) {
			sb.append(memo[i]).append(" ");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
