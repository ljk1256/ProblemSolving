package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ19236 {
	
	static class Shark {
		
		int x;
		int y;
		int dir;
		
		public Shark(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}
	
	static class Fish {
		
		int x;
		int y;
		int number;
		int dir;
		
		public Fish(int x, int y, int number, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.number = number;
			this.dir = dir;
		}
		
	}
	
	static int totalSum;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	
	private static void Init(Fish[][] fishmap) {
		
		Shark newShark = new Shark(0, 0, 0);
		Fish eatedFish = fishmap[0][0];
		
		newShark.dir = eatedFish.dir;
		int eatSum = eatedFish.number;
		fishmap[0][0] = null;
		
		moveShark(new Shark(newShark.x, newShark.y, newShark.dir), fishmap, eatSum);
	}
	
	private static void moveShark(Shark shark, Fish[][] fishmap, int prevSum) {
		
		Fish[][] copyMap = new Fish [4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(fishmap[i][j] != null) copyMap[i][j] = new Fish(i, j, fishmap[i][j].number, fishmap[i][j].dir);
			}
		}
		
		int FishNum = 1;	
		
		cp:for(; FishNum <=16; FishNum++) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(copyMap[i][j] != null && copyMap[i][j].number == FishNum) {
						Fish temp = new Fish(i, j, copyMap[i][j].number, copyMap[i][j].dir);
						int dir = copyMap[i][j].dir;
						
						for(int k=0; k<8; k++) {
							int nx = i + dx[(dir+k)%8];
							int ny = j + dy[(dir+k)%8];
							
							if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
							
							if(nx == shark.x && ny == shark.y) continue;
							
							if(copyMap[nx][ny] != null) {
								Fish tradeFish = new Fish(nx, ny, copyMap[nx][ny].number, copyMap[nx][ny].dir);
								copyMap[nx][ny] = new Fish(nx, ny, temp.number, (dir+k)%8);
								copyMap[i][j] = new Fish(i, j, tradeFish.number, tradeFish.dir);
								continue cp;
							}
							
							else {
								copyMap[i][j] = null;
								copyMap[nx][ny] = new Fish(nx, ny, temp.number, (dir+k)%8);
								continue cp;
							}
						}
					}
				}
			}
		}
		
		int nx = shark.x + dx[shark.dir];
		int ny = shark.y + dy[shark.dir];
		
		while(nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
			
			if(copyMap[nx][ny] != null) {
				Fish prevFish = new Fish(nx, ny, copyMap[nx][ny].number, copyMap[nx][ny].dir);
				Shark newShark = new Shark(nx, ny, copyMap[nx][ny].dir);
				int tempSum = prevSum + copyMap[nx][ny].number;
				copyMap[nx][ny] = null;
				moveShark(newShark, copyMap, tempSum);
				
				copyMap[nx][ny] = new Fish(prevFish.x, prevFish.y, prevFish.number, prevFish.dir); // 백트래킹
			}
			
			nx += dx[shark.dir];
			ny += dy[shark.dir];
		}
		
		totalSum = Math.max(totalSum, prevSum);
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Fish[][] fishMap = new Fish [4][4];
		
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				int fishNum = Integer.parseInt(st.nextToken());
				int fishDir = Integer.parseInt(st.nextToken());
				fishMap[i][j] = new Fish(i, j, fishNum, fishDir-1);
			}
		}
		
		Init(fishMap);
		System.out.println(totalSum);
	}

}
