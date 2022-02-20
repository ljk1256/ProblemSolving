package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ12851 {
	
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
		
		int N = Integer.parseInt(st.nextToken()); // 수빈이 좌표
		int K = Integer.parseInt(st.nextToken()); // 동생 좌표
		int[] visited = new int [100001];
		
		PriorityQueue<Subin> pq = new PriorityQueue<>(new Comparator<Subin>() {

			@Override
			public int compare(Subin o1, Subin o2) {
				return o1.time - o2.time;
			}
		});
		
		pq.offer(new Subin(N, 0));
		visited[N] = 0;
		
		int cnt = 0; // 경우의 수
		int time = 0; // 최소 시간
		boolean flag = false;
		
		while(!pq.isEmpty()) {
			Subin temp = pq.poll();
			
			if(temp.x == K) { // 동생을 만났다면
				if(!flag) {
					time = temp.time;
					cnt++;
					flag = true;
				}
				else {
					if(temp.time == time) {
						cnt++;
					}
				}
			}
			
			if(temp.x*2 < 100001 && visited[temp.x*2] == 0) { // 순간이동
				pq.offer(new Subin(temp.x*2, temp.time+1));
				visited[temp.x*2] = temp.time+1;
			}
			
			else if(temp.x*2 < 100001 && visited[temp.x*2] != 0) {
				if(temp.time+1 <= visited[temp.x*2]) {
					pq.offer(new Subin(temp.x*2, temp.time+1));
					visited[temp.x*2] = temp.time+1;
				}
			}
			
			if(temp.x+1 < 100001 && visited[temp.x+1] == 0) {
				pq.offer(new Subin(temp.x+1, temp.time+1));
				visited[temp.x+1] = temp.time+1;
			}
			else if(temp.x+1 < 100001 && visited[temp.x+1] != 0) {
				if(temp.time+1 <= visited[temp.x+1]) {
					pq.offer(new Subin(temp.x+1, temp.time+1));
					visited[temp.x+1] = temp.time+1;
				}
			}
			
			if(temp.x-1 > -1 && visited[temp.x-1] == 0) {
				pq.offer(new Subin(temp.x-1, temp.time+1));
				visited[temp.x-1] = temp.time+1;
			}
			
			else if(temp.x-1 > -1 && visited[temp.x-1] != 0) {
				if(temp.time+1 <= visited[temp.x-1]) {
					pq.offer(new Subin(temp.x-1, temp.time+1));
					visited[temp.x-1] = temp.time+1;
				}
			}
		}
		
		System.out.println(time);
		System.out.println(cnt);
	}

}
