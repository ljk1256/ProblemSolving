package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1325 {
	
	static Deque<Integer>[] list;
	static boolean[] visited;
	static int cnt;
	
	static void dfs(int computer) {
		for(int temp : list[computer]) {
			if(!visited[temp]) {
				visited[temp] = true;
				cnt++;
				dfs(temp);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());	
		
		list = new ArrayDeque [N+1];
		for(int i=1; i<N+1; i++) {
			list[i] = new ArrayDeque<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[b].add(a); // list[b] 는 b 컴퓨터를 해킹하면 자동적으로 해킹 가능한 컴퓨터 번호를 저장
		}
		
		int[] record = new int [N+1];
		for(int i=1; i<N+1; i++) {
			cnt = 1; // 자기 자신은 이미 해킹에 포함
			visited = new boolean [N+1];
			visited[i] = true; // 자기 자신은 이미 해킹에 포함
			dfs(i);
			record[i] = cnt;
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=1; i<N+1; i++) {
			if(max < record[i]) max = record[i];
		}
		
		for(int i=1; i<N+1; i++) {
			if(record[i] >= max) sb.append(i).append(" ");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
