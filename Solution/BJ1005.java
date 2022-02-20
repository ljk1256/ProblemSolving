package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1005 {
	
	static class ACM {
		
		int level;
		int number;
		long time;
		
		public ACM(int level, int number, long time) {
			super();
			this.level = level;
			this.number = number;
			this.time = time;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		cp:for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			long[] times = new long [N+1];
			int[] orders = new int [N+1];
			boolean[] selected = new boolean [N+1];
			boolean[][] graph = new boolean [N+1][N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				times[j] = Long.parseLong(st.nextToken());
			}
			
			for(int j=0; j<K; j++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				
				graph[X][Y] = true;
				orders[Y]++;
			}
			
			int dest = Integer.parseInt(br.readLine()); // 지어야 할 목표 건물
			Queue<ACM> q = new LinkedList<>();
			long[] resultTime = new long [N+1];
			
			for(int j=1; j<N+1; j++) {
				if(!selected[j] && orders[j] == 0) {
					q.offer(new ACM(0, j, times[j]));
					selected[j] = true;
					resultTime[j] = times[j];
				}
			}
			
			while(!q.isEmpty()) {
				ACM temp = q.poll();
				
				if(temp.number == dest) {
					sb.append(resultTime[temp.number]).append("\n");
					continue cp;
				}
				
				for(int j=1; j<N+1; j++) {
					if(graph[temp.number][j]) {
						resultTime[j] = Math.max(resultTime[j], resultTime[temp.number] + times[j]); // 같은 레벨이 아닌 하위 건물이랑 연관된 것만 가지고 시간을 계산해야한다
						orders[j]--;
					}
				}
				
				for(int j=1; j<N+1; j++) {
					if(!selected[j] && orders[j] == 0) {
						q.offer(new ACM(temp.level+1, j, times[j]));
						selected[j] = true;
					}
				}
				
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
