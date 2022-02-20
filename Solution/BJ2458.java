package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2458 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<ArrayList<Integer>> listup = new ArrayList<ArrayList<Integer>>();
		List<ArrayList<Integer>> listdown = new ArrayList<ArrayList<Integer>>();
		int cnt = 0;
		
		for(int i=0; i<N+1; i++) {
			listup.add(new ArrayList<Integer>());
			listdown.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			listup.get(start).add(end);
			listdown.get(end).add(start);
		}
		
		for(int i=1; i<=N; i++) {
			int sum = 0;
			Queue<Integer> q = new LinkedList<Integer>();
			boolean[] visited = new boolean [N+1];
			q.offer(i);
			visited[i] = true;
			
			while(!q.isEmpty()) {
				int temp = q.poll();
				
				for(int j=0; j<listup.get(temp).size(); j++) {
					if(!visited[listup.get(temp).get(j)]) {
						q.offer(listup.get(temp).get(j));
						sum++;
						visited[listup.get(temp).get(j)] = true;
					}
				}
			}
			
			visited = new boolean [N+1];
			q.offer(i);
			visited[i] = true;
			
			while(!q.isEmpty()) {
				int temp = q.poll();
				
				for(int j=0; j<listdown.get(temp).size(); j++) {
					if(!visited[listdown.get(temp).get(j)]) {
						q.offer(listdown.get(temp).get(j));
						sum++;
						visited[listdown.get(temp).get(j)] = true;
					}
				}
			}
			
			if(sum == N-1) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
