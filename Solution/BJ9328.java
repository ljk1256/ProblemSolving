package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ9328 {
	
	static char[][] map;
	static int h;
	static int w;
	static int answer;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static boolean[][] visited;
	static boolean[] key;
	static ArrayList<Sang>[] list;
	static Queue<Sang> q;
	
	private static void bfs() { // 테두리를 만들어 어디서든 접근 가능하도록 구현, 탐색을 열쇠를 얻는다면 못열었던 문으로 점프 하도록 구현, 문을 열었다면 다닐 수 있는 길로 수정하여 탐색시도
		                        // 거리가 필요한 탐색이었다면 재탐색이 필요하다. 하지만 문서 갯수만 찾는거기에 재탐색은 필요없다.
		q.offer(new Sang(0, 0));
		
		while(!q.isEmpty()) {
			Sang temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			visited[x][y] = true;
			
			if(map[x][y] == '$') { // 문서를 찾을 경우
				answer++;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= h+2 || ny < 0 || ny >= w+2 || visited[nx][ny] || map[nx][ny] == '*') { // 범위를 벗어나거나 벽을 발견할 경우
					continue;
				}
				
				if(map[nx][ny] >= 'a' && map[nx][ny] <= 'z') { // 키를 찾을 경우
					key[map[nx][ny] - 'a'] = true;
					q.offer(new Sang(nx, ny)); 
					visited[nx][ny] = true;
				}
				else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') { // 문을 발견 할 경우
					if(key[map[nx][ny] - 'A']) { // 키가 있을경우
						map[nx][ny] = '.'; // 문을 열고
						q.offer(new Sang(nx, ny)); // 탐색 시작
						visited[nx][ny] = true;
					}
					else { // 아직은 키가 없지만 키가 생기면 방문하기 위해 리스트에 추가
						list[map[nx][ny] - 'A'].add(new Sang(nx, ny));
					}
				}
				else { // 일반 탐색의 경우
					q.offer(new Sang(nx, ny));
					visited[nx][ny] = true;
				}
				
				for(int k=0; k<26; k++) { // ** 한 방향 끝날 때 마다 새로운 키를 먹었는지 확인해야 그때 그때 상황이 바뀌는걸 알수있다.
					if(key[k] && list[k].size() != 0) { // 효율적인 코드는 열쇠를 먹을때만 해당일을 수행하는 것이 좋다.
						for(int j=0; j<list[k].size(); j++) {
							Sang tmp = list[k].get(j);
							map[tmp.x][tmp.y] = '.';
							q.offer(new Sang(tmp.x, tmp.y));
							visited[tmp.x][tmp.y] = true;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			map = new char [h+2][w+2]; // 어디에서든 출입 가능하도록 테두리를 만들어줌
			visited = new boolean [h+2][w+2];
			q = new LinkedList<Sang>();
			list = new ArrayList [26];
			
			for(int j=0; j<26; j++) { // 초기화
				list[j] = new ArrayList<>();
			}
			
			for(int j=0; j<h+2; j++) { // 테두리에서는 접근 가능 하도록 일단 모든 공간 . 으로 초기화
				for(int k=0; k<w+2; k++) {
					map[j][k] = '.';
				}
			}
			
			for(int j=1; j<=h; j++) {
				String s = br.readLine();
				for(int k=1; k<=w; k++) {
					map[j][k] = s.charAt(k-1);
					
				}
			}
			
			String s = br.readLine();
			key = new boolean [26];
			
			if(!s.equals("0")) { // 초기 키 입력
				char[] c = s.toCharArray();
				for(int j=0; j<c.length; j++) {
					key[c[j] - 'a'] = true; // 0번 인덱스 a ~ 25번 인덱스 z 
				}
			}
			
			answer = 0;
			bfs();
			System.out.println(answer);
		}
	}

}
class Sang {
	
	int x;
	int y;
	
	public Sang(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
