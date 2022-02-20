package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ11505 {
	
	static int[] Arr;
	static long[] segment;
	static final long MOD = 1000000007;
	
	private static void makeSegment(int nodeLeft, int nodeRight, int tempIdx) {
		if(nodeLeft == nodeRight) {
			segment[tempIdx] = Arr[nodeLeft];
			return;
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		makeSegment(nodeLeft, mid, tempIdx*2);
		makeSegment(mid+1, nodeRight, tempIdx*2+1);
		segment[tempIdx] = (segment[tempIdx*2] % MOD) * (segment[tempIdx*2+1] % MOD) % MOD;
	}
	
	private static void update(int nodeLeft, int nodeRight, int targetIdx, int tempIdx, int diff) {
		if(nodeLeft == nodeRight) {
			segment[tempIdx] = diff;
			return;
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		if(targetIdx <= mid) update(nodeLeft, mid, targetIdx, tempIdx*2, diff);
		else update(mid+1, nodeRight, targetIdx, tempIdx*2+1, diff);
		
		segment[tempIdx] = (segment[tempIdx*2] % MOD) * (segment[tempIdx*2+1] % MOD) % MOD; 
	}
	
	private static long sectionMul(int nodeLeft, int nodeRight, int queryLeft, int queryRight, int tempIdx) {
		if(queryRight < nodeLeft || nodeRight < queryLeft) return 1L;
		if(queryLeft <= nodeLeft && nodeRight <= queryRight) return segment[tempIdx];
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		return (sectionMul(nodeLeft, mid, queryLeft, queryRight, tempIdx*2) % MOD) * (sectionMul(mid+1, nodeRight, queryLeft, queryRight, tempIdx*2+1) % MOD) % MOD;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Arr = new int [N+1];
		for(int i=1; i<N+1; i++) {
			Arr[i] = Integer.parseInt(br.readLine());
		}
		
		int h = (int)Math.ceil((Math.log(N) / Math.log(2)));
		int treeSize = (int)Math.pow(2, h+1);
		
		segment = new long [treeSize];
		makeSegment(1, N, 1);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			
			if(number == 1) {
				int index = Integer.parseInt(st.nextToken());
				int diff = Integer.parseInt(st.nextToken());
				update(1, N, index, 1, diff);
			}
			else {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				long result = sectionMul(1, N, start, end , 1);
				bw.write(String.valueOf(result));
				bw.write("\n");
			}
		}
		
		bw.flush();
		bw.close();
	}

}
