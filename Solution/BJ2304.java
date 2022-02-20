package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;


public class BJ2304 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<int []> list = new ArrayList<>();
		int area = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new int [] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		java.util.Collections.sort(list, new Comparator<int []>() {

			@Override
			public int compare(int [] o1, int [] o2) {
				
				return o1[0] - o2[0];
			}
		});
		
		int max_x = 0; // 최고 높이 x좌표
		int max_y = 0; // 최고 높이 y좌표
		for(int i=0; i<N; i++) {
			if(list.get(i)[1] > max_y) {
				max_x = list.get(i)[0];
				max_y = list.get(i)[1];
			}
		}
		
		int left_x = list.get(0)[0];
		int left_y = list.get(0)[1];
		int dir = 1;
		while(left_x < max_x) {  // 좌측 끝부터 최고 높이 좌표까지 넓이    최고점을 만나지 못했을때, 동일한 최고점 들어올때
			if(dir <= list.size()-1 && left_y < list.get(dir)[1]) {
				area += (list.get(dir)[0] - left_x) * left_y;
				left_x = list.get(dir)[0];
				left_y = list.get(dir)[1];
				dir++;
				continue;
			}
			else {
				dir++;
				continue;
			}
		}
		
		int right_x = list.get(list.size()-1)[0];
		int right_y = list.get(list.size()-1)[1];
		dir = -1;
		while(max_x < right_x) { // 우측 끝부터 최고 높이 좌표까지 넓이
			if(list.size()-1+dir >= 0 && right_y < list.get(list.size()-1+dir)[1]) {
				area += (right_x - list.get(list.size()-1+dir)[0]) * right_y;
				right_x = list.get(list.size()-1+dir)[0];
				right_y = list.get(list.size()-1+dir)[1];
				dir--;
				continue;
			}
			else {
				dir--;
				continue;
			}
		}
		
		area += max_y; // 최고 높이 넓이
		System.out.println(area);
	}

}
