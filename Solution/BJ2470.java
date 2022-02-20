package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2470 {
	
	static class Set {
		
		int num1;
		int num2;
		int abs;
		
		public Set(int num1, int num2, int abs) {
			super();
			this.num1 = num1;
			this.num2 = num2;
			this.abs = abs;
		}
		
	}

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
		
		int left = 0, right = arr.length-1;
		PriorityQueue<Set> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.abs, o2.abs));
		
		while(left < right) {
			int temp = arr[left] + arr[right];
			pq.offer(new Set(arr[left], arr[right], Math.abs(arr[left] + arr[right])));
			
			if(temp == 0) break;
			else if(temp < 0) left++;
			else right--;
		}
		
		StringBuilder sb = new StringBuilder();
		Set temp = pq.poll();
		sb.append(temp.num1).append(" ").append(temp.num2);
		System.out.println(sb.toString());
	}

}
