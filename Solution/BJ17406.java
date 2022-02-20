package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17406 {
	
	static int N;
	static int M;
	static int K;
	static int minSum;
	static boolean[] check;
	static int[] selected;
	static RCS[] list;
	
	static class RCS {
		
		int r;
		int c;
		int s;
		
		public RCS(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
		
	}
	
	private static void permutation(int cnt, int[][] originArr) {
		if(cnt == K) {
		
			int[][] copyArr = new int [N][M];
			for(int i=0; i<N; i++) {
				System.arraycopy(originArr[i], 0, copyArr[i], 0, M);
			}
			
			int[][] resultArr = new int [N][M];
			for(int i=0; i<K; i++) {
				int index = selected[i];
				RCS temp = list[index];
				
				resultArr = rotateArr(copyArr, temp.r, temp.c, temp.s);
				copyArr = resultArr;
			}
			
			for(int i=0; i<N; i++) {
				int tempSum = 0;
				for(int j=0; j<M; j++) {
					tempSum += resultArr[i][j];
				}
				minSum = Math.min(minSum, tempSum);
			}
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(!check[i]) {
				selected[cnt] = i;
				check[i] = true;
				permutation(cnt+1, originArr);
				check[i] = false;
			}
		}
	}
	
	private static int[][] rotateArr(int[][] arr, int r, int c, int s){
		int[][] newArr = new int [N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				newArr[i][j] = arr[i][j];
			}
		}
		
		int start_x = r-s, start_y = c-s, end_x = r+s, end_y = c+s;
		int rotateCnt = (end_x - start_x + 1) / 2;
		
		for(int i=0; i<rotateCnt; i++) {
			start_x += i;
			start_y += i;
			end_x -= i;
			end_y -= i;
			
			for(int j=start_y; j<end_y; j++) {
				newArr[start_x][j+1] = arr[start_x][j];
				newArr[end_x][j] = arr[end_x][j+1];
			}
			
			for(int j=start_x; j<end_x; j++) {
				newArr[j+1][end_y] = arr[j][end_y];
				newArr[j][start_y] = arr[j+1][start_y];
			}
			
		}
		
		return newArr;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int [N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		list = new RCS [K];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			list[i] = new RCS(r-1, c-1, s);
		}
		
		selected = new int [K];
		check = new boolean [K];
		minSum = Integer.MAX_VALUE;
		
		permutation(0, arr);
		
		System.out.println(minSum);
	}

}
