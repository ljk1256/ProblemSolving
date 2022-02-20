package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ15558 {
	
	static class Point {
		
		int x;
		int isLeft; // 왼쪽줄 0, 오른쪽 1
		
		public Point(int x, int isLeft) {
			super();
			this.x = x;
			this.isLeft = isLeft;
		}
		
	}
	
	static int N;
	static int k;
	static Queue<Point> q;
	static boolean[][] visited;
	static boolean flag;
	static int[][] line;
	
	private static void move() {
		int size = q.size();
		for(int i=0; i<size; i++) {
			Point temp = q.poll();
			int x = temp.x;
			int isLeft = temp.isLeft;
			
			if(temp.x > N-1) {
				flag = true;
				return;
			}
			
			if(x+1 < N && !visited[isLeft][x+1] && line[isLeft][x+1] == 1) {
				q.offer(new Point(x+1, isLeft));
				visited[isLeft][x+1] = true;
			}
			
			if(x-1 >= 0 && !visited[isLeft][x-1] && line[isLeft][x-1] == 1) {
				q.offer(new Point(x-1, isLeft));
				visited[isLeft][x-1] = true;
			}
			
			if(x+k < N && !visited[(isLeft+1)%2][x+k] && line[(isLeft+1)%2][x+k] == 1) {
				q.offer(new Point(x+k, (isLeft+1)%2));
				visited[(isLeft+1)%2][x+k] = true;
			}
			
			if(x+1 >= N || x+k >= N) {
				q.offer(new Point(N, isLeft));
			}
		}
	}
	
	private static void invalid(int time) {
		if(time < N) {
			for(int i=0; i<2; i++) line[i][time] = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		line = new int [2][N];
		for(int i=0; i<2; i++) {
			String input = br.readLine();
			for(int j=0; j<N; j++) {
				line[i][j] = input.charAt(j) - '0';
			}
		}
		
		flag = false;
		q = new LinkedList<>();
		visited = new boolean [2][N];
		q.offer(new Point(0, 0));
		visited[0][0] = true;
		int time = 0;
		
		while(!q.isEmpty()) {
			invalid(time++);
			move();
			if(flag) {
				System.out.println(1);
				System.exit(0);
			}
		}
		
		System.out.println(0);
	}

}
