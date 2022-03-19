package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1461 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int minLen = 0;
		
		PriorityQueue<Integer> posBooks = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		PriorityQueue<Integer> negaBooks = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		st = new StringTokenizer(br.readLine());
		int lastBook = Integer.MIN_VALUE; // 마지막에 놔둘 책의 위치는 저장해 뒀다가 마지막 거리에서 빼준다 0으로 돌아올 필요가 없기때문
		
		for(int i=0; i<N; i++) {
			int book = Integer.parseInt(st.nextToken());
			
			if(book > 0) posBooks.offer(book);
			else negaBooks.offer(-book); // 양수값으로 넣어준다
			
			lastBook = Math.max(lastBook, Math.abs(book));
		}
		
		while(!posBooks.isEmpty()) {
			int next = posBooks.poll(); // 책 한권을 꺼낸다
			
			for(int i=0; i<M-1; i++) { // 이미 한 권을 들고 있기때문에 M-1 만큼 더 빼준다
				if(posBooks.isEmpty()) break;
				posBooks.poll();
			}
			
			minLen += next*2;
		}
		
		while(!negaBooks.isEmpty()) {
			int next = negaBooks.poll(); // 책 한권을 꺼낸다
			
			for(int i=0; i<M-1; i++) { // 이미 한 권을 들고 있기때문에 M-1 만큼 더 빼준다
				if(negaBooks.isEmpty()) break;
				negaBooks.poll();
			}
			
			minLen += next*2;
		}
		
		System.out.println(minLen-lastBook);
	}

}
