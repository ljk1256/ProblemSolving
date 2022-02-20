package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ2467 {
	
	static class Pair {
		
		int idx1;
		int idx2;
		int sum;
		
		public Pair(int idx1, int idx2, int sum) {
			super();
			this.idx1 = idx1;
			this.idx2 = idx2;
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
		
		int left = 0, right = arr.length-1, prevSum = Integer.MAX_VALUE;
		ArrayList<Pair> list = new ArrayList<>();
		
		while(left < right) { // 전체 범위에서 좁혀 나가야한다
			int leftVal = arr[left];
			int rightVal = arr[right];
			int tempSum = Math.abs(leftVal + rightVal);
			
			if(tempSum < prevSum) {
				prevSum = tempSum;
				list.add(new Pair(left, right, tempSum));
			}
			
			if(leftVal + rightVal < 0) left++;
			else right--;
		}
		
		Collections.sort(list, new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.sum - o2.sum;
			}
		});
		
		Pair answer = list.get(0);
		StringBuilder sb = new StringBuilder();
		sb.append(arr[answer.idx1]).append(" ").append(arr[answer.idx2]);
		System.out.println(sb.toString());
	}

}
