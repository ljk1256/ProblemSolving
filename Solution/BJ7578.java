package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ7578 {
	
	static int[] segment;
	
	private static int getSegmentSize(int leafCnt) {
		int h = (int)Math.ceil(Math.log(leafCnt) / Math.log(2));
		return (int)Math.pow(2, h+1);
	}
	
	private static void update(int nodeLeft, int nodeRight, int targetIdx, int tempIdx) {
		if(nodeLeft == nodeRight) {
			segment[tempIdx]++;
			return;
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		if(targetIdx <= mid) update(nodeLeft, mid, targetIdx, tempIdx*2);
		else update(mid+1, nodeRight, targetIdx, tempIdx*2+1);
		
		segment[tempIdx] = segment[tempIdx*2] + segment[tempIdx*2+1];
	}
	
	private static long prefixSum(int nodeLeft, int nodeRight, int queryLeft, int queryRight, int tempIdx) {
		if(nodeLeft > queryRight || nodeRight < queryLeft) return 0;
		if(queryLeft <= nodeLeft && nodeRight <= queryRight) return segment[tempIdx];
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		return prefixSum(nodeLeft, mid, queryLeft, queryRight, tempIdx*2) + prefixSum(mid+1, nodeRight, queryLeft, queryRight, tempIdx*2+1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] Arr = new int [N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			Arr[i] = Integer.parseInt(st.nextToken());
		}
		
		HashMap<Integer, Integer> hm = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			hm.put(Integer.parseInt(st.nextToken()), i);
		}
		
		segment = new int [getSegmentSize(N)];
		long crossCnt = 0;
		
		for(int i=1; i<=N; i++) {
			crossCnt += prefixSum(1, N, hm.get(Arr[i])+1, N, 1);
			update(1, N, hm.get(Arr[i]), 1);
		}
		
		System.out.println(crossCnt);
	}

}
