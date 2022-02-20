package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1162 {
	
	static class Node {
		
		int next;
		long time;
		int paveCnt;
		
		public Node(int next, long time, int paveCnt) {
			super();
			this.next = next;
			this.time = time;
			this.paveCnt = paveCnt;
		}
		
	}
	
	static ArrayList<Node>[] list;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		list = new ArrayList [N+1];
		for(int i=1; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			list[start].add(new Node(end, time, 0));
			list[end].add(new Node(start, time, 0));
		}
		
		pq = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if(o2.time > o1.time) return -1;
				else if(o2.time == o1.time) return 0;
				else return 1;
			}
			
		});
		long[][] minTime = new long [N+1][K+1]; // K가 최대 20인걸 봐선 dp를 생각해야한다
		
		for(int i=1; i<N+1; i++) {
			Arrays.fill(minTime[i], Long.MAX_VALUE);
		}
		
		pq.offer(new Node(1, 0, 0));
		minTime[1][0] = 0;
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			
			if(minTime[temp.next][temp.paveCnt] < temp.time) continue;
				
			for(int j=0; j<list[temp.next].size(); j++) {
				Node next = list[temp.next].get(j);
					
				if(minTime[next.next][temp.paveCnt] > minTime[temp.next][temp.paveCnt] + next.time) { // 안할때
					minTime[next.next][temp.paveCnt] = minTime[temp.next][temp.paveCnt] + next.time;
					pq.offer(new Node(next.next, minTime[next.next][temp.paveCnt], temp.paveCnt));
				}
						
				if(temp.paveCnt < K && minTime[next.next][temp.paveCnt+1] > minTime[temp.next][temp.paveCnt]) { // 포장할때
					minTime[next.next][temp.paveCnt+1] = minTime[temp.next][temp.paveCnt];
					pq.offer(new Node(next.next, minTime[next.next][temp.paveCnt+1], temp.paveCnt+1));
				}
			}
		}
		
		long ans = Long.MAX_VALUE;
		for(int i=0; i<K+1; i++) {
			if(minTime[N][i] < ans) ans = minTime[N][i];
		}
		
		System.out.println(ans);
	}

}
