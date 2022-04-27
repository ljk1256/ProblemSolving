package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1826 {
	
	static class Fuel {
		
		int dist;
		int plus;
		
		public Fuel(int dist, int plus) {
			super();
			this.dist = dist;
			this.plus = plus;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Fuel> fuelList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int dist = Integer.parseInt(st.nextToken());
			int plus = Integer.parseInt(st.nextToken());
			
			fuelList.add(new Fuel(dist, plus));
		}
		
		Collections.sort(fuelList, new Comparator<Fuel>() {
			@Override
			public int compare(Fuel o1, Fuel o2) {
				return o1.dist - o2.dist;
			}
		});
		
		st = new StringTokenizer(br.readLine());
		int stopPoint = Integer.parseInt(st.nextToken());
		int remainFuel = Integer.parseInt(st.nextToken());
		int tempIdx = 0;
		int answer = 0;
		
		PriorityQueue<Fuel> pq = new PriorityQueue<>(new Comparator<Fuel>() {
			@Override
			public int compare(Fuel o1, Fuel o2) {
				return o2.plus - o1.plus;
			}
		});
		
		while(remainFuel < stopPoint) {
			while(tempIdx < fuelList.size() && remainFuel >= fuelList.get(tempIdx).dist) {
				pq.offer(fuelList.get(tempIdx));
				tempIdx++;
			}
			
			if(pq.isEmpty()) break;
			
			remainFuel += pq.poll().plus; // 그리디적으로 가장 먼 거리의 가장 많이 채워주는 주유소만 가는게 좋겠지만 그걸로 안된다면 다른 주유소를 가야한다
			answer++;
		}
		
		System.out.println(remainFuel >= stopPoint ? answer : -1);
	}

}
