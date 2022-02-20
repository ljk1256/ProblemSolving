package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ20304 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new LinkedList<>();
		
		int[] visited = new int [N+1];
		Arrays.fill(visited, 21); // 최대 20일 수 있으니 그 보다 높은 값으로 초기화
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int password = Integer.parseInt(st.nextToken());
			q.offer(password); // 로그인 시도에 사용된 비밀번호 큐에 삽입
			visited[password] = 0;
		}
		
		int maxAns = 0; // 안전도
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			
			for(int i=1; i<=N; i <<= 1) {
				int newNum = i ^ temp; // temp 와 해밍 거리가 1인 비밀번호 XOR 연산으로 찾는다 서로 달라야 1 출력
				
				if(newNum <= N && visited[newNum] > visited[temp] + 1) { // 최대범위 N을 넘지 않으면서 안전거리가 최소라면
					q.offer(newNum);
					visited[newNum] = visited[temp] + 1;
					maxAns = Math.max(maxAns, visited[newNum]);
				}
			}
		}
		
		System.out.println(maxAns);
	}

}
