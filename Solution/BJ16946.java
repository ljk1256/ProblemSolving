package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16946 {
	
	static class Wall {
		
		int x;
		int y;
		
		public Wall(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int row = Integer.parseInt(st.nextToken());
		int column = Integer.parseInt(st.nextToken());
		
		int[][] map = new int [row][column]; // 입력받은 맵
		int[][] group = new int [row][column]; // 결과 저장
		
		List<Wall> list = new LinkedList<>();
		for(int i=0; i<row; i++) {
			String s = br.readLine();
			for(int j=0; j<column; j++) {
				map[i][j] = s.charAt(j) - '0';
				
				if(map[i][j] == 1) {
					list.add(new Wall(i, j));
				}
			}
		}
		
		HashMap<Integer, Integer> groupmap = new HashMap<>(); // key : value = 그룹번호 : 그룹사이즈
		int groupidx = 1;
		boolean[][] visited = new boolean [row][column]; // bfs 돌때마다 배열의 선언은 O(row * column) 의 시간복잡도로 최악의 경우 O(row^2 * column^2) 가 걸린다
		for(int i=0; i<row; i++) {
			for(int j=0; j<column; j++) {
				if(map[i][j] == 0 && !visited[i][j]) {
					int count = 1; // 자기 자리는 1포함
					Queue<int[]> q = new LinkedList<>();
					q.offer(new int [] {i, j});
					group[i][j] = groupidx;
					visited[i][j] = true;
					
					while(!q.isEmpty()) {
						int[] temp = q.poll();
						int x = temp[0]; // x좌표
						int y = temp[1]; // y좌표
						
						for(int k=0; k<4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];
							
							if(nx < 0 || nx >= row || ny < 0 || ny >= column || visited[nx][ny]) continue;
							
							if(map[nx][ny] == 0) {
								q.offer(new int[] {nx, ny});
								group[nx][ny] = groupidx;
								visited[nx][ny] = true;
								count++;
							}
						}
					}
					
					groupmap.put(groupidx, count);
					groupidx++;
				}
				// else 문을 넣었는데 else는 if가 아닐경우 반드시 들어오는 거니 실수 할 수 있다.
			}
		}
		
		
		int[][] newmap = new int [row][column];
		for(Wall w : list) {
			int x = w.x;
			int y = w.y;
			
			HashSet<Integer> groupset = new HashSet<>();
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= row || ny < 0 || ny >= column || group[nx][ny] == 0) continue;
				
				groupset.add(group[nx][ny]);
			}
			
			newmap[x][y] = 1;
			for(int i : groupset) {
				newmap[x][y] += groupmap.get(i);
			}
			newmap[x][y] = newmap[x][y] % 10;
		}
			
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<row; i++) {
			for(int j=0; j<column; j++) {
				bw.write(Integer.toString(newmap[i][j]));
			}
			bw.write("\n");
		}
		
		bw.flush();
	}

}
