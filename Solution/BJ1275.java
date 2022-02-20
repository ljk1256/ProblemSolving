package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1275 {
	
	static long[] Arr;
	static long[] segment;
	
	private static void makeSegment(int nodeLeft, int nodeRight, int tempIdx) {
		if(nodeLeft == nodeRight) {
			segment[tempIdx] = Arr[nodeLeft];
			return;
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		makeSegment(nodeLeft, mid, tempIdx*2);
		makeSegment(mid+1, nodeRight, tempIdx*2+1);
		
		segment[tempIdx] = segment[tempIdx*2] + segment[tempIdx*2+1];
	}
	
	private static long prefixSum(int queryLeft, int queryRight, int nodeLeft, int nodeRight, int tempIdx) {
		if(nodeLeft > queryRight || nodeRight < queryLeft) return 0L;
		if(queryLeft <= nodeLeft && nodeRight <= queryRight) return segment[tempIdx];
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		return prefixSum(queryLeft, queryRight, nodeLeft, mid, tempIdx*2) + prefixSum(queryLeft, queryRight, mid+1, nodeRight, tempIdx*2+1);
	}
	
	private static void update(int nodeLeft, int nodeRight, int targetIdx, int tempIdx, int diff) {
		if(nodeLeft == nodeRight) {
			segment[tempIdx] = diff;
			return;
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		if(targetIdx <= mid) update(nodeLeft, mid, targetIdx, tempIdx*2, diff);
		else update(mid+1, nodeRight, targetIdx, tempIdx*2+1, diff);
		
		segment[tempIdx] = segment[tempIdx*2] + segment[tempIdx*2+1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		Arr = new long [N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			Arr[i] = Long.parseLong(st.nextToken());
		}
		
		int h = (int)Math.ceil(Math.log(N) / Math.log(2)); // 자바 로그는 자연로그이기 때문에 밑을 2로 하기위해서 log2로 나눠준다
		int treeSize = (int)Math.pow(2, h+1);
		segment = new long [treeSize];
		makeSegment(1, N, 1);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			int diff = Integer.parseInt(st.nextToken());
			
			if(start > end) sb.append(prefixSum(end, start, 1, N, 1)).append("\n");
			else sb.append(prefixSum(start, end, 1, N, 1)).append("\n");
			
			update(1, N, index, 1, diff);
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
