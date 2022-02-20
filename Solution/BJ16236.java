package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16236 {
	
	static int[][] map;
	static int[][] dis;
	static boolean[][] visited;
	static boolean flag = true;
	static int baby = 2;
	static int eatcount = 0;
	static int time = 0;
	static int[] point = new int [2];
	static List<int[]> list;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	
	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(point);
		visited = new boolean [map.length][map.length];
		dis = new int [map.length][map.length];
		list = new ArrayList<int[]>();
		flag = false;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			visited[x][y] = true;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx >= 0 && ny >=0 && nx < map.length && ny < map.length && !visited[nx][ny] && map[nx][ny] <= baby) {
					dis[nx][ny] = dis[x][y] + 1;
					
					if(map[nx][ny] != 0 && map[nx][ny] < baby) {
						list.add(new int[] {nx, ny, dis[nx][ny]});
					}
					
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
		
		if(list.size() == 1) {
			flag = true;
			eat();
		}
		
		if(list.size() > 1) {
			flag = true;
			Collections.sort(list, new Comparator<int[]>() { // 문제 조건에 만족하도록 정렬 0번 인덱스에 들어간 배열[]이 상어가 먹을 물고기

				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[2] == o2[2]) { // 최소거리가 같을 경우
						
						if(o1[0] != o2[0]) {
							return o1[0] - o2[0]; // 더 위에 있는 물고기 부터
						}
						else {
							if(o1[1] != o2[1]) {
								return o1[1] - o2[1];  // 위 까지 같을경우 왼쪽에 있는 물고기
							}
						}
					}
					return o1[2] - o2[2];
				}
			});
			eat();
		}
	}
	
	private static void eat() {
		int[] temp = list.get(0);
		map[point[0]][point[1]] = 0; // 상어가 머문자리 0 으로 갱신
		
		point[0] = temp[0];
		point[1] = temp[1]; // 상어 x, y 좌표 갱신
		eatcount++;
		time += temp[2]; // 시간 = 이동거리 (이유 : 1초에 1칸 이동하기 때문)
		map[point[0]][point[1]] = 9; // 물고기 먹고 도착한 자리 상어 위치로 9 갱신
		
		if(eatcount == baby) { // 상어가 자기 숫자만큼 물고기를 먹을경우 상어 레벨업, 먹은 물고기수 초기화
			baby++;
			eatcount = 0;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		map = new int [N][N];  // 맵 초기화
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 9) { // 아기 상어 초기 위치 저장
					point[0] = i;
					point[1] = j;
				}
			}
		}
		while(flag) {
			bfs();
		}
		System.out.println(time);
	}

}
