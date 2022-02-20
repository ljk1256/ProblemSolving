package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1753 {
	
	 static class Node {
		 
		 int end;
		 int weight;
		 
		public Node(int end, int weight) {
			super();
			this.end = end;
			this.weight = weight;
		}
		 
	 }
	 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점 갯수
		int E = Integer.parseInt(st.nextToken()); // 간선 갯수
		int start = Integer.parseInt(br.readLine()); // 시작점
		
		ArrayList<Node>[] list = new ArrayList [V+1];
		for(int i=0; i<V+1; i++) { // 배열 초기화
			list[i] = new ArrayList<Node>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[u].add(new Node(v, w));
		}
		
		int[] min = new int [V+1]; // 최단거리 저장
		boolean[] visited = new boolean [V+1];
		int INF = Integer.MAX_VALUE;
		for(int i=0; i<V+1; i++) { // 최단거리배열 초기화
			min[i] = INF;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() { // 우선순위큐를 이용한 bfs = 다익스트라 알고리즘 

			@Override
			public int compare(Node o1, Node o2) {
				return o1.weight - o2.weight;
			}
		});
		
		pq.offer(new Node(start, 0)); // 시작 정점 넣어주기
		min[start] = 0; // 자기 자신은 0으로 만듬
		
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			
			if(!visited[temp.end]) {
				visited[temp.end] = true;  // 경유지로 고려됐으니 방문처리
			}
			
			for(int i=0; i<list[temp.end].size(); i++) { // 시작 정점과 연결된 정점들 큐에 넣기
				Node node = list[temp.end].get(i);
				if(min[node.end] > min[temp.end] + node.weight) {
					min[node.end] = min[temp.end] + node.weight;
					pq.offer(new Node(node.end, min[node.end]));
				}
			}
			
		}
		
		for(int i=1; i<=V; i++) {
			if(min[i] == INF) {
				System.out.println("INF");
			}
			else {
				System.out.println(min[i]);
			}
		}
		
	}
}


