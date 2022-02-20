package Solution;

import java.util.Comparator;
import java.util.PriorityQueue;

public class test2 {
	
	static int N;
	static int[] selected;
	
	private static void comb(int cnt, int idx) {
		if(cnt == N) {
			for(int i=0; i<N; i++) {
				System.out.print(selected[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=idx; i<N; i++) {
			selected[cnt] = i;
			comb(cnt+1, i+1);
		}
	}

	public static void main(String[] args) {
		N = 3;
		selected = new int [N];
		comb(0, 0);
	}

}
