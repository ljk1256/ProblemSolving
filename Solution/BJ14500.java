package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14500 {
	
	static int answer;
	static int N;
	static int M;
	static int[][] map;
	static int[][][] dir = { 
								{{1,0},{2,0},{3,0}},    //0. ㅡ
					            {{0,1},{0,2},{0,3}},    //1. ㅣ
					            {{1,1},{0,1},{1,0}},        //2. ㅁ
					            {{1,0},{2,0},{2,1}},    //3. L
					            {{1,0},{2,0},{2,-1}},    //4.
					            {{0,1},{0,2},{1,2}},    //5.
					            {{0,1},{0,2},{-1,2}},    //6.
					            {{-1,0},{-2,0},{-2,1}},    //7.
					            {{-1,0},{-2,0},{-2,-1}},//8.
					            {{0,-1},{0,-2},{1,-2}},    //9.
					            {{0,-1},{0,-2},{-1,-2}},//10.
					            {{1,0},{1,1},{2,1}},        //11. ㄹ
					            {{1,0},{1,-1},{2,-1}},        //12.
					            {{0,1},{-1,1},{-1,2}},        //13.
					            {{0,1},{1,1},{1,2}},        //14.
					            {{0,1},{0,2},{1,1}},    //15. ㅏ
					            {{0,1},{0,2},{-1,1}},    //16.
					            {{1,0},{2,0},{1,1}},    //17.
					            {{1,0},{2,0},{1,-1}}    //18.
						  };
	
	private static void Play(int row, int column) {
		int x = row;
		int y = column;
		int sum = 0;
		int tempmax = 0;
		
   cp : for(int i=0; i<19; i++) {
			sum = map[x][y];
			
			for(int j=0; j<3; j++) {
				int nx = x + dir[i][j][0];
				int ny = y + dir[i][j][1];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue cp;
				
				sum += map[nx][ny];
			}
			
			if(tempmax < sum) tempmax = sum;
		}
		
		answer = Math.max(answer, tempmax);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int [N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				Play(i, j);
			}
		}
		
		System.out.println(answer);
	}

}
