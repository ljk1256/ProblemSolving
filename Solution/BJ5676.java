package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ5676 {
	
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
		segment[tempIdx] = segment[tempIdx*2] * segment[tempIdx*2+1];
	}
	
	private static long update(int nodeLeft, int nodeRight, int nodeIdx, int tempIdx, int diff) {
		if(nodeLeft == nodeRight) {
			if(diff < 0) segment[tempIdx] = -1L;
			else if(diff > 0) segment[tempIdx] = 1L;
			else segment[tempIdx] = 0;
		}
		
		else {
			int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
			if(nodeIdx <= mid) update(nodeLeft, mid, nodeIdx, tempIdx*2, diff);
			else update(mid+1, nodeRight, nodeIdx, tempIdx*2+1, diff);
			segment[tempIdx] = segment[tempIdx*2] * segment[tempIdx*2+1];
		}
		
		return segment[tempIdx];
	}
	
	private static long sectionMul(int nodeLeft, int nodeRight, int queryLeft, int queryRight, int tempIdx) {
		if(queryRight < nodeLeft || nodeRight < queryLeft) return 1L;
		if(queryLeft <= nodeLeft && nodeRight <= queryRight) return segment[tempIdx];
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		return sectionMul(nodeLeft, mid, queryLeft, queryRight, tempIdx*2) * sectionMul(mid+1, nodeRight, queryLeft, queryRight, tempIdx*2+1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String firstLine = br.readLine();
			
			if(firstLine == null || firstLine.isEmpty()) break; // 더 이상 입력이 없다면
			
			st = new StringTokenizer(firstLine);
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			Arr = new long [N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<N+1; i++) {
				long number = Long.parseLong(st.nextToken());
				if(number < 0) Arr[i] = -1L;
				else if(number > 0) Arr[i] = 1L;
				else Arr[i] = 0;
			}
			
			segment = new long [4*N];
			makeSegment(1, N, 1);
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				
				if(st.nextToken().equals("C")) {
					int index = Integer.parseInt(st.nextToken());
					int diff = Integer.parseInt(st.nextToken());
					
					update(1, N, index, 1, diff);
				}
				
				else {
					int start = Integer.parseInt(st.nextToken());
					int end = Integer.parseInt(st.nextToken());
					long result = sectionMul(1, N, start, end, 1);
					
					if(result < 0) sb.append("-");
					else if(result > 0) sb.append("+");
					else sb.append(0);
				}
			}
			
			sb.append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
