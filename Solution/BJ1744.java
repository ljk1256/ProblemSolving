package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BJ1744 {
	
	private static int binarySearch(int number, int[] arr) {
		int left = 0, right = arr.length-1, mid = 0;
		while(left < right) {
			mid = left + (right - left) / 2;
			if(number <= arr[mid]) right = mid;
			else left = mid+1;
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> minus = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
		PriorityQueue<Integer> plus = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
		
		int[] Arr = new int [N];
		for(int i=0; i<N; i++) {
			Arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(Arr);
		
		int index = binarySearch(0, Arr);
		int ans = 0;
		
		if(index == 0) {
			for(int i=0; i<Arr.length; i++) plus.offer(Arr[i]);
			
			while(plus.size() != 1 && !plus.isEmpty()) {
				int a = plus.poll();
				int b = plus.poll();
				if(a + b > a * b) ans += a + b;
				else ans += a * b;
			}
			while(!plus.isEmpty()) ans += plus.poll();
		}
		
		else {
			if(Arr[index] == 0) { // index 까지 넣고 사이즈가 2라면 0으로 퉁친다
				for(int i=0; i<=index; i++) minus.offer(Arr[i]);
				for(int i=index+1; i<Arr.length; i++) plus.offer(Arr[i]);
				
				if(minus.size() != 2) while(minus.size() != 1 && !minus.isEmpty()) ans += minus.poll() * minus.poll();
				while(plus.size() != 1 && !plus.isEmpty()) {
					int a = plus.poll();
					int b = plus.poll();
					if(a + b > a * b) ans += a + b;
					else ans += a * b;
				}
				
				while(!plus.isEmpty()) ans += plus.poll();
			}
			
			else {
				for(int i=0; i<index; i++) minus.offer(Arr[i]);
				for(int i=index; i<Arr.length; i++) plus.offer(Arr[i]);
				
				while(minus.size() != 1 && !minus.isEmpty()) ans += minus.poll() * minus.poll();
				while(plus.size() != 1 && !plus.isEmpty()) {
					int a = plus.poll();
					int b = plus.poll();
					if(a + b > a * b) ans += a + b;
					else ans += a * b;
				}
				
				while(!minus.isEmpty()) ans += minus.poll();
				while(!plus.isEmpty()) ans += plus.poll();
			}
			
		}
		
		System.out.println(ans);
	}

}
