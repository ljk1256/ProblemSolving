package Solution;

import java.util.*;
import java.io.*;

public class BJ17142 {
	
	static class Virus {
		
		int x;
		int y;
		int time;
		
		public Virus(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
	}
	
	static int virusCnt;
	static int emptyCnt;
	static int mapSize;
	static int minAns;
	static int[][] map;
	static boolean[][] originVisit;
	static boolean[][] copyVisit;
	static int[] selected;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static ArrayList<Virus> virusList;
	
	private static void comb(int cnt, int idx) {
		if(cnt == virusCnt) {
			
			for(int i=0; i<mapSize; i++) { // 방문체크 배열 초기화
				System.arraycopy(originVisit[i], 0, copyVisit[i], 0, mapSize);
			}
			
			int[][] timeDp = new int [mapSize][mapSize];
			Queue<Virus> q = new LinkedList<>(); // bfs 큐
			
			for(int i=0; i<virusCnt; i++) { // 바이러스 배치
				Virus selectVirus = virusList.get(selected[i]);
				copyVisit[selectVirus.x][selectVirus.y] = true;
				q.offer(selectVirus);
			}
			
			int tempEmpty = 0;
			boolean flag = false;
			
			while(!q.isEmpty()) {
				
				Virus temp = q.poll();
				int x = temp.x;
				int y = temp.y;
				int time = temp.time;
				
				for(int j=0; j<4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
				
					if(nx < 0 || nx >= mapSize || ny < 0 || ny >= mapSize || copyVisit[nx][ny]) continue;
					
					if(map[nx][ny] != 2) {
						q.offer(new Virus(nx, ny, time+1));
						copyVisit[nx][ny] = true;
						timeDp[nx][ny] = time+1;
						tempEmpty++;
					}
					else if(map[nx][ny] == 2){
						q.offer(new Virus(nx, ny, time+1));
						copyVisit[nx][ny] = true;
					}
				}
				
				if(tempEmpty == emptyCnt) {
					flag = true;
					break;
				}
			}
			
			if(flag) {
				int maxTime = 0;
				
				for(int i=0; i<mapSize; i++) {
					for(int j=0; j<mapSize; j++) {
						if(maxTime < timeDp[i][j]) maxTime = timeDp[i][j];
					}
				}
				
				minAns = Math.min(minAns, maxTime);
				return;	
			}
			
			return;
		}
		
		for(int i=idx; i<virusList.size(); i++) {
			selected[cnt] = i;
			comb(cnt+1, i+1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		mapSize = Integer.parseInt(st.nextToken());
		virusCnt = Integer.parseInt(st.nextToken());
		
		map = new int [mapSize][mapSize];
		originVisit = new boolean [mapSize][mapSize];
		copyVisit = new boolean [mapSize][mapSize];
		virusList = new ArrayList<>();
		emptyCnt = 0;
		
		for(int i=0; i<mapSize; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<mapSize; j++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(num == 2) virusList.add(new Virus(i, j, 0));
				else if(num == 1) originVisit[i][j] = true;
				else emptyCnt++;
				
				map[i][j] = num;
			}
		}
		
		minAns = Integer.MAX_VALUE;
		selected = new int [virusCnt];
		comb(0, 0);
		
		System.out.println(minAns == Integer.MAX_VALUE ? -1 : minAns);
	}

}
