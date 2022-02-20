package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ17143 {
	
	static int answer;
	static int R;
	static int C;
	static Shark [][] map;
	static PriorityQueue<Shark> first_q;
	
	private static void move() {
		
		for(int i=1; i<R+1; i++) {  // 움직이기 전에 낚시한 상어가 있을 수 있기에 남아있는 상어만 큐에 넣기
			for(int j=1; j<C+1; j++) {
				if(map[i][j] != null) {
					first_q.offer(map[i][j]);
				}
			}
		}
		
		map = new Shark [R+1][C+1]; // 이동된 상어 배치를 위해 새로 선언
		
		for(int i=0; i<R+1; i++) {
			for(int j=0; j<C+1; j++) {
				map[i][j] = null;
			}
		}
		
		while(!first_q.isEmpty()) {
			Shark temp = first_q.poll();

			switch(temp.dir) {
			case 1 : {
				int x = temp.x;
				int div = (temp.v) % ((R-1) * 2);
				int flag = 1;
				for(int i=0; i<div; i++) {
					if(flag == 1) {
						if(x == 1) {
							flag = -1;
							x++;
							continue;
						}
						x--;
					}
					else {
						if(x == R) {
							flag = 1;
							x--;
							continue;
						}
						x++;
					}
				}
				
				if(flag == 1) {
					map[x][temp.y] = new Shark(x, temp.y, temp.v, temp.dir, temp.size);
				}
				else {
					map[x][temp.y] = new Shark(x, temp.y, temp.v, 2, temp.size);
				}
				break;
			}
			
			case 2 : {
				int x = temp.x;
				int div = (temp.v) % ((R-1) * 2);
				int flag = 1;
				for(int i=0; i<div; i++) {
					if(flag == 1) {  
						if(x == R) {  // 혹시 답이 이상하다면 이 부분 확인 해봐야함
							flag = -1;
							x--;
							continue;
						}
						x++;
					}
					else {
						if(x == 1) {
							flag = 1;
							x++;
							continue;
						}
						x--;
					}
				}
				
				if(flag == 1) {
					map[x][temp.y] = new Shark(x, temp.y, temp.v, temp.dir, temp.size);
				}
				else {
					map[x][temp.y] = new Shark(x, temp.y, temp.v, 1, temp.size);
				}
				break;
			}
			
			case 3 : {
				int y = temp.y;
				int div = (temp.v) % ((C-1) * 2);
				int flag = 1;
				for(int i=0; i<div; i++) {
					if(flag == 1) {  
						if(y == C) {  // 혹시 답이 이상하다면 이 부분 확인 해봐야함
							flag = -1;
							y--;
							continue;
						}
						y++;
					}
					else {
						if(y == 1) {
							flag = 1;
							y++;
							continue;
						}
						y--;
					}
				}
				
				if(flag == 1) {
					map[temp.x][y] = new Shark(temp.x, y, temp.v, temp.dir, temp.size);
				}
				else {
					map[temp.x][y] = new Shark(temp.x, y, temp.v, 4, temp.size);
				}
				break;
			}
			
			case 4 : {
				int y = temp.y;
				int div = (temp.v) % ((C-1) * 2);
				int flag = 1;
				for(int i=0; i<div; i++) {
					if(flag == 1) {  
						if(y == 1) {  // 혹시 답이 이상하다면 이 부분 확인 해봐야함
							flag = -1;
							y++;
							continue;
						}
						y--;
					}
					else {
						if(y == C) {
							flag = 1;
							y--;
							continue;
						}
						y++;
					}
				}
				
				if(flag == 1) {
					map[temp.x][y] = new Shark(temp.x, y, temp.v, temp.dir, temp.size);
				}
				else {
					map[temp.x][y] = new Shark(temp.x, y, temp.v, 3, temp.size);
				}
				break;
			}
			
			}
		}
		
		/*for(int i=1; i<R+1; i++) { // 다음 이동을 위해 이동한 상어(작은 상어는 먹힌 뒤 없음)를 맵에서 탐색 후 다시 큐에 삽입
			for(int j=1; j<C+1; j++) {
				if(map[i][j] != null) {
					first_q.offer(map[i][j]);
				}
			}
		}*/

	}
	
	private static void fishing(int column) {
		for(int i=1; i<R+1; i++) {
			if(map[i][column] != null) {
				answer += map[i][column].size;
				map[i][column] = null;
				return;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new Shark [R+1][C+1];
		
		for(int i=0; i<R+1; i++) {
			for(int j=0; j<C+1; j++) {
				map[i][j] = null;
			}
		}
		first_q = new PriorityQueue<>(new Comparator<Shark>() {

			@Override
			public int compare(Shark o1, Shark o2) {
				
				return o1.size - o2.size;
			}
		});
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			map[r][c] = new Shark(r, c, s, d, z);
		}
		
		answer = 0;
		int time = 0;
		while(time < C) {
			time++;
			fishing(time);
			move();
		}
		
		System.out.println(answer);
	}

}
class Shark {
	
	int x;
	int y;
	int v;
	int dir;
	int size;
	
	public Shark(int x, int y, int v, int dir, int size) {
		super();
		this.x = x;
		this.y = y;
		this.v = v;
		this.dir = dir;
		this.size = size;
	}
	
}