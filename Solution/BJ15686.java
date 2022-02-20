package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ15686 {
	
	static class House {
		int x;
		int y;
		int dir;
		public House(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	static class Chicken {
		int x;
		int y;
		int cnt;
		public Chicken(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	static ArrayList<House> house_list;
	static ArrayList<Chicken> chicken_list;
	static int M;
	static int answer;
	
	private static void Comb(int cnt, int idx, boolean[] selected) {
		if(cnt == M) { // 조합이 완성됐다면
			
			for(int i=0; i<house_list.size(); i++) { // 거리 다시 구하기 위해 거리 초기화
				house_list.get(i).dir = Integer.MAX_VALUE;
			}
			
			for(int i=0; i<chicken_list.size(); i++) {
				if(selected[i]) { // 선택된 치킨집이라면
					int chicken_x = chicken_list.get(i).x;
					int chicken_y = chicken_list.get(i).y;
					
					for(int j=0; j<house_list.size(); j++) {
						int house_x = house_list.get(j).x;
						int house_y = house_list.get(j).y;
						int dir = Math.abs(house_x - chicken_x) + Math.abs(house_y - chicken_y); // 해당 치킨집과의 치킨거리
						house_list.get(j).dir = Math.min(house_list.get(j).dir, dir);
					}
				}
			}
			int total = 0;
			for(int i=0; i<house_list.size(); i++) {
				total += house_list.get(i).dir;
			}
			
			answer = Math.min(answer, total); // 최소거리 갱신
			return;
		}
		
		else {
			for(int i=idx; i<chicken_list.size(); i++) {
				if(!selected[i]) {
					selected[i] = true;
					Comb(cnt+1, i+1, selected);
					selected[i] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		house_list = new ArrayList<>();
		chicken_list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int number = Integer.parseInt(st.nextToken());
				
				if(number == 1) {
					house_list.add(new House(i, j, Integer.MAX_VALUE));
				}
				else if(number == 2) {
					chicken_list.add(new Chicken(i, j, 0));
				}
			}
		}
		
		answer = Integer.MAX_VALUE;
		Comb(0, 0, new boolean [chicken_list.size()]);
		System.out.println(answer);
	}

}
