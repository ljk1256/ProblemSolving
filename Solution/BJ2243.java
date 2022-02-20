package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ2243 {
	
	static ArrayList<Integer> candyList;
	static int[] segment;

	private static int searchCandy(int nodeLeft, int nodeRight, int tempCnt, int tempIdx) {
		if(nodeLeft == nodeRight) return nodeLeft;
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		if(segment[tempIdx*2] >= tempCnt) return searchCandy(nodeLeft, mid, tempCnt, tempIdx*2);
		else return searchCandy(mid+1, nodeRight, tempCnt-segment[tempIdx*2], tempIdx*2+1);
	}
	
	private static void update(int nodeLeft, int nodeRight, int targetIdx, int tempIdx, int diff) {
		if(nodeLeft == nodeRight) {
			segment[tempIdx] += diff;
			return;
		}
		
		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		if(targetIdx <= mid) update(nodeLeft, mid, targetIdx, tempIdx*2, diff);
		else update(mid+1, nodeRight, targetIdx, tempIdx*2+1, diff);
		segment[tempIdx] = segment[tempIdx*2] + segment[tempIdx*2+1];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		candyList = new ArrayList<>();
		segment = new int [4000000];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			
			if(A == 1) {
				int B = Integer.parseInt(st.nextToken());
				int taste = searchCandy(1, 1000000, B, 1);
				update(1, 1000000, taste, 1, -1);
				sb.append(taste).append("\n");
			}
			
			else {
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				update(1, 1000000, B, 1, C);
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
