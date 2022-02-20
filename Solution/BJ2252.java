package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2252 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] list = new ArrayList [N+1];
		int[] order = new int [N+1];
		
		for(int i=1; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			list[A].add(B);
			order[B]++;
		}
		
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean [N+1];
		
		for(int i=1; i<N+1; i++) {
			if(!visited[i] && order[i] == 0) {
				q.offer(i);
				visited[i] = true;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			sb.append(temp).append(" ");
			
			for(int i=0; i<list[temp].size(); i++) {
				order[list[temp].get(i)]--;
			}
			
			for(int i=1; i<N+1; i++) {
				if(!visited[i] && order[i] == 0) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
