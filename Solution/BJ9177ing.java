package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ9177ing {
	
	static class Point {
		
		int a_idx;
		int b_idx;
		
		public Point(int a_idx, int b_idx) {
			super();
			this.a_idx = a_idx;
			this.b_idx = b_idx;
		}
		
	}
	
	static char[] union;
	static char[] A;
	static char[] B;
	
	private static boolean bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean [A.length+1][B.length+1];
		q.offer(new Point(0, 0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Point temp = q.poll();
			
			if(temp.a_idx + temp.b_idx == union.length-2) { // A, B문자열 모두 탐색이 끝났다면
				return true;
			}
			
			if(temp.a_idx < A.length && A[temp.a_idx] == union[temp.a_idx + temp.b_idx] && !visited[temp.a_idx+1][temp.b_idx]) { // 범위를 벗어나지않고 내 문자와 합쳐진 문자열의 문자가 같다면
				q.offer(new Point(temp.a_idx+1, temp.b_idx)); // 다음 문자열 탐색시도
				visited[temp.a_idx+1][temp.b_idx] = true;
			}
			
			if(temp.b_idx < B.length && B[temp.b_idx] == union[temp.a_idx + temp.b_idx] && !visited[temp.a_idx][temp.b_idx+1]) { // 범위를 벗어나지않고 내 문자와 합쳐진 문자열의 문자가 같다면
				q.offer(new Point(temp.a_idx, temp.b_idx+1)); // 다음 문자열 탐색시도
				visited[temp.a_idx][temp.b_idx+1] = true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			A = st.nextToken().toCharArray();
			B = st.nextToken().toCharArray();
			union = st.nextToken().toCharArray();
			
			sb = new StringBuilder();
			sb.append("Data Set ").append(i).append(": ");
			
			if(bfs()) {
				sb.append("yes");
			}
			else {
				sb.append("no");
			}
			
			System.out.println(sb.toString());
		}
	}

}
