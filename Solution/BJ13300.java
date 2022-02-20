package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ13300 {
	
	static int count = 0;
	static int K;
	
	private static void allocate(List<String> list) {
		if(!list.isEmpty()) {
			if(list.size() / K == 0) {
				count++;
			}
			else {
				count += list.size() / K;
				
				if(list.size() % K != 0) {
					count++;
				}
				else
					return;
			}
		}
		return;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		List<String> w1_list = new ArrayList<>();
		List<String> m1_list = new ArrayList<>();
		
		List<String> w2_list = new ArrayList<>();
		List<String> m2_list = new ArrayList<>();
		
		List<String> w3_list = new ArrayList<>();
		List<String> m3_list = new ArrayList<>();
		
		List<String> w4_list = new ArrayList<>();
		List<String> m4_list = new ArrayList<>();
		
		List<String> w5_list = new ArrayList<>();
		List<String> m5_list = new ArrayList<>();
		
		List<String> w6_list = new ArrayList<>();
		List<String> m6_list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());
			
			if(sex == 0) {
				switch(grade) {
				case 1 : {
					w1_list.add("W");
					break;
				}
				case 2 : {
					w2_list.add("W");
					break;
				}
				case 3 : {
					w3_list.add("W");
					break;
				}
				case 4 : {
					w4_list.add("W");
					break;
				}
				case 5 : {
					w5_list.add("W");
					break;
				}
				case 6 : {
					w6_list.add("W");
					break;
				}
				}
			}
			
			else {
				switch(grade) {
				case 1 : {
					m1_list.add("M");
					break;
				}
				case 2 : {
					m2_list.add("M");
					break;
				}
				case 3 : {
					m3_list.add("M");
					break;
				}
				case 4 : {
					m4_list.add("M");
					break;
				}
				case 5 : {
					m5_list.add("M");
					break;
				}
				case 6 : {
					m6_list.add("M");
					break;
				}
				}
			}
		}
		
		allocate(w1_list);
		allocate(w2_list);
		allocate(w3_list);
		allocate(w4_list);
		allocate(w5_list);
		allocate(w6_list);
		allocate(m1_list);
		allocate(m2_list);
		allocate(m3_list);
		allocate(m4_list);
		allocate(m5_list);
		allocate(m6_list);
		
		System.out.println(count);
	}

}
