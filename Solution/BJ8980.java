package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ8980 {
	
	static class Info {
		
		int src;
		int dest;
		int box;
		
		public Info(int src, int dest, int box) {
			super();
			this.src = src;
			this.dest = dest;
			this.box = box;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int village = Integer.parseInt(st.nextToken());
		int capacity = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		
		ArrayList<Info> deliveryList = new ArrayList<>();

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int box = Integer.parseInt(st.nextToken());
			
			deliveryList.add(new Info(src, dest, box));
		}
		
		Collections.sort(deliveryList, new Comparator<Info>() {
			@Override
			public int compare(Info o1, Info o2) {
				if(o1.dest == o2.dest) return o1.src - o2.src;
				else return o1.dest - o2.dest;
			}
		});
		
		int[] boxes = new int [village+1]; // dp로 현재 구간에서 트럭이 싣고 있는 박스의 수
		int maxAns = 0;
		
		for(int i=0; i<deliveryList.size(); i++) {
			Info temp = deliveryList.get(i);
			int src = temp.src;
			int dest = temp.dest;
			int box = temp.box;
			
			int loadedBox = 0;
			for(int j=src; j<dest; j++) { // 해당 구간에서는 가장 큰값을 찾는 이유는 그 구간에서 박스를 들 여유공간은 확보되어야 하기때문
				loadedBox = Math.max(loadedBox, boxes[j]);
			}
			
			int possible = Math.min(capacity-loadedBox, box); // 현재 여유공간이 배송해야 할 박스보다 작다면 여유공간 만큼만 배송가능하다
			maxAns += possible;
			
			for(int j=src; j<dest; j++) {
				boxes[j] += possible;
			}
		}
		
		System.out.println(maxAns);
	}

}
