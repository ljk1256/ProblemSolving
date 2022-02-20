package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1967 {
	
	static int answer;
	static boolean[] visited;
	static ArrayList<Node>[] nodeList;
	
	static class Node {
		
		int nextNodeNum;
		int weight;
		
		public Node(int nextNodeNum, int weight) {
			super();
			this.nextNodeNum = nextNodeNum;
			this.weight = weight;
		}
		
	}
	
	private static void dfs(int nextNode, int sum) {
		for(int i=0; i<nodeList[nextNode].size(); i++) {
			if(!visited[nodeList[nextNode].get(i).nextNodeNum]) {
				visited[nodeList[nextNode].get(i).nextNodeNum] = true;
				dfs(nodeList[nextNode].get(i).nextNodeNum, sum+nodeList[nextNode].get(i).weight);
			}
		}
		
		answer = Math.max(answer, sum);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		nodeList = new ArrayList [N+1];
		
		for(int i=1; i<=N; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int rootNum = Integer.parseInt(st.nextToken());
			int childNum = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			nodeList[rootNum].add(new Node(childNum, weight));
			nodeList[childNum].add(new Node(rootNum, weight));
		}
		
		answer = Integer.MIN_VALUE;
		visited = new boolean [N+1];
		for(int i=1; i<N+1; i++) {
			Arrays.fill(visited, false);
			visited[i] = true;
			dfs(i, 0);
		}
		
		System.out.println(answer);
	}

}
