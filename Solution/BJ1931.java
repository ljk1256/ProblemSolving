package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ1931 {
	
	static class Meeting {
		
		int start;
		int end;
		
		public Meeting(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		ArrayList<Meeting> list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			list.add(new Meeting(start, end));
		}
		
		Collections.sort(list, new Comparator<Meeting>() {

			@Override
			public int compare(Meeting o1, Meeting o2) {
				return o1.start - o2.start;
			}
		});
		
		Collections.sort(list, new Comparator<Meeting>() {

			@Override
			public int compare(Meeting o1, Meeting o2) {
				return o1.end - o2.end;
			}
		});
		
		int idx = list.get(0).end, cnt = 1;
		for(int i=1; i<N; i++) {
			int start = list.get(i).start;
			
			if(idx <= start) {
				idx = list.get(i).end;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
