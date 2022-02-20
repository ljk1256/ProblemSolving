package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ2473 {
	
	static class Set {
		
		int leftIdx;
		int midIdx;
		int rightIdx;
		long sum;
		
		public Set(int leftIdx, int midIdx, int rightIdx, long sum) {
			super();
			this.leftIdx = leftIdx;
			this.midIdx = midIdx;
			this.rightIdx = rightIdx;
			this.sum = sum;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int [N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int left = 0, right = arr.length-1; 
		long prevSum = Long.MAX_VALUE;
		ArrayList<Set> list = new ArrayList<>();
		
		for(int mid=1; mid<arr.length-1; mid++) {
			left = 0;
			right = arr.length-1; 
			
			while(left < mid && mid < right) { // 더 해서 오버플로우가 날것같으면, 더하기 전 숫자부터 큰 자료형으로 선언하는게 좋다
				long leftVal = arr[left];
				long midVal = arr[mid];
				long rightVal = arr[right];
				long tempSum = Math.abs(leftVal + midVal + rightVal);
				
				if(tempSum < prevSum) {
					prevSum = tempSum;
					list.add(new Set(left, mid, right, tempSum));
				}
				
				if(leftVal + midVal + rightVal < 0) left++;
				else right--;
			}
		}
		
		Collections.sort(list, new Comparator<Set>() {
			@Override
			public int compare(Set o1, Set o2) {
				return o1.sum < o2.sum ? -1 : 1;
			}
		});
		
		StringBuilder sb = new StringBuilder();
		Set answer = list.get(0);
		sb.append(arr[answer.leftIdx]).append(" ").append(arr[answer.midIdx]).append(" ").append(arr[answer.rightIdx]);
		System.out.println(sb.toString());
	}

}
