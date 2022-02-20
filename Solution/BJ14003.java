package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ14003 {
	
	static int[] arr;
	static Stack<Point> stack;
	static int[] memo;
	
	static class Point {
		
		int idx;
		int number;
		
		public Point(int idx, int number) {
			super();
			this.idx = idx;
			this.number = number;
		}
		
	}
	
	private static int binarySearch(int number, int lastIdx) {
		int start = 0, end = lastIdx;
		int mid = 0;
		
		while(start < end) {
			mid = (start + end) / 2;
			
			if(memo[mid] < number) start = mid+1;
			else end = mid;
		}
		
		return end;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		arr = new int [N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		memo = new int [N];
		stack = new Stack<>();
		
		int len = 0, lastNum = Integer.MIN_VALUE; // len을 인덱스로 변경하려면 -1 해야한다
		for(int i=0; i<N; i++) {
			if(arr[i] > lastNum) {
				stack.push(new Point(len, arr[i]));
				memo[len++] = arr[i];
				lastNum = arr[i];
			}
			
			else if(arr[i] == lastNum) continue;
			
			else {
				int index = binarySearch(arr[i], len-1);
				
				if(index == len-1) {
					lastNum = arr[i];
					memo[len-1] = arr[i];
					stack.push(new Point(index, arr[i]));
				}
				else {
					memo[index] = arr[i];
					stack.push(new Point(index, arr[i]));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(len).append("\n");
		
		int lastIdx = len-1;
		
		while(!stack.isEmpty()) {
			Point temp = stack.pop();
			
			if(temp.idx == lastIdx) {
				lastIdx = temp.idx;
				memo[lastIdx] = temp.number;
				lastIdx--;
			}
			
		}
		
		for(int i=0; i<len; i++) {
			sb.append(memo[i]).append(" ");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
