package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1504_ver02 {
	
	static class Node {
		
		int end;
		int cost;
		
		public Node(int end, int cost) {
			super();
			this.end = end;
			this.cost = cost;
		}
		
	}
	
	static ArrayList<Node>[] list;
	static int[] distance;
	static final int INF = 100000000;
	static int N;
	static boolean check1;
	static boolean check2;
	
	static void dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));  // 람다식
		
		distance = new int [N+1];
		Arrays.fill(distance, INF);
		
		distance[start] = 0;
		pq.offer(new Node(start, 0)); 
		
		while(!pq.isEmpty()) {
			int now = pq.poll().end;
			
			if(now == end) return;
			
			for(int i=0; i<list[now].size(); i++) {
				Node next = list[now].get(i);
				if(distance[next.end] > distance[now] + next.cost) {
					distance[next.end] = distance[now] + next.cost;
					pq.offer(new Node(next.end, next.cost));
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList [N+1];
		for(int i=1; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b, c));
			list[b].add(new Node(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		check1 = false;
		check2 = false;
		int answer = INF;
		int sum = 0;
		
		dijkstra(1, v1); // 먼저 1 > v1 으로 가본다
		if(distance[v1] != INF) {
			sum += distance[v1];
			dijkstra(v1, v2);
			if(distance[v2] != INF) {
				sum += distance[v2];
				dijkstra(v2, N);
				if(distance[N] != INF) {
					check1 = true;
					sum += distance[N];
				}
			}
		}
		
		if(check1) {
			answer = sum;
		}
		sum = 0;
		
		dijkstra(1, v2);
		if(distance[v2] != INF) {
			sum += distance[v2];
			dijkstra(v2, v1);
			if(distance[v1] != INF) {
				sum += distance[v1];
				dijkstra(v1, N);
				if(distance[N] != INF) {
					check2 = true;
					sum += distance[N];
				}
			}
		}
		
		if(check2) {
			if(answer == INF) answer = sum;
			else answer = Math.min(answer, sum);
		}
		
		if(answer == INF) answer = -1;
		System.out.println(answer);
	}
	
}
