package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ11779 {
	
	static class Node {
		
		int start;
		int end;
		int cost;
		
		public Node(int start, int end, int cost) {
			super();
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		
	}
	
	static final int INF = 100000000;
	static ArrayList<Node>[] list;
	static StringBuilder sb;
	static int[] track;
	static int n;
	
	private static void Dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		int[] distance = new int [n+1];
		Arrays.fill(distance, INF);
		distance[start] = 0;
		
		pq.offer(new Node(0, start, 0));
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(distance[now.end] < now.cost) continue;
			
			for(int i=0; i<list[now.end].size(); i++) {
				Node next = list[now.end].get(i);
				
				if(distance[next.end] > distance[next.start] + next.cost) {
					distance[next.end] = distance[next.start] + next.cost;
					track[next.end] = next.start;
					pq.offer(new Node(next.start, next.end, next.cost));
				}
			}
		}
		
		sb.append(distance[end]).append("\n");
	}
	
	private static void Tracking(int start, int end, int count) {
		if(track[end] == 0 || track[end] == start) {
			sb.append(count).append("\n");
			sb.append(start).append(" ").append(end);
			return;
		}
		
		int cnt = count + 1;
		StringBuilder builder = new StringBuilder();
		cnt += Recursion(start, track[end], builder);
		builder.append(track[end]).append(" ");
		cnt += Recursion(track[end], end, builder);
		sb.append(cnt).append("\n");
		sb.append(start).append(" ").append(builder.toString()).append(end);
	}
	
	private static int Recursion(int start, int end, StringBuilder builder) {
		if(track[end] == 0 || track[end] == start) return 0;
		
		int sum = 1;
		sum += Recursion(start, track[end], builder);
		builder.append(track[end]).append(" ");
		sum += Recursion(track[end], end, builder);
		return sum;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine()); // 도시 수
		int m = Integer.parseInt(br.readLine()); // 버스 수
		
		list = new ArrayList [n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list[start].add(new Node(start, end, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		int startpoint = Integer.parseInt(st.nextToken());
		int endpoint = Integer.parseInt(st.nextToken());
	
		track = new int [n+1];
		sb = new StringBuilder();
		Dijkstra(startpoint, endpoint);
		Tracking(startpoint, endpoint, 2); // 경로 추적은 stack의 자료구조의 방식과 같다 >> 스택을 활용해도 된다 >> 노드에 갱신된 경로(경유지)를 부모노드로 연결지어 놓고 스택에 넣는방식
		
		System.out.println(sb.toString());
	}

}
