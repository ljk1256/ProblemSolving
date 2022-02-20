package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BJ1927 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		for(int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if(x != 0) {
				pq.offer(x);
			}
			else {
				if(pq.isEmpty()) {
					sb.append(0).append("\n");
				}
				else sb.append(pq.poll()).append("\n");
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
