package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2042 {
	
	static long[] Arr;
	static long[] segment;
	
	private static long makeSegment(int nodeLeft, int nodeRight, int tempIdx) {
		if(nodeLeft == nodeRight) {
			segment[tempIdx] = Arr[nodeLeft];
		}
		
		else {
			int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
			segment[tempIdx] = makeSegment(nodeLeft, mid, tempIdx*2) + makeSegment(mid+1, nodeRight, tempIdx*2+1);
		}
		return segment[tempIdx];
	}
	
	private static void update(int nodeLeft, int nodeRight, int nodeIdx, int tempIdx, long change) {
		if(nodeLeft == nodeRight) {
			segment[tempIdx] = change;
			return;
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		if(nodeIdx <= mid) update(nodeLeft, mid, nodeIdx, tempIdx*2, change);
		else update(mid+1, nodeRight, nodeIdx, tempIdx*2+1, change);
		
		segment[tempIdx] = segment[tempIdx*2] + segment[tempIdx*2+1];
	}
	
	private static long prefixSum(int nodeLeft, int nodeRight, int queryLeft, int queryRight, int tempIdx) {
		if(nodeRight < queryLeft || nodeLeft > queryRight) return 0;
		if(queryLeft <= nodeLeft && nodeRight <= queryRight) return segment[tempIdx];
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		return prefixSum(nodeLeft, mid, queryLeft, queryRight, tempIdx*2) + prefixSum(mid+1, nodeRight, queryLeft, queryRight, tempIdx*2+1);
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Arr = new long [N+1];
		for(int i=1; i<N+1; i++) {
			Arr[i] = Long.parseLong(br.readLine());
		}
		
		segment = new long [N*4];
		makeSegment(1, N, 1);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			
			if(a == 1) {
				int index = Integer.parseInt(st.nextToken());
				long change = Long.parseLong(st.nextToken());
				update(1, N, index, 1, change);
			}
			else {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				sb.append(prefixSum(1, N, start, end, 1)).append("\n");
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
