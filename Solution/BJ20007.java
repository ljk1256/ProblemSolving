package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ20007 {
	
	static class Node {

		int end;
		int cost;
		
		public Node(int end, int cost) {
			super();
			this.end = end;
			this.cost = cost;
		}
		
	}
	
	static final int INF = 2000000000;
	static int N;
	static int Y;
	static int[] distance;
	static boolean[] visited;
	
	private static int nextHome() {
		int min = INF;
		int idx = -1;
		
		for(int j=0; j<N; j++) {
			if(j == Y) continue;
			if(!visited[j] && min > distance[j]) {
				min = distance[j];
				idx = j;
			}
		}

		return idx;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 집의 수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수
		int X = Integer.parseInt(st.nextToken()); // 하루에 최대로 갈 수 있는 거리
		Y = Integer.parseInt(st.nextToken()); // 성현이의 집
		
		ArrayList<Node>[] list = new ArrayList [N];
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0;  i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list[start].add(new Node(end, cost));
			list[end].add(new Node(start, cost));
		}
		
		distance = new int [N];
		Arrays.fill(distance, INF);
		
		distance[Y] = 0; // 성현이 집 입력
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost;
			}
		});
		
		pq.offer(new Node(Y, 0)); // 시작점을 넣어준다
		
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			
			if(distance[temp.end] < temp.cost) continue;
			
			for(int i=0; i<list[temp.end].size(); i++) {
				Node next = list[temp.end].get(i);
				
				if(distance[next.end] > distance[temp.end] + next.cost) {
					distance[next.end] = distance[temp.end] + next.cost;
					pq.offer(new Node(next.end, distance[next.end]));
				}
			}
		}
		
		for(int i=0; i<N; i++) { // 떡을 돌릴 수 없거나 왕복거리가 X 보다 큰 경우
			if(distance[i] == INF || distance[i] * 2 > X) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		
		visited = new boolean [N];
		int answer = 1;
		
	bp : while(true) {
			int remain = X; // 하룻 밤 자고 갈수 있는 거리 초기화
			
			while(true) {
				int nextHome = nextHome();
				
				if(nextHome == -1) break bp; // 더 이상 방문할 집이 없다면
				
				if(distance[nextHome]*2 <= remain) {
					remain -= distance[nextHome]*2;
					visited[nextHome] = true;
				}
				else break;
			}
			
			answer++;
		}
		
		System.out.println(answer);
	}

}
