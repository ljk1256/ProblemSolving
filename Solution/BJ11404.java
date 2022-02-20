package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11404 {
	
	static final int INF = 100000000;

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
		
		int[][] distance = new int [n+1][n+1];
		for(int i=1; i<n+1; i++) { // 배열 복사
			for(int j=1; j<n+1; j++) {
				distance[i][j] = input[i][j];
			}
		}
		
		for(int i=1; i<n+1; i++) { // 경유지 도시
			for(int j=1; j<n+1; j++) { // 출발 도시
				for(int k=1; k<n+1; k++) { // 도착 도시
					if(distance[j][k] > distance[j][i] + distance[i][k]) {
						distance[j][k] = distance[j][i] + distance[i][k];
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<n+1; i++) { // 배열 복사
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
		
		System.out.println(sb.toString());
	}

}
