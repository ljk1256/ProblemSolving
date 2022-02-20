package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ1517 {
	
	static long answer;
	static int[] segmentTree;
	
	private static int prefixSum(int totalLeft, int totalRight, int start, int end, int nodeIdx) {
		if(start > totalRight || end < totalLeft) return 0;
		if(totalLeft <= start && end <= totalRight) return segmentTree[nodeIdx];
		int mid = start + (end - start) / 2;
		return prefixSum(totalLeft, totalRight, start, mid, nodeIdx*2) + prefixSum(totalLeft, totalRight, mid+1, end, nodeIdx*2+1);
	}
	
	private static void update(int start, int end, int nodeIdx, int tempIdx) {
		if(start == end) {
			segmentTree[tempIdx] = 1;
			return;
		}
		
		int mid = start + (end - start) / 2;
		if(nodeIdx <= mid) update(start, mid, nodeIdx, tempIdx*2);
		else update(mid+1, end, nodeIdx, tempIdx*2+1);
		
		segmentTree[tempIdx] = segmentTree[tempIdx*2] + segmentTree[tempIdx*2+1];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] Arr = new int [N];
		HashMap<Integer, Integer> pointSet = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			Arr[i] = Integer.parseInt(st.nextToken());
			pointSet.put(Arr[i], i+1);
		}
		
		Arrays.sort(Arr);
		segmentTree = new int [N*4];
		answer = 0L;
		
		for(int i=0; i<N; i++) {
			int index = pointSet.get(Arr[i]);
			answer += prefixSum(index+1, N, 1, N, 1);
			update(1, N, index, 1);
		}
		
		System.out.println(answer);
	}

}
