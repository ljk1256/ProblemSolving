package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ11000 {
	
	static class Table {
		
		int start;
		int end;
		
		public Table(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Table> tableList = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			tableList.add(new Table(start, end));
		}
		
		Collections.sort(tableList, new Comparator<Table>() {
			@Override
			public int compare(Table o1, Table o2) {
				return o1.start - o2.start;
			}
		});
		
		PriorityQueue<Table> pq = new PriorityQueue<>(new Comparator<Table>() {

			@Override
			public int compare(Table o1, Table o2) {
				return o1.end - o2.end;
			}
			
		});
		
		int minLectureRoom = 0;
		for(Table temp : tableList) {
			if(pq.isEmpty()) {
				minLectureRoom++;
				pq.add(temp);
				continue;
			}
			
			Table pqpoll = pq.poll();
			if(pqpoll.end > temp.start) {
				minLectureRoom++;
				pq.add(temp);
				pq.add(pqpoll);
			}
			
			else pq.add(temp);
		}
		
		System.out.println(minLectureRoom);
	}

}
