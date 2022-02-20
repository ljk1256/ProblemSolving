package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ14696 {
	
	private static int get_count(List<Integer> list, int number) {
		int count = 0;
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i) == number) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st_A;
		StringTokenizer st_B;
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			st_A = new StringTokenizer(br.readLine());
			List<Integer> A_list = new ArrayList<>();
			int A_time = Integer.parseInt(st_A.nextToken());
			
			for(int j=0; j<A_time; j++) {
				A_list.add(Integer.parseInt(st_A.nextToken()));
			}
			
			st_B = new StringTokenizer(br.readLine());
			List<Integer> B_list = new ArrayList<>();
			int B_time = Integer.parseInt(st_B.nextToken());
			
			for(int j=0; j<B_time; j++) {
				B_list.add(Integer.parseInt(st_B.nextToken()));
			}
			
			if(get_count(A_list, 4) > get_count(B_list, 4)) {
				System.out.println("A");
				continue;
			}
			else if(get_count(A_list, 4) < get_count(B_list, 4)) {
				System.out.println("B");
				continue;
			}
			else if(get_count(A_list, 4) == get_count(B_list, 4) && get_count(A_list, 3) > get_count(B_list, 3)) {
				System.out.println("A");
				continue;
			}
			else if(get_count(A_list, 4) == get_count(B_list, 4) && get_count(A_list, 3) < get_count(B_list, 3)) {
				System.out.println("B");
				continue;
			}
			else if(get_count(A_list, 4) == get_count(B_list, 4) && get_count(A_list, 3) == get_count(B_list, 3) && get_count(A_list, 2) > get_count(B_list, 2)) {
				System.out.println("A");
				continue;
			}
			else if(get_count(A_list, 4) == get_count(B_list, 4) && get_count(A_list, 3) == get_count(B_list, 3) && get_count(A_list, 2) < get_count(B_list, 2)) {
				System.out.println("B");
				continue;
			}
			else if(get_count(A_list, 4) == get_count(B_list, 4) && get_count(A_list, 3) == get_count(B_list, 3) && get_count(A_list, 2) == get_count(B_list, 2) && get_count(A_list, 1) > get_count(B_list, 1)) {
				System.out.println("A");
				continue;
			}
			else if(get_count(A_list, 4) == get_count(B_list, 4) && get_count(A_list, 3) == get_count(B_list, 3) && get_count(A_list, 2) == get_count(B_list, 2) && get_count(A_list, 1) < get_count(B_list, 1)) {
				System.out.println("B");
				continue;
			}
			else {
				System.out.println("D");
				continue;
			}
		}
	}

}
