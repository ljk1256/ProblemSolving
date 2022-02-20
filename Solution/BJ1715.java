package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ1715 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		if(N == 1) System.out.println(0);
		else {
			int prevSum = 0;
			while(pq.size() != 1 && !pq.isEmpty()) { // 정렬해서 앞에서 부터 뽑는것이 반드시 최선은 아닐 수 있다
				int tempSum = pq.poll() + pq.poll();
				prevSum += tempSum;
				pq.offer(tempSum);
			}
			
			System.out.println(prevSum);
		}

	}

}
