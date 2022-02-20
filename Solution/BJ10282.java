package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ10282 {
	
	static class Node {
		
		int next;
		int time;
		
		public Node(int next, int time) {
			super();
			this.next = next;
			this.time = time;
		}
		
	}
	
	static ArrayList<Node>[] list;
	static final int INF = 1000000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()); // 간선의 수
			int s = Integer.parseInt(st.nextToken()); // 시작 컴퓨터
			
			list = new ArrayList [n+1];
			for(int j=1; j<n+1; j++) {
				list[j] = new ArrayList<>();
			}
			
			for(int j=0; j<d; j++) {
				st = new StringTokenizer(br.readLine());
				int end = Integer.parseInt(st.nextToken());
				int start = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				
				list[start].add(new Node(end, time));
			}
			
			int[] minTimes = new int [n+1];
			Arrays.fill(minTimes, INF);
			
			minTimes[s] = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.time, o2.time));
			pq.offer(new Node(s, 0));
			
			while(!pq.isEmpty()) {
				Node temp = pq.poll();
				
				if(minTimes[temp.next] < temp.time) continue;
				
				for(int j=0; j<list[temp.next].size(); j++) {
					Node next = list[temp.next].get(j);
					
					if(minTimes[next.next] > minTimes[temp.next] + next.time) {
						minTimes[next.next] = minTimes[temp.next] + next.time;
						pq.offer(new Node(next.next, minTimes[next.next]));
					}
				}
			}
			
			int cnt = 0, maxTime = 0;
			for(int j=0; j<minTimes.length; j++) {
				if(minTimes[j] != INF) {
					maxTime = Math.max(maxTime, minTimes[j]);
					cnt++;
				}
			}
			
			sb.append(cnt).append(" ").append(maxTime).append("\n");
		}
		
		sb.setLength(sb.length()-1);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
