package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2842 {
	
	static class Postman {
		
		int x;
		int y;
		
		public Postman(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static int N;
	static int total_house;
	static Postman init;
	static char[][] map;
	static int[][] high;
	static int[] setlist;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	static boolean Delivery(int left, int right) {
		Queue<Postman> q = new LinkedList<>();
		boolean[][] visited = new boolean [N][N];
		int cnt = 0;
		
		q.offer(init);
		visited[init.x][init.y] = true;
		
		while(!q.isEmpty()) {
			Postman temp = q.poll();
			
			if(map[temp.x][temp.y] == 'K') { // 배달해야할 집에 도착했다면
				cnt++;
			}
			
			for(int i=0; i<8; i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue; // 범위를 지나거나 방문 했다면
				
				if(setlist[left] <= high[nx][ny] && high[nx][ny] <= setlist[right]) { // 고도 범위 내에 있다면
					q.offer(new Postman(nx, ny)); // 큐에 넣고 다음 경로 탐색
					visited[nx][ny] = true;
				}
			}
		}
		
		if(cnt == total_house) { // 모든 집에 배달을 완료 했다면
			return true;
		}
		else return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new char [N][N];
		high = new int [N][N];
		total_house = 0;
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		HashSet<Integer> set = new HashSet<>(); // 중복되지 않게 고도 저장
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = s.charAt(j);
				
				if(map[i][j] == 'P') { // 우체국 위치 저장
					init = new Postman(i, j);
				}
				else if(map[i][j] == 'K') { // 배달해야하는 집
					total_house++;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				high[i][j] = Integer.parseInt(st.nextToken());
				set.add(high[i][j]);
				
				if(map[i][j] != '.') { // 반드시 가야 하는 곳 이라면
					if(high[i][j] < min) min = high[i][j]; // 최대, 최소 값 저장
					if(high[i][j] > max) max = high[i][j];
				}
			} // 투 포인터는 범위 설정이 중요    [최대 범위      [ 최소 범위  ]       ]
		}     //                          << left   right >>
		
		int idx = 0;
		setlist = new int [set.size()];
		for(int temp : set) { // set은 값을 얻어올때 iterator가 필요하기에 조회만 필요하다면 foreach를 쓴다
			setlist[idx++] = temp;
		}
		
		Arrays.sort(setlist); // 오름차순 정렬
		int left = 0, right = 0; // 투 포인터 사용
		for(int i=0; i<setlist.length; i++) {
			if(setlist[i] == min) min = i; // left는 인덱스 0부터 시작 하고 min을 넘어 갈 수 없다 >> 반드시 거쳐야하는 곳중에 최소 높이이기때문
			if(setlist[i] == max) right = i;
		}
		
		int answer = Integer.MAX_VALUE;
		while(left <= min && right < setlist.length && left <= right) { // 두개 포인터가 범위를 벗어나지 않으면서 겹칠때까지 수행
			if(Delivery(left, right)) { // 해당 고도 범위에서 배달이 완료했다면
				answer = Math.min(answer, setlist[right] - setlist[left]); // 피로도 갱신
				left++; // 범위를 좁혀가며 탐색한다
			}
			else { // 해당 범위가 안된다면 범위를 늘린다
				right++;
			}
		}
		
		System.out.println(answer);
	}

}
