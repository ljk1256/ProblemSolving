package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ3860ing {
	
	static class Hole {
		
		int w;
		int h;
		int time;
		
		public Hole(int w, int h, int time) {
			super();
			this.w = w;
			this.h = h;
			this.time = time;
		}
		
	}
	
	static StringBuilder sb;
	static int result;
	static int W;
	static int H;
	static HashMap<Integer, Hole> hm;
	static int[][] map;
	static int[] dw = {-1, 1, 0, 0};
	static int[] dh = {0, 0, -1, 1};
	
	private static void escape() {
		PriorityQueue<Hole> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.time, o2.time));
		int[][] visited = new int [H][W];
		pq.offer(new Hole(0, 0, 0));
		visited[0][0] = 1;
		
		while(!pq.isEmpty()) {
			Hole temp = pq.poll();
			int w = temp.w;
			int h = temp.h;
			int time = temp.time;
			
			if(map[h][w] != 0 && map[h][w] != 1000) { // 잔디도 아니고 묘비로 아니라면 >> 귀신구멍 이라면
				int idx = map[h][w];
				Hole jump = hm.get(idx); // 빠져나올 위치
				
				if(time + jump.time <= 0) { // 틀렸다면 이 부분 수정필요
					sb.append("Never").append("\n");
					return;
				}
				
				if(visited[jump.h][jump.w] != 0) {
					if(visited[jump.h][jump.w] > time + jump.time) {
						pq.offer(new Hole(jump.w, jump.h, time + jump.time));
						visited[jump.h][jump.w] = time + jump.time;
						continue;
					}
				}
				
				pq.offer(new Hole(jump.w, jump.h, time + jump.time));
				visited[jump.h][jump.w] = time + jump.time;
				continue;
			}
			
			if(w == W-1 && h == H-1) {
				sb.append(time).append("\n");
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nh = h + dh[i];
				int nw = w + dw[i];
				
				if(nh < 0 || nh >= H || nw < 0 || nw >= W) continue;
				
				if(map[nh][nw] != 1000) {
					if(visited[nh][nw] == 0) { // 방문 안한곳 이라면
						pq.offer(new Hole(nw, nh, time+1));
						visited[nh][nw] = time+1;
					}
					
					else { // 이미 방문 했던 곳이라면
						if(visited[nh][nw] > time) {
							pq.offer(new Hole(nw, nh, time+1));
							visited[nh][nw] = time+1;
						}
					}
				}
			}
		}
		
		if(result == Integer.MAX_VALUE) { // 도달하지 못했다면
			sb.append("Impossible").append("\n");
			return;
		}
	}
 
	public static void main(String[] args) throws IOException { // 간선정보를 만들어서 벨만포드로 먼저 음의 순환이 있는지도 확인이 필요함
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken()); // 가로
		H = Integer.parseInt(st.nextToken()); // 세로
		
		sb = new StringBuilder();
		result = Integer.MAX_VALUE;
	cp: while(W != 0 && H != 0) {
			
			map = new int [H][W]; // 맵 정보 저장 0은 잔디
			
			int G = Integer.parseInt(br.readLine());
			for(int i=0; i<G; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				
				map[Y][X] = 1000; // 묘비는 1000로 설정
			}
			
			int E = Integer.parseInt(br.readLine());
			int holecount = 0;
			boolean flag = false;
			if(E != 0) {
				hm = new HashMap<>(); // 귀신구멍 저장
				
				for(int i=0; i<E; i++) {
					holecount++;
					st = new StringTokenizer(br.readLine());
					int X1 = Integer.parseInt(st.nextToken());
					int Y1 = Integer.parseInt(st.nextToken());
					int X2 = Integer.parseInt(st.nextToken());
					int Y2 = Integer.parseInt(st.nextToken());
					int T = Integer.parseInt(st.nextToken());
					if(T < 0) {
						flag = true;
						sb.append("Never").append("\n");
					}
					
					map[Y1][X1] = holecount;
					hm.put(holecount, new Hole(X2, Y2, T));
				}
			}
			
			if(flag) {
				st = new StringTokenizer(br.readLine()); // 다음 루프문을 위해 서 마지막에 가로 세로 갱신
				W = Integer.parseInt(st.nextToken());
				H = Integer.parseInt(st.nextToken());
				continue cp;
			}
			
			escape();
			
			st = new StringTokenizer(br.readLine()); // 다음 루프문을 위해 서 마지막에 가로 세로 갱신
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
