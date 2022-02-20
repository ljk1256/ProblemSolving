package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1197 {
	
	static int[] parent;
	
	private static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		else {
			return parent[a] = find(parent[a]); // 갱신 안하면 매번 호출할 때 찾아야 하기에 시간 초과날 수 있음
		}
	}
	
	private static void union(int a, int b) {
		int aroot = find(a);
		int broot = find(b);
		
		if(aroot != broot) {
			parent[broot] = parent[aroot];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Graph> pq = new PriorityQueue<>();
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			pq.offer(new Graph(start, end, weight));
		}
		
		parent = new int [V+1];
		for(int i=1; i<V+1; i++) {
			parent[i] = i;
		}
		
		int minweight = 0;
		for(int i=0; i<E; i++) {
			Graph next = pq.poll();
			
			if(find(next.start) != find(next.end)) {
				union(next.start, next.end);
				minweight = minweight + next.weight;
			}
		}
		
		System.out.println(minweight);
	}

}

class Graph implements Comparable<Graph>{
	int start;
	int end;
	int weight;
	
	public Graph(int start, int end, int weight) {
		super();
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Graph o) {
		
		return this.weight - o.weight;
	}
	
}