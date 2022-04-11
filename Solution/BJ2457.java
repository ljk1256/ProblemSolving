package Solution;

import java.util.*;
import java.io.*;

public class BJ2457 {
	
	static class Flower {
		
		int start;
		int end;
		
		public Flower(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Flower> flowerList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int S_month = Integer.parseInt(st.nextToken());
			int S_day = Integer.parseInt(st.nextToken());
			int E_month = Integer.parseInt(st.nextToken());
			int E_day = Integer.parseInt(st.nextToken());
			
			flowerList.add(new Flower(S_month*100 + S_day, E_month*100 + E_day)); // 월*100 + 일 로 일수에 가중치를 부여할 수 있다
		}
		
		Collections.sort(flowerList, new Comparator<Flower>() {
			@Override
			public int compare(Flower o1, Flower o2) {
				if(o1.start == o2.start) return o2.end - o1.end;
				else return o1.start - o2.start;
			}
		});
		
		int nextStart = 301;
		int tempMax = 301;
		int totalEnd = 1201;
		int index = 0;
		int count = 0;
		
		while(nextStart < totalEnd) {
			boolean isVaild = false; // 루프 문을 빠져 나올수 있게함
			
			for(int i=index; i<N; i++) {
				Flower tempFlower = flowerList.get(i);
				
				if(tempFlower.start > nextStart) break; // 비어있는 기간이 있으면 더 이상 진행불가
				
				if(nextStart >= tempFlower.start) {
					if(nextStart >= tempFlower.end) continue;
					
					else if(tempMax < tempFlower.end) { // 아 부분이 중요한 이 보다 더 좋은 조건을 만족하는 꽃이 있는지 더 확인해 본다
						isVaild = true;
						tempMax = tempFlower.end;
						index = i+1;
					}
				}
			}
			
			if(!isVaild) break;
			else {
				nextStart = tempMax;
				count++;
			}
		}
		
		if(nextStart >= totalEnd) System.out.println(count);
		else System.out.println(0);
	}

}
