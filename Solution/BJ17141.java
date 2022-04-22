package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17141 {
	
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
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static int[] selected;
	static int mapSize;
	static int virusSize;
	static int answer;
	static ArrayList<Virus> virusList;
	
	private static void comb(int cnt, int idx) {
		if(cnt == virusSize) {
			Queue<Virus> q = new LinkedList<>();
			int[][] visited = new int [mapSize][mapSize];
			
			for(int i=0; i<mapSize; i++) {
				Arrays.fill(visited[i], -1);
			}
			
			for(int i=0; i<virusSize; i++) {
				int selectedVirusIdx = selected[i];
				Virus selectedVirus = virusList.get(selectedVirusIdx);
				
				q.offer(new Virus(selectedVirus.x, selectedVirus.y, selectedVirus.time));
				visited[selectedVirus.x][selectedVirus.y] = selectedVirus.time;
			}
			
			while(!q.isEmpty()) {
				Virus temp = q.poll();
				int x = temp.x;
				int y = temp.y;
				int time = temp.time;
				
				for(int i=0; i<4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(nx < 0 || nx >= mapSize || ny < 0 || ny >= mapSize || visited[nx][ny] != -1) continue;
					
					if(map[nx][ny] == 0) {
						q.offer(new Virus(nx, ny, time+1));
						visited[nx][ny] = time+1;
					}
				}
			}
			
			int totalTime = 0;
			
			for(int i=0; i<mapSize; i++) {
				for(int j=0; j<mapSize; j++) {
					if(visited[i][j] == -1) {
						if(map[i][j] == 0) return;
						else continue;
					}
					totalTime = Math.max(totalTime, visited[i][j]);
				}
			}
			
			answer = Math.min(answer, totalTime);
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
		virusSize = Integer.parseInt(st.nextToken());
		
		map = new int [mapSize][mapSize];
		virusList = new ArrayList<>();
		
		for(int i=0; i<mapSize; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<mapSize; j++) {
				int condition = Integer.parseInt(st.nextToken());
				
				if(condition == 2) {
					virusList.add(new Virus(i, j, 0));
					map[i][j] = 0;
				}
				else map[i][j] = condition;
			}
		}
		
		selected = new int [virusSize];
		answer = Integer.MAX_VALUE;
		
		comb(0, 0);
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

}
