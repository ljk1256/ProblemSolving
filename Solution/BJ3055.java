package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Move {
	int x;
	int y;
	
	public Move(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class BJ3055 {
	
	static int R;
	static int C;
	static int time = 0;
	static boolean flag;
	static Move beaver;
	static Move gosum;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	static char[][] forest;
	static boolean[][] wvisited;
	static boolean[][] mvisited;
	static Queue<Move> waterq = new LinkedList<Move>();
	static Queue<Move> moveq = new LinkedList<Move>();
	
	private static void move() { 
		int mcnt = moveq.size();
		for(int i=0; i<mcnt; i++) {
			Move mtemp = moveq.poll();
			int x = mtemp.x;
			int y = mtemp.y;
			
			if(forest[x][y] == '*') {
				continue;
			}
			
			if(x == beaver.x && y == beaver.y) {
				flag = false;
				return;
			}
			
			for(int j=0; j<4; j++) {
				int mx = x + dx[j];
				int my = y + dy[j];
				
				if(mx >= 0 && my >= 0 && mx < R && my < C) {
					if(forest[mx][my] != '*' && forest[mx][my] != 'X' && !mvisited[mx][my]) {
						if(forest[mx][my] == 'D') {
							moveq.offer(new Move(mx, my));
							mvisited[mx][my] = true;
						}
						else {
							forest[mx][my] = 'S';
							moveq.offer(new Move(mx, my));
							mvisited[mx][my] = true;
						}
					}
				}
			}
		}
		if(moveq.isEmpty()) {
			time = -1;
			flag = false;
			return;
		}
		time = time + 1;
	}
	
	private static void water() {
		int wcnt = waterq.size();
		for(int i=0; i<wcnt; i++) {
			Move wtemp = waterq.poll();
			int x = wtemp.x;
			int y = wtemp.y;
			
			for(int j=0; j<4; j++) {
				int wx = x + dx[j];
				int wy = y + dy[j];
				
				if(wx >= 0 && wy >= 0 && wx < R && wy < C) {
					if(forest[wx][wy] != 'D' && forest[wx][wy] != 'X' && !wvisited[wx][wy]) {
						forest[wx][wy] = '*';
						waterq.offer(new Move(wx, wy));
						wvisited[wx][wy] = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		forest = new char [R][C];
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				forest[i][j] = s.charAt(j);
				if(forest[i][j] == 'D') {
					beaver = new Move(i, j);
				}
				if(forest[i][j] == 'S') {
					gosum = new Move(i, j);
					moveq.offer(gosum);
				}
				if(forest[i][j] == '*') {
					waterq.offer(new Move(i, j));
				}
			}
		}
		
		wvisited = new boolean [R][C];
		mvisited = new boolean [R][C];
		flag = true;
		while(flag) {
			move();
			water();
		}
		
		if(time == -1) {
			System.out.println("KAKTUS");
		}
		else {
			System.out.println(time);
		}
	}

}
