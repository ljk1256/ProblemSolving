package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12100 {
	
	static int answer;
	static int N;
	
	static class Block {
		
		int x;
		int y;
		boolean isUnion;
		int cost;
		
		public Block(int x, int y, boolean isUnion, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.isUnion = isUnion;
			this.cost = cost;
		}
		
	}
	
	private static void Play(Block[][] map) {
		Move(map, 0, 0);
		Move(map, 0, 1);
		Move(map, 0, 2);
		Move(map, 0, 3);
	}
	
	private static void Move(Block[][] map, int cnt, int dir) { // 0 : 위, 1 : 아래, 2 : 왼, 3 : 오
		if(cnt == 5) {
			for(Block[] blocks : map) {
				for(Block b : blocks) {
					if(b == null) continue;
					if(b.cost > answer) answer = b.cost;
				}
			}
			return;
		}
		
		// 배열복사
		Block[][] newmap = new Block [N][N]; // 객체 복사는 안된다
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == null) continue;
				
				newmap[i][j] = new Block(map[i][j].x, map[i][j].y, map[i][j].isUnion, map[i][j].cost);
			}
		}
		
		// 합쳐진 여부 초기화
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(newmap[i][j] == null) continue;
				
				newmap[i][j].isUnion = false;
			}
		}
		
		// 해당 방향으로 전부 이동 및 합치기
		switch(dir) {
		case 0 : {
			for(int i=0; i<N; i++) { // i column을 의미
				for(int j=1; j<N; j++) {
					if(newmap[j][i] == null) continue;
					
					Block temp = new Block(newmap[j][i].x, newmap[j][i].y, newmap[j][i].isUnion, newmap[j][i].cost);
					int temp_row = j-1;
					boolean union = false;
					
					while(temp_row >= 0) {
						if(newmap[temp_row][i] == null) {
							if(temp_row == 0) break; // 끝까지 도달 했다면
							temp_row--;
							continue;
						}
						
						if(newmap[temp_row][i].cost != temp.cost) {
							temp_row++;
							break;
						}
						
						if(newmap[temp_row][i].cost == temp.cost) {
							if(newmap[temp_row][i].isUnion) {
								temp_row++;
								break;
							}
							else {
								newmap[temp_row][i].cost += temp.cost;
								newmap[temp_row][i].isUnion = true;
								union = true;
								break;
							}
						}
					}
					
					newmap[temp.x][temp.y] = null;
					if(union) continue;
					newmap[temp_row][i] = new Block(temp_row, i, false, temp.cost);
				}
			}
			
			Move(newmap, cnt+1, 0);
			Move(newmap, cnt+1, 1);
			Move(newmap, cnt+1, 2);
			Move(newmap, cnt+1, 3);
			break;
		}
		
		case 1 : {
			for(int i=0; i<N; i++) { // i column을 의미
				for(int j=N-2; j>=0; j--) {
					if(newmap[j][i] == null) continue;
					
					Block temp = new Block(newmap[j][i].x, newmap[j][i].y, newmap[j][i].isUnion, newmap[j][i].cost);
					int temp_row = j+1;
					boolean union = false;
					
					while(temp_row <= N-1) {
						if(newmap[temp_row][i] == null) {
							if(temp_row == N-1) break; // 끝까지 도달 했다면
							temp_row++;
							continue;
						}
						
						if(newmap[temp_row][i].cost != temp.cost) {
							temp_row--;
							break;
						}
						
						if(newmap[temp_row][i].cost == temp.cost) {
							if(newmap[temp_row][i].isUnion) {
								temp_row--;
								break;
							}
							else {
								newmap[temp_row][i].cost += temp.cost;
								newmap[temp_row][i].isUnion = true;
								union = true;
								break;
							}
						}
					}
					
					newmap[temp.x][temp.y] = null;
					if(union) continue;
					newmap[temp_row][i] = new Block(temp_row, i, false, temp.cost);
				}
			}
			
			Move(newmap, cnt+1, 0);
			Move(newmap, cnt+1, 1);
			Move(newmap, cnt+1, 2);
			Move(newmap, cnt+1, 3);
			break;
		}
		
		case 2 :{
			for(int i=0; i<N; i++) { // i row을 의미
				for(int j=1; j<N; j++) {
					if(newmap[i][j] == null) continue;
					
					Block temp = new Block(newmap[i][j].x, newmap[i][j].y, newmap[i][j].isUnion, newmap[i][j].cost);
					int temp_column = j-1;
					boolean union = false;
					
					while(temp_column >= 0) {
						if(newmap[i][temp_column] == null) {
							if(temp_column == 0) break; // 끝까지 도달 했다면
							temp_column--;
							continue;
						}
						
						if(newmap[i][temp_column].cost != temp.cost) {
							temp_column++;
							break;
						}
						
						if(newmap[i][temp_column].cost == temp.cost) {
							if(newmap[i][temp_column].isUnion) {
								temp_column++;
								break;
							}
							else {
								newmap[i][temp_column].cost += temp.cost;
								newmap[i][temp_column].isUnion = true;
								union = true;
								break;
							}
						}
					}
					
					newmap[temp.x][temp.y] = null;
					if(union) continue;
					newmap[i][temp_column] = new Block(i, temp_column, false, temp.cost);
				}
			}
			
			Move(newmap, cnt+1, 0);
			Move(newmap, cnt+1, 1);
			Move(newmap, cnt+1, 2);
			Move(newmap, cnt+1, 3);
			break;
		}
		
		case 3 : {
			for(int i=0; i<N; i++) { // i row을 의미
				for(int j=N-2; j>=0; j--) {
					if(newmap[i][j] == null) continue;
					
					Block temp = new Block(newmap[i][j].x, newmap[i][j].y, newmap[i][j].isUnion, newmap[i][j].cost);
					int temp_column = j+1;
					boolean union = false;
					
					while(temp_column <= N-1) {
						if(newmap[i][temp_column] == null) {
							if(temp_column == N-1) break;
							temp_column++;
							continue;
						}
						
						if(newmap[i][temp_column].cost != temp.cost) {
							temp_column--;
							break;
						}
						
						if(newmap[i][temp_column].cost == temp.cost) {
							if(newmap[i][temp_column].isUnion) {
								temp_column--;
								break;
							}
							else {
								newmap[i][temp_column].cost += temp.cost;
								newmap[i][temp_column].isUnion = true;
								union = true;
								break;
							}
						}
					}
					
					newmap[temp.x][temp.y] = null;
					if(union) continue;
					newmap[i][temp_column] = new Block(i, temp_column, false, temp.cost);
				}
			}
			
			Move(newmap, cnt+1, 0);
			Move(newmap, cnt+1, 1);
			Move(newmap, cnt+1, 2);
			Move(newmap, cnt+1, 3);
			break;
		}
		
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		Block[][] map = new Block [N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				
				if(cost == 0) continue;
				
				map[i][j] = new Block(i, j, false, cost);
			}
		}
		
		answer = 0;
		Play(map);
		System.out.println(answer);
	}

}
