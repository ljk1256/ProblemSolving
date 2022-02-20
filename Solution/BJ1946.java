package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ1946 {
	
	static class Grade {
		
		int exam;
		int interview;
		
		public Grade(int exam, int interview) {
			super();
			this.exam = exam;
			this.interview = interview;
		}
		
	}
	// 문제 분석이 중요 점수인지 등수인지 확인하여 정렬한다
	// 처음부터 등수와 인덱스를 일치 시킨다면 좀 더 빠르게 접근이 가능하다
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			int N = Integer.parseInt(br.readLine());
			
			ArrayList<Grade> list = new ArrayList<>();
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				int exam = Integer.parseInt(st.nextToken());
				int interview = Integer.parseInt(st.nextToken());
				
				list.add(new Grade(exam, interview));
			}
			
			Collections.sort(list, new Comparator<Grade>() { // 필기시험 정렬

				@Override
				public int compare(Grade o1, Grade o2) {
					return o1.exam - o2.exam;
				}
				
			});
			
			int interview = list.get(0).interview, cnt = 1;
			for(int j=1; j<N; j++) {
				Grade temp = list.get(j);
				if(temp.interview < interview) {
					cnt++;
					interview = temp.interview;
				}
			}
			
			sb.append(cnt).append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
