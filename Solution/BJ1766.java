package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1766 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] orders = new int [N+1];
		ArrayList<Integer>[] list = new ArrayList [N+1];
		boolean[] visited = new boolean [N+1];
		
		for(int i=1; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			list[A].add(B);
			orders[B]++;
		}
		
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; i<N+1; i++) {
			if(!visited[i] && orders[i] == 0) {
				q.offer(i);
				visited[i] = true;
				break;
			}
		}
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			sb.append(temp).append(" ");
			
			for(int i=0; i<list[temp].size(); i++) {
				orders[list[temp].get(i)]--;
			}
			
			for(int i=1; i<N+1; i++) {
				if(!visited[i] && orders[i] == 0) {
					q.offer(i);
					visited[i] = true;
					break;
				}
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
