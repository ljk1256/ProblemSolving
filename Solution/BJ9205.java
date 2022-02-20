package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ9205 {
	
	static int beer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) { // 테스트 케이스 만큼 반복
			int N = Integer.parseInt(br.readLine()); 
			
			st = new StringTokenizer(br.readLine()); // 상근이 집 좌표
			Songdo sang = new Songdo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			ArrayList<Songdo> list = new ArrayList<>(); // 편의점 좌표 저장
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Songdo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			st = new StringTokenizer(br.readLine()); // 페스티벌 좌표
			Songdo festival = new Songdo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			Queue<Songdo> q = new LinkedList<Songdo>();
			q.offer(sang);
			beer = 20; // 집에서 출발 할 때 맥주 20병 채우고 출발
			boolean flag = false; // 결과 입출력을 위함
			boolean[] visited = new boolean [N];  // 편의점 방문 체크를 위함
			
			while(!q.isEmpty()) {
				Songdo temp = q.poll();  // 초기 값은 집에서 출발이겠지만, 경유한다면 q에서 뽑은 좌표는 편의점 좌표가 됨
				
				if(Math.abs(temp.x - festival.x) + Math.abs(temp.y - festival.y) <= beer * 50) {  // 현 시점에서 페스티벌에 도착 할 수 있는지 확인
					flag = true;
					break;
				}
				
				for(int j=0; j<list.size(); j++) { // 페스티벌에 못간다면 편의점 경유 하기위해 편의점 탐색 (만약 시간이 부족하다면 한번 탐색한 편의점 지우기도 고려해봐야함)
					Songdo con = list.get(j);
					
					if(Math.abs(temp.x - con.x) + Math.abs(temp.y - con.y) <= beer * 50 && !visited[j]) {
						q.offer(new Songdo(con.x, con.y));
						visited[j] = true;
					}
				}
			}
			
			if(flag) { // 도착했다면 true, 아니면 false
				System.out.println("happy");
			}
			else
				System.out.println("sad");
		}
	}

}

class Songdo {
	int x;
	int y;
	
	public Songdo(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
