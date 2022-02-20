package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11780 {

	static final int INF = 100000000;
	static int[][] traces;
	static int[][] distance;
	static StringBuilder sb;
	
	private static void Tracking(int start, int end, int count) {
		if(traces[start][end] == 0) {
			sb.append(count).append(" ").append(start).append(" ").append(end);
			return;	
		}
		
		else {
			StringBuilder builder = new StringBuilder();
			int cnt = count+1;
			cnt += dfs(start, traces[start][end], builder);
			builder.append(traces[start][end]).append(" ");
			cnt += dfs(traces[start][end], end, builder);
			sb.append(cnt).append(" ").append(start).append(" ").append(builder.toString()).append(end);
			return;
		}
	}
	
	private static int dfs(int start, int end, StringBuilder builder) {
		if(traces[start][end] == 0) {
			return 0;
		}
		else {
			int sum = 1;
			sum += dfs(start, traces[start][end], builder);
			builder.append(traces[start][end]).append(" ");
			sum += dfs(traces[start][end], end, builder);
			return sum;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine()); // 도시의 수
		int m = Integer.parseInt(br.readLine()); // 버스 노선 정보의 수
		
		int[][] input = new int [n+1][n+1];
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				if(i == j) input[i][j] = 0;
				else input[i][j] = INF;
			}
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 츌발지
			int b = Integer.parseInt(st.nextToken()); // 도착지
			int c = Integer.parseInt(st.nextToken()); // 비용
			
			if(input[a][b] > c) input[a][b] = c;
		}
		
		distance = new int [n+1][n+1];
		for(int i=1; i<n+1; i++) { // 배열 복사
			for(int j=1; j<n+1; j++) {
				distance[i][j] = input[i][j];
			}
		}
		
		traces = new int [n+1][n+1];
		for(int i=1; i<n+1; i++) { // 경유지 도시
			for(int j=1; j<n+1; j++) { // 출발 도시
				for(int k=1; k<n+1; k++) { // 도착 도시
					if(distance[j][k] > distance[j][i] + distance[i][k]) {
						distance[j][k] = distance[j][i] + distance[i][k];
						traces[j][k] = i;
					}
				}
			}
		}
		
		sb = new StringBuilder();
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				if(distance[i][j] == INF) {
					sb.append(0).append(" ");
				}
				else {
					sb.append(distance[i][j]).append(" ");
				}
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				if(i == j) {
					sb.append(0).append("\n");
					continue;
				}
				else {
					if(distance[i][j] == INF) { // 애초에 탐색 하지 않은 곳이면 0출력
						sb.append(0).append("\n");
						continue;
					}
					Tracking(i, j, 2);
					sb.append("\n");
				}
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
