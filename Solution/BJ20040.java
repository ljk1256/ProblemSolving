package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20040 {
	
	static int[] parent;
	
	private static boolean union(int a, int b) {
		int aRoot = findParent(a);
		int bRoot = findParent(b);
		
		if(aRoot == bRoot) return true;
		else {
			parent[bRoot] = aRoot;
			return false;
		}
	}
	
	private static int findParent(int n) {
		if(parent[n] == n) {
			return parent[n];
		}
		
		return parent[n] = findParent(parent[n]); // 계속 재귀 호출이 발생하기에 한번 할때 최상위 부모를 저장 해놓는다면 다음 호출 시 빨라진다
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int [n]; // 부모 초기화
		for(int i=0; i<n; i++) {
			parent[i] = i;
		}
		
		for(int i=1; i<m+1; i++) {
			st = new StringTokenizer(br.readLine());
			if(union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
				System.out.println(i);
				return;
			}
		}
		
		System.out.println(0);
	}

}
