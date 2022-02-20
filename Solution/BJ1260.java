package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ1260 {
	
	static boolean visited[];
	
	private static void bfs(ArrayList<ArrayList<Integer>> list, int start) {
		Queue<Integer> queue = new LinkedList();
		StringBuilder sb = new StringBuilder();
		queue.offer(start);
		
		for(int i=1; i<list.size(); i++) {
			if(list.get(i) != null) {
				Collections.sort(list.get(i), new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						return o1 - o2;
					}
				});
			}
		}
		
		while(!queue.isEmpty()) {
			int temp = queue.poll();
			
			if(!visited[temp]) {
				sb.append(temp).append(" ");
			}
			visited[temp] = true;
			
			for(int i=0; i<list.get(temp).size(); i++) {
				if(!visited[list.get(temp).get(i)]) {	
					queue.offer(list.get(temp).get(i));
				}
			}
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	
	private static void dfs(ArrayList<ArrayList<Integer>> list, int start) {
		
		for(int i=1; i<list.size(); i++) {
			if(list.get(i) != null) {
				Collections.sort(list.get(i), new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						return o2 - o1;
					}
				});
			}
		}
		
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		stack.push(start);
		
		while(!stack.isEmpty()) {
			int temp = stack.pop();
			
			if(!visited[temp]) {
				sb.append(temp).append(" ");
			}
			visited[temp] = true;
			
			for(int i=0; i<list.get(temp).size(); i++) {
				if(!visited[list.get(temp).get(i)]) {	
					stack.push(list.get(temp).get(i));
				}
			}
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		
		visited = new boolean [N+1];
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		
		for(int i=0; i<N+1; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			int node = Integer.parseInt(st.nextToken());
			
			list.get(index).add(node);
			list.get(node).add(index);
		}
		
		dfs(list, start);
		Arrays.fill(visited, false);
		bfs(list, start);
	}

}
