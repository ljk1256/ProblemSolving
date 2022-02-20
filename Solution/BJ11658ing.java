package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ11658ing {
	
	static int N;
	static long[] segment;
	static long[][] arr;
	
	private static int convert(int x, int y) {
		return (x-1)*N + y;
	}
	
	private static int getSegmentSize(int leafCnt) {
		int h = (int)Math.ceil(Math.log(leafCnt) / Math.log(2));
		int size = (int)Math.pow(2, h+1);
		return size;
	}
	
	private static long update(int leftNode, int rightNode, int targetIdx, int tempIdx, long diff) {
		if(leftNode == rightNode) segment[tempIdx] = diff;
		else {
			int mid = leftNode + (rightNode - leftNode) / 2;
			if(targetIdx <= mid) update(leftNode, mid, targetIdx, tempIdx*2, diff);
			else update(mid+1, rightNode, targetIdx, tempIdx*2+1, diff);
			segment[tempIdx] = segment[tempIdx*2] + segment[tempIdx*2+1];
		}
		return segment[tempIdx];
	}
	
	private static long prefixSum(int leftNode, int rightNode, int queryLeft, int queryRight, int tempIdx) {
		if(rightNode < queryLeft || queryRight < leftNode) return 0L;
		if(queryLeft <= leftNode && rightNode <= queryRight) return segment[tempIdx];
		
		int mid = leftNode + (rightNode - leftNode) / 2;
		return prefixSum(leftNode, mid, queryLeft, queryRight, tempIdx*2) + prefixSum(mid+1, rightNode, queryLeft, queryRight, tempIdx*2+1);
	}
	
	private static long getLeaf(int leftNode, int rightNode, int targetIdx, int tempIdx) {
		if(leftNode == rightNode) return segment[tempIdx];
		else {
			int mid = leftNode + (rightNode - leftNode) / 2;
			if(targetIdx <= mid) return getLeaf(leftNode, mid, targetIdx, tempIdx*2);
			else return getLeaf(mid+1, rightNode, targetIdx, tempIdx*2+1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken()); // 리프 노드의 수
		int M = Integer.parseInt(st.nextToken()); // 연산 횟수
		
		arr = new long [N+1][N+1];
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				arr[i][j] = Long.parseLong(st.nextToken());
			}
		}
		
		segment = new long [getSegmentSize(N*N)];
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				update(1, N*N, convert(i, j), 1, arr[i][j]);
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			
			if(command == 0) { // 바꾸는 연산
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				long diff = Long.parseLong(st.nextToken());
				
				update(1, N*N, convert(x, y), 1, diff);
			}
			else { // 구간 합 구하는 연산
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				
				int minRow = Math.min(x1, x2);
				int maxRow = Math.max(x1, x2);
				int minCol = Math.min(y1, y2);
				int maxCol = Math.max(y1, y2);
				
				long result = prefixSum(1, N*N, convert(x1, y1), convert(x2, y2), 1);
				
				for(int j=minRow; j<=maxRow; j++) {
					for(int k=1; k<=N; k++) {
						if(j == minRow) {
							if(k > maxCol) result -= getLeaf(1, N*N, convert(j, k), 1);
						}
						else if(j == maxRow) {
							if(k < minCol) result -= getLeaf(1, N*N, convert(j, k), 1);
						}
						else {
							if(k < minCol || k > maxCol) result -= getLeaf(1, N*N, convert(j, k), 1);
						}
					}
				}
				
				bw.write(String.valueOf(result));
				bw.write("\n");
			}
		}
		
		bw.flush();
		bw.close();
	}

}
