package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14890 {
	
	static int[][] map;
	static int N;
	static int L;
	static int answer;
	
	static class Point {
		
		int x;
		int y;
		int dir;
		
		public Point(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}
	
	private static boolean isMove(Point point) {
		int[] ground = new int [N];
		if(point.dir == 0) {
			for(int i=0; i<N; i++) {
				ground[i] = map[i][point.y];
			}
		}
		else {
			for(int i=0; i<N; i++) {
				ground[i] = map[point.x][i];
			}
		}
		
		int prev = ground[0];
		boolean[] isUsed = new boolean [N];
		
		for(int i=1; i<N; i++) {
			if(prev == ground[i]) continue;
			
			if(prev < ground[i]) {
				if(i-L < 0) return false;
				for(int j=i-1; j>=i-L; j--) {
					if(ground[j] != prev || isUsed[j] || prev+1 != ground[i]) return false;
					isUsed[j] = true;
				}
				
				prev = ground[i];
			}
			
			else {
				prev = ground[i];
				if(i+L-1 >= N) return false;
				for(int j=i; j<=i+L-1; j++) {
					if(ground[j] != prev || isUsed[j] || prev+1 != ground[i-1]) return false;
					isUsed[j] = true;
				}
				
				i = i+L-1;
			}
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int [N][N];
		Queue<Point> q = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(i == 0) q.offer(new Point(i, j, 0)); // 세로줄
				if(j == 0) q.offer(new Point(i, j, 1)); // 가로줄
			}
		}
		
		answer = 0;
		while(!q.isEmpty()) {
			Point temp = q.poll();
			if(isMove(temp)) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}

}
