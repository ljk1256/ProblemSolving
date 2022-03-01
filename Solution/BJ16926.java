package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16926 {
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	private static int[][] rotateArr(int[][] arr) {
		int row = arr.length, column = arr[0].length;
		int depth = Math.min(row, column) / 2;
		
		for(int start=0, end = column-1; start<depth; start++, end--) {
			int swap = arr[start][start], prev = arr[start][start];
			int x = start, y = start;
			int dir = 0;
			
			while(dir <= 3) {
				x += dx[dir];
				y += dy[dir];
				
				if(x < start || x >= row-start || y < start || y > end) { // 범위를 벗어 났다면 방향전환
					x -= dx[dir];
					y -= dy[dir];
					dir++;
				}
				else {
					prev = arr[x][y];
					arr[x][y] = swap;
					swap = prev;
				}
			}
		}
		
		return arr;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // row
		int M = Integer.parseInt(st.nextToken()); // column
		int R = Integer.parseInt(st.nextToken()); // rotate
		
		int[][] arr = new int [N][M];
		
		for(int i=0;  i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<R; i++) arr = rotateArr(arr);
		
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				builder.append(arr[i][j]).append(" ");
			}
			builder.setLength(builder.length()-1);
			builder.append("\n");
		}
		
		builder.setLength(builder.length()-1);
		System.out.println(builder.toString());
	}

}
