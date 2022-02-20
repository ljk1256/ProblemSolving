package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BJ11279 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // print는 한번만
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1; // 오름차순 정렬
			}
		});
		
		for(int i=0; i<N; i++) {
			int t = Integer.parseInt(br.readLine());
			
			if(t == 0) {
				if(pq.isEmpty()) {
					sb.append(0).append("\n");
				}
				else sb.append(pq.poll()).append("\n");
			}
			
			else {
				pq.offer(t);
			}
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
