package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ13549 {
	
	static int[] dx = {-1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] map = new int [100001];
		Arrays.fill(map, -1);
		Deque<Subin> q = new LinkedList<Subin>();
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
				int rx = x + dx[i];
				
				if(rx < 0 || rx >= 100001) {
					continue;
				}
				
				if(map[rx] == -1) {
					q.offerLast(new Subin(rx, time+1));
					map[rx] = time+1;
				}
				else if(map[rx] == 0 || map[rx] > time) {
					q.offerLast(new Subin(rx, time+1));
					map[rx] = time+1;
				}
			}
			
			int nx = x * 2;
			if(nx >= 100001) {
				continue;
			}
			
			if(map[nx] == -1) {
				q.offerFirst(new Subin(nx, time));
				map[nx] = time;
			}
			else if(map[nx] > time) {
				q.offerFirst(new Subin(nx, time));
				map[nx] = time;
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
