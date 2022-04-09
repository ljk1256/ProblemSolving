package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ2109 {
	
	static class lecture {
		
		int day;
		int cost;
		
		public lecture(int day, int cost) {
			super();
			this.day = day;
			this.cost = cost;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<lecture> list = new ArrayList<>();
		
		lecture[] table = new lecture [10001];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cost = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			
			list.add(new lecture(day, cost));
		}
		
		Collections.sort(list, new Comparator<lecture>() {
			@Override
			public int compare(lecture o1, lecture o2) {
				if(o1.cost == o2.cost) return o1.day - o2.day;
				else return o2.cost - o1.cost;
			}
		});
		
		for(int i=0; i<N; i++) {
			lecture temp = list.get(i);
			int day = temp.day;
			int cost = temp.cost;
			
			for(int j=day; j>0; j--) {
				if(table[j] == null) {
					table[j] = temp;
					break;
				}
			}
		}
		
		int answer = 0;
		
		for(int i=1; i<10001; i++) {
			if(table[i] != null) answer += table[i].cost;
		}
		
		System.out.println(answer);
	}

}
