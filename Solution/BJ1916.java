package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1916 {
	
	static class Node {
		
		int end;
		int cost;
		
		public Node(int end, int cost) {
			super();
			this.end = end;
			this.cost = cost;
		}
		
	}
	
	static final int INF = 1000000000;
	static ArrayList<Node>[] list;
	static int N;
	
	private static int dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		int[] mincosts = new int [N+1];
		Arrays.fill(mincosts, INF);
		
		pq.offer(new Node(start, 0));
		mincosts[start] = 0;
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			int now = temp.end;
			
			if(mincosts[now] < temp.cost) continue; // 우선순위큐가 더 빠른 시간복잡도를 가지지만 그것은 모든 정점을 최소횟수로만 방문했을시 >> 방문처리를 하거나, 이미 방문한 정점중에서 더 큰값이 있다면 재 탐색을 하지말아야한다
			
			for(int i=0; i<list[now].size(); i++) {
				Node next = list[now].get(i);
				
				if(mincosts[next.end] > mincosts[now] + next.cost) {
					mincosts[next.end] = mincosts[now] + next.cost;
					pq.offer(new Node(next.end, mincosts[next.end]));
				}
			}
		}
		
		return mincosts[end];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		list = new ArrayList [N+1];
		for(int i=1; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list[start].add(new Node(end, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		System.out.println(dijkstra(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
	}

}
