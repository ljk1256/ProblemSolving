package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ9177 {
	
	static char[][] inputs;
	
	private static boolean bfs() {
		boolean[][] visited = new boolean [inputs[0].length+1][inputs[1].length+1];
		
		Queue<int []> q = new LinkedList<>();
		q.offer(new int [] {0, 0});
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int firstIdx = temp[0];
			int secondIdx = temp[1];
			
			if(firstIdx + secondIdx == inputs[2].length) return true;
			
			if(firstIdx < inputs[0].length && inputs[0][firstIdx] == inputs[2][firstIdx+secondIdx] && !visited[firstIdx][secondIdx]) {
				q.offer(new int [] {firstIdx+1, secondIdx}); // 첫번째 문자 같은지 보고
			}
			
			if(secondIdx < inputs[1].length && inputs[1][secondIdx] == inputs[2][firstIdx+secondIdx] && !visited[firstIdx][secondIdx]) {
				q.offer(new int [] {firstIdx, secondIdx+1}); // 두번째 문자가 같은지 보고
			}
			visited[firstIdx][secondIdx] = true;
		}
		
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			inputs = new char [3][];
			for(int j=0; j<3; j++) {
				inputs[j] = st.nextToken().toCharArray();
			}
			
			boolean isPossible = bfs();
			if(isPossible) sb.append("Data set ").append(i+1).append(": ").append("yes").append("\n");
			else sb.append("Data set ").append(i+1).append(": ").append("no").append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
