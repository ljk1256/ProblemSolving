package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	int dis;
	boolean wall;
	
	public Point(int x, int y, int dis, boolean wall) {
		super();
		this.x = x;
		this.y = y;
		this.dis = dis;
		this.wall = wall;
	}
	
}

public class BJ2206 {
	
	static int N;
	static int M;
	static int distance = Integer.MAX_VALUE;
	static int[] dx = new int [] {-1, 0, 1, 0};
	static int[] dy = new int [] {0, 1, 0, -1};
	static int[][] map;
	static boolean[][][] visited;
	
	private static void bfs() {
		
		Queue<Point> q = new LinkedList<Point>(); // 큐에 map 좌표 배열 넣기 [row좌표, column좌표]
		q.offer(new Point(1, 1, 0, false));
		visited[1][1][0] = true;
		visited[1][1][1] = true; // 혼동을 피하기 위해선 첫 번째 큐 방문 처리는 밖에다 해주는것이 좋음
		
		while(!q.isEmpty()) {      //            
			Point temp = q.poll(); // 
			int x = temp.x;
			int y = temp.y;
			int len = temp.dis;
			boolean visit = temp.wall;
			
			if(x == N && y == M) {
				distance = len + 1;
				return;
			}
			
			for(int i=0; i<4; i++) { // 4방 탐색
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx >= 1 && ny >= 1 && nx < N+1 && ny < M+1) { // [0]과 [1]이 다르기에 각각 다르게 탐색을 시도한다.
					if(visit == false) {
						if(map[nx][ny] == 0 && !visited[nx][ny][0]) {
							q.offer(new Point(nx, ny, len+1, false));  // ++len 하고 len+1 은 자세히 보면 다르다. len++은 len 자체 값을 올리는것, +1은 len은 변하지 않음
							visited[nx][ny][0] = true;
						}
						else if(!visited[nx][ny][0]){
							q.offer(new Point(nx, ny, len+1, true));
							visited[nx][ny][1] = true; 
						}
					}
					
					else {
						if(visit == true && !visited[nx][ny][1]) {
							if(map[nx][ny] == 0) {
								q.offer(new Point(nx, ny, len+1, true));
								visited[nx][ny][1] = true; 
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean [N+1][M+1][2];
		map = new int [N+1][M+1]; // 좌표를 1,1 과 N,M으로 맞추기 위해 +1로 선언
		for(int i=1; i<N+1; i++) {
			String s = br.readLine();
			for(int j=1; j<M+1; j++) {
				map[i][j] = s.charAt(j-1) - '0'; // char형을 int형변환
			}
		}
		bfs();
		if(distance == Integer.MAX_VALUE) { // 모두 탐색 했는데 초기값 Integer.MAX_VALUE 라면 모든 경우에 대해 도달 할 수 없다는 상황이므로 -1 출력
			System.out.println(-1);
		}
		else {
			System.out.println(distance);
		}
	}

}
