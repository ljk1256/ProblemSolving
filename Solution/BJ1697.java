package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1697 {
	
	static int[] dx = {-1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] map = new int [100001];
		Queue<Subin> q = new LinkedList<Subin>();
		q.offer(new Subin(N, 0));
		
		while(!q.isEmpty()) {
			Subin temp = q.poll();
			int x = temp.x;
			int time = temp.time;
			
			if(x == K) {
				System.out.println(time);
				return;
			}
			
			for(int i=0; i<2; i++) {
				int nx = x + dx[i];
				
				if(nx < 0 || nx >= 100001) {
					continue;
				}
				
				if(map[nx] == 0) {
					q.offer(new Subin(nx, time+1));
					map[nx] = time+1;
				}
				else if(map[nx] > time) {
					q.offer(new Subin(nx, time+1));
					map[nx] = time+1;
				}
			}
			
			int nx = x * 2;
			if(nx < 0 || nx >= 100001) {
				continue;
			}
			
			if(map[nx] == 0) {
				q.offer(new Subin(nx, time+1));
				map[nx] = time+1;
			}
			else if(map[nx] > time) {
				q.offer(new Subin(nx, time+1));
				map[nx] = time+1;
			}
		}
	}

}

class Subin {
	
	int x;
	int time;
	
	public Subin(int x, int time) {
		super();
		this.x = x;
		this.time = time;
	}
	
}