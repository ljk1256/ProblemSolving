package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2357 {
	
	static int[] arr;
	static int[] minSegment;
	static int[] maxSegment;
	
	private static int makeMinSegment(int start, int end, int treeIdx) {
		if(start == end) {
			minSegment[treeIdx] = arr[start];
		}
		else {
			int mid = start + (end - start) / 2; // 이렇게 해주는 이유는 나중에 크기가 커질시 오버플로우를 방지하기 위함!!!
			minSegment[treeIdx] = Math.min(makeMinSegment(start, mid, treeIdx*2), makeMinSegment(mid+1, end, treeIdx*2+1));
		}
		return minSegment[treeIdx];
	}
	
	private static int makeMaxSegment(int start, int end, int treeIdx) {
		if(start == end) {
			maxSegment[treeIdx] = arr[start];
		}
		else {
			int mid = start + (end - start) / 2; // 이렇게 해주는 이유는 나중에 크기가 커질시 오버플로우를 방지하기 위함!!!
			maxSegment[treeIdx] = Math.max(makeMaxSegment(start, mid, treeIdx*2), makeMaxSegment(mid+1, end, treeIdx*2+1));
		}
		return maxSegment[treeIdx];
	}
	
	private static int getMin(int start, int end, int treeLidx, int treeRidx, int tempIdx) {
		if(start > treeRidx || end < treeLidx) return Integer.MAX_VALUE;
		
		if(treeLidx <= start && end <= treeRidx) return minSegment[tempIdx];
		
		int mid = start + (end - start) / 2;
		return Math.min(getMin(start, mid, treeLidx, treeRidx, tempIdx*2), getMin(mid+1, end, treeLidx, treeRidx, tempIdx*2+1));
	}
	
	private static int getMax(int start, int end, int treeLidx, int treeRidx, int tempIdx) {
		if(start > treeRidx || end < treeLidx) return Integer.MIN_VALUE;
		
		if(treeLidx <= start && end <= treeRidx) return maxSegment[tempIdx];
		
		int mid = start + (end - start) / 2;
		return Math.max(getMax(start, mid, treeLidx, treeRidx, tempIdx*2), getMax(mid+1, end, treeLidx, treeRidx, tempIdx*2+1));
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int [N+1];
		for(int i=1; i<N+1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		minSegment = new int [4*N];
		maxSegment = new int [4*N];
		makeMinSegment(1, N, 1);
		makeMaxSegment(1, N, 1);
		StringBuilder builder = new StringBuilder();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			builder.append(getMin(1, N, start, end, 1)).append(" ").append(getMax(1, N, start, end, 1)).append("\n");
		}
		
		builder.setLength(builder.length()-1);
		System.out.println(builder.toString());
	}

}
