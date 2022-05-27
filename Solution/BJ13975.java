package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ13975 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testcase = Integer.parseInt(br.readLine());
		StringBuilder answerBuilder = new StringBuilder();
		
		for(int i=0; i<testcase; i++) {
			int K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			PriorityQueue<Long> pq = new PriorityQueue<>();
			
			for(int j=0; j<K; j++) {
				pq.offer(Long.parseLong(st.nextToken()));
			}
			
			long tempAns = 0L;
			
			while(!pq.isEmpty()) {
				long firstFile = pq.poll();
				long secondFile = pq.poll();
				
				tempAns += firstFile + secondFile;
				
				if(pq.isEmpty()) break;
				
				pq.offer(firstFile + secondFile);
			}
			
			answerBuilder.append(tempAns).append("\n");
		}
		
		answerBuilder.setLength(answerBuilder.length()-1);
		System.out.println(answerBuilder.toString());
	}

}
