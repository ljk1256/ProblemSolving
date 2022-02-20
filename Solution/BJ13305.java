package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ13305 {
	
	static class Data {
		
		int index;
		long cost;
		
		public Data(int index, long cost) {
			super();
			this.index = index;
			this.cost = cost;
		}
		
	}
	
	// 처음엔 무조건 주유를 해야하니 일단 넣고 출발 >> 도시를 경유하면서 만약 현재 주유가격보다 싸다면 낮은금액으로 주유 아니라면 현재 주유값으로 계속 주유한다
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		long[] distances = new long [N-1]; // 출발점 인덱스 기준
		long[] costs = new long [N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N-1; i++) {
			distances[i] = Long.parseLong(st.nextToken());
		}
		
		PriorityQueue<Data> pq = new PriorityQueue<>(new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				if(o1.cost < o2.cost) return -1;
				else if(o1.cost == o2.cost) return 0;
				else return 1;
			}
		});
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			costs[i] = Long.parseLong(st.nextToken());
			if(i < N-1) pq.offer(new Data(i, costs[i]));
		}
		
		long totalCost = 0L;
		if(N == 2) totalCost += costs[0] * distances[0];
		else {
			
			int minIdx = costs.length-2;
			int prevIdx = costs.length-1;
			long minCost = Long.MAX_VALUE;
			
			while(minIdx >= 0) {	
				Data temp = pq.poll();
				
				if(temp.index > minIdx) continue;
				
				minIdx = temp.index;
				minCost = temp.cost;
				
				for(int i=minIdx; i<prevIdx; i++) {
					totalCost += minCost * distances[i];
				}
				
				prevIdx = minIdx;
				minIdx--;
			}
			
		}
		
		System.out.println(totalCost);
	}

}
