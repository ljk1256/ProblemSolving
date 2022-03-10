package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ17837 {
	
	static class Horse {
		
		int x;
		int y;
		int dir;
		
		public Horse(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}
	
	static int N;
	static int K;
	static boolean stop;
	static int[][] colors;
	static int[][] dx = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	static HashMap<Integer, Horse> table;
	static String[][] stack;
	
	private static int reverse(int dir) {
		if(dir == 0) return 1;
		else if(dir == 1) return 0;
		else if(dir == 2) return 3;
		else return 2;
	}
	
	private static boolean isStack() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(stack[i][j] != null && stack[i][j].length() >= 4) return true;
			}
		}
		return false;
	}
	
	private static void play() {
		for(int i=0; i<K; i++) {
			Horse temp = table.get(i);
			int stackIdx = 0;
			int stackLen = stack[temp.x][temp.y].length();
			
			if(stack[temp.x][temp.y] != null) {
				// 현재 말이 몇층에 쌓였는지 확인한다
				for(int j=0; j<stackLen; j++) {
					int number = stack[temp.x][temp.y].charAt(j) - '0';
					if(number == i) {
						stackIdx = j;
						break;
					}
				}
			}
			
			String remain;
			String sequence;
			boolean flag = false;
			
			if(stackLen - stackIdx != 1) { // 내 위에 다른말이 있다면
				flag = true;
				if(stackIdx == 0) remain = null;
				else remain = stack[temp.x][temp.y].substring(0, stackIdx);
				sequence = stack[temp.x][temp.y].substring(stackIdx, stackLen);
			}
			else {
				if(stackIdx == 0) remain = null;
				else remain = stack[temp.x][temp.y].substring(0, stackIdx);
				sequence = stack[temp.x][temp.y].substring(stackIdx, stackLen);
			}
			
			int nx = temp.x + dx[temp.dir][0];
			int ny = temp.y + dx[temp.dir][1];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || colors[nx][ny] == 2) { // 범위를 벗어나거나 파란색일 경우
				temp.dir = reverse(temp.dir); // 방향 전환 뒤
				nx = temp.x + dx[temp.dir][0];
				ny = temp.y + dx[temp.dir][1];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < N && colors[nx][ny] != 2) { // 방향을 전환뒤 위치갱신
					
					if(colors[nx][ny] == 0) { // 흰색일 경우
						if(flag) { // 현재 말 위에 다른말이 있다면 같이 움직이므로 위에 말들의 위치도 갱신한다
							for(int j=stackIdx+1; j<stackLen; j++) {
								int horseNum = stack[temp.x][temp.y].charAt(j) - '0';
								Horse another = table.get(horseNum);
								another.x = nx;
								another.y = ny;
							}
						}
						
						stack[temp.x][temp.y] = remain; // 현재 위치는 이동하고 남은 말들로 갱신
						if(stack[nx][ny] == null) stack[nx][ny] = sequence; // 움직인 곳에 말정보 갱신
						else stack[nx][ny] += sequence; 
						temp.x = nx;
						temp.y = ny;
					}
					
					else { // 빨강일 경우
						if(flag) { // 현재 말 위에 다른말이 있다면 같이 움직이므로 위에 말들의 위치도 갱신한다
							for(int j=stackIdx+1; j<stackLen; j++) {
								int horseNum = stack[temp.x][temp.y].charAt(j) - '0';
								Horse another = table.get(horseNum);
								another.x = nx;
								another.y = ny;
							}
						}
						
						StringBuilder builder = new StringBuilder(); // 뒤집어 준다
						builder.append(sequence);
						builder.reverse();
						
						stack[temp.x][temp.y] = remain;
						if(stack[nx][ny] == null) stack[nx][ny] = builder.toString(); // 움직인 곳에 말정보 갱신
						else stack[nx][ny] += builder.toString(); 
						
						temp.x = nx;
						temp.y = ny;
					}
				}
					
			}
			
			else if(colors[nx][ny] == 0) { // 이동하는 곳이 흰색인 경우
				
				if(flag) { // 현재 말 위에 다른말이 있다면 같이 움직이므로 위에 말들의 위치도 갱신한다
					for(int j=stackIdx+1; j<stackLen; j++) {
						int horseNum = stack[temp.x][temp.y].charAt(j) - '0';
						Horse another = table.get(horseNum);
						another.x = nx;
						another.y = ny;
					}
				}
				
				stack[temp.x][temp.y] = remain; // 현재 위치는 이동하고 남은 말들로 갱신
				if(stack[nx][ny] == null) stack[nx][ny] = sequence; // 움직인 곳에 말정보 갱신
				else stack[nx][ny] += sequence; 
				temp.x = nx;
				temp.y = ny;
			}
			
			else { // 이동하는 곳이 빨강인 경우
				
				if(flag) { // 현재 말 위에 다른말이 있다면 같이 움직이므로 위에 말들의 위치도 갱신한다
					for(int j=stackIdx+1; j<stackLen; j++) {
						int horseNum = stack[temp.x][temp.y].charAt(j) - '0';
						Horse another = table.get(horseNum);
						another.x = nx;
						another.y = ny;
					}
				}
				
				StringBuilder builder = new StringBuilder(); // 뒤집어 준다
				builder.append(sequence);
				builder.reverse();
				
				stack[temp.x][temp.y] = remain;
				if(stack[nx][ny] == null) stack[nx][ny] = builder.toString(); // 움직인 곳에 말정보 갱신
				else stack[nx][ny] += builder.toString(); 
				
				temp.x = nx;
				temp.y = ny;
			}
			
			if(isStack()) {
				stop = true;
				return;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		colors = new int [N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				colors[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		table = new HashMap<>(); // 말의 번호와 말의 정보가 담겨있음
		stack = new String [N][N];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			table.put(i, new Horse(x-1, y-1, dir-1)); // 인덱스 0부터 시작하기때문
		}
		
		for(int i=0; i<K; i++) {
			Horse temp = table.get(i);
			stack[temp.x][temp.y] = String.valueOf(i);
		}
		
		int count = 0;
		stop = false;
		
		while(count <= 1000) { // 1000 보다 큰 건지 같아도 되는지 확인
			count++;
			play();
			if(stop) {
				System.out.println(count);
				System.exit(0);
			}
		}
		
		System.out.println(-1);
	}

}
