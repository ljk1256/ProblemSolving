package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ1849 {
	
	static int[] segment;
	
	private static void makeSegment(int nodeLeft, int nodeRight, int tempIdx) {
		if(nodeLeft == nodeRight) {
			segment[tempIdx] = 1;
			return;
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		makeSegment(nodeLeft, mid, tempIdx*2);
		makeSegment(mid+1, nodeRight, tempIdx*2+1);
		
		segment[tempIdx] = segment[tempIdx*2] + segment[tempIdx*2+1];
	}
	
	private static int searchIdx(int nodeLeft, int nodeRight, int queryCnt, int tempIdx) {
		if(nodeLeft == nodeRight) {
			return nodeLeft;
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		if(segment[tempIdx*2] > queryCnt) return searchIdx(nodeLeft, mid, queryCnt, tempIdx*2); // 앞에 큰 수가 들어갈 자리 남은수가 현재 필요한 수보다 많을 경우만
		else return searchIdx(mid+1, nodeRight, queryCnt-segment[tempIdx*2], tempIdx*2+1); // 같을 경우는 보면 안된다
	}
	
	private static void update(int nodeLeft, int nodeRight, int targetIdx, int tempIdx) {
		if(nodeLeft == nodeRight) {
			segment[tempIdx] = 0;
			return;
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		if(targetIdx <= mid) update(nodeLeft, mid, targetIdx, tempIdx*2);
		else update(mid+1, nodeRight, targetIdx, tempIdx*2+1);
		
		segment[tempIdx] = segment[tempIdx*2] + segment[tempIdx*2+1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] Arr = new int [N+1];
		for(int i=1; i<=N; i++) {
			Arr[i] = Integer.parseInt(br.readLine());
		}
		
		int depth = (int)Math.ceil(Math.log(N)/Math.log(2));
		int treeSize = (int)Math.pow(2, depth+1);
		
		segment = new int [treeSize];
		makeSegment(1, N, 1);
		
		int[] results = new int [N+1];
		for(int i=1; i<=N; i++) {
			int index = searchIdx(1, N, Arr[i], 1);
			results[index] = i;
			update(1, N, index, 1);
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=1; i<=N; i++) {
			bw.write(String.valueOf(results[i]));
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}

}
