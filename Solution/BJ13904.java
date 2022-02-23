package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ13904 {
	
	static class Assignment {
		
		int day;
		int score;
		
		public Assignment(int day, int score) {
			this.day = day;
			this.score = score;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Assignment> pq = new PriorityQueue<>(new Comparator<Assignment>() {
			@Override
			public int compare(Assignment o1, Assignment o2) {
				if(o1.score == o2.score) return o1.day - o2.day;
				else return o2.score - o1.score;
			}
		});
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			
			pq.offer(new Assignment(day, score));
		}
		
		int[] totalScore = new int [1001];
		
		while(!pq.isEmpty()) {
			Assignment temp = pq.poll();
			
			if(totalScore[temp.day] == 0) totalScore[temp.day] = temp.score; // 마감일 최대 기한에 넣어야 남은 기간을 최대로 늘릴 수 있어 더 많은 과제를 할 수 있다
			else {
				int idx = temp.day;
				while(idx >= 1) {
					if(totalScore[idx] == 0) {
						totalScore[idx] = temp.score;
						break;
					}
					else idx--;
				}
			}
		}
		
		int answer = 0;
		for(int i=1; i<1001; i++) answer += totalScore[i];
		
		System.out.println(answer);
	}

}
