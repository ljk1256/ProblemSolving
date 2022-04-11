package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ15903 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int numberCnt = Integer.parseInt(st.nextToken());
		int gameCnt = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<numberCnt; i++) {
			pq.offer(Long.parseLong(st.nextToken()));
		}
		
		for(int i=0; i<gameCnt; i++) {
			long first = pq.poll();
			long second = pq.poll();
			
			pq.offer(first + second);
			pq.offer(first + second);
		}
		
		long answer = 0;
		
		while(!pq.isEmpty()) answer += pq.poll();
		
		System.out.println(answer);
	}

}
