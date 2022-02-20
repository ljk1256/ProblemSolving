package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ5972 {
	
	static class Node {
		
		int end;
		int cost;
		
		public Node(int end, int cost) {
			super();
			this.end = end;
			this.cost = cost;
		}
		
	}
	
	static final int INF = 100000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] list = new ArrayList[N];
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			list[A-1].add(new Node(B-1, C));
			list[B-1].add(new Node(A-1, C));
		}
		
		int[] distance = new int [N];
 		Arrays.fill(distance, INF);
		distance[0] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		pq.offer(new Node(0, 0));
		
		while(!pq.isEmpty()) {
			int now = pq.poll().end;
			
			for(int i=0; i<list[now].size(); i++) {
				Node next = list[now].get(i);
				
				if(distance[next.end] > distance[now] + next.cost) {
					distance[next.end] = distance[now] + next.cost;
					pq.offer(new Node(next.end, next.cost));
				}
			}
		}
		
		System.out.println(distance[N-1]);
	}

}
