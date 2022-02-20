package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1202 {
	
	static class Jewel {
		
		int weight; // 보석의 무게
		int cost; // 보석의 가격
		
		public Jewel(int weight, int cost) {
			super();
			this.weight = weight;
			this.cost = cost;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 보석의 개수
		int K = Integer.parseInt(st.nextToken()); // 가방의 개수
		
		
		List<Jewel> jewels = new ArrayList<>(300000);
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int M1 = Integer.parseInt(st.nextToken());
			int V1 = Integer.parseInt(st.nextToken());
			jewels.add(new Jewel(M1, V1));
		}
		
		List<Integer> bags = new ArrayList<>(300000);
		for(int i=0; i<K; i++) {
			bags.add(Integer.parseInt(br.readLine()));
		}
		
		long answer = 0;
		Collections.sort(jewels, new Comparator<Jewel>() { // 무게가 가벼운 보석 순서대로

			@Override
			public int compare(Jewel o1, Jewel o2) {
				return o1.weight - o2.weight;
			}
		});
		Collections.sort(bags); // 허용 무게가 가벼운 순서대로
		
		PriorityQueue<Jewel> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.cost, o1.cost)); // 가격이 비싼 순서대로 정렬
		int jewel_index = 0;
		for(int i=0; i<bags.size(); i++) {
			int temp_bag = bags.get(i);
			
			for(int j=jewel_index; j<jewels.size(); j++) { // 한번 큐에 넣은 보석은 또 넣지 않게 하기 위해
				if(jewels.get(j).weight <= temp_bag) {
					pq.offer(new Jewel(jewels.get(j).weight, jewels.get(j).cost));
					jewel_index++;
				}
				else break; // 루프 탈출이 없다면 모든 경우에 대해서 if문을 돔
			}
			
			if(!pq.isEmpty()) {
				answer += pq.poll().cost;
			}
		}
		
		System.out.println(answer);
	}

}
