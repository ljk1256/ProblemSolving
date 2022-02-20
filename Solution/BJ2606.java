package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2606 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int K = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> network = new ArrayList<>();
		
		for(int i=0; i<N+1; i++) {
			network.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			network.get(start).add(end);
			network.get(end).add(start);
		}
		
		boolean[] visited = new boolean [N+1];
		int count = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(1);
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			visited[temp] = true;
			
			for(int i=0; i<network.get(temp).size(); i++) {
				if(!visited[network.get(temp).get(i)]) {
					q.offer(network.get(temp).get(i));
					visited[network.get(temp).get(i)] = true;
					count = count + 1;
				}
			}
		}
		
		System.out.println(count);
	}

}
