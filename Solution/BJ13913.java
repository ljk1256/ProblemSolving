package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ13913 {
	// 경로를 저장할때 객체마다 경로를 갖고있는건 비효율적일 수 있다 >> 최선의 선택만 필요하다면 공통된 공간에서 최선의 값만 갱신하는 방법을 선택한다
	static class Subin {
		
		int x;
		int time;
		
		public Subin(int x, int time) {
			super();
			this.x = x;
			this.time = time;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()); // 수빈이 좌표
		int K = Integer.parseInt(st.nextToken()); // 동생 좌표
		
		int[] visited = new int [100001];
		int[] record = new int [100001];
		Arrays.fill(visited, -1);
		PriorityQueue<Subin> pq = new PriorityQueue<>(new Comparator<Subin>() {

			@Override
			public int compare(Subin o1, Subin o2) {
				return o1.time - o2.time;
			}
		});
		
		pq.offer(new Subin(N, 0));
		int time = 0; // 가장 빠른 시간
		
		while(!pq.isEmpty()) {
			Subin temp = pq.poll();
			
			if(temp.x == K) { // 동생을 만났다면
				time = temp.time;
				break;
			}
			
			int next = 0;
			for(int i=0; i<3; i++) {
				if(i == 0) next = temp.x - 1 ;
				else if(i == 1) next = temp.x + 1;
				else next = temp.x * 2;
				
				if(next < 0 || next > 100000) continue; // 범위를 벗어난다면
				
				if(visited[next] == -1) { // 처음 방문 하는 곳이라면
					pq.offer(new Subin(next, temp.time+1));
					visited[next] = temp.time + 1;
					record[next] = temp.x; // 이전경로 저장
				}
				
				else if(temp.time + 1 <= visited[next]) { // 이미 방문한곳의 시간이 지금 시간보다 큰 경우
					pq.offer(new Subin(next, temp.time+1));
					visited[next] = temp.time + 1;
					record[next] = temp.x;
				}
				
			}
		}
		
		sb.append(time).append("\n");
		int idx = K;
		Stack<Integer> s = new Stack<>();
		while(idx != N) { // 경로 역추적 : 동생에서 수빈이로 갈때까지
			s.push(idx);
			idx = record[idx];
		}
		
		s.push(N);
		while(!s.isEmpty()) {
			sb.append(s.pop()).append(" ");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
