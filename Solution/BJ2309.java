package Solution;

import java.util.Arrays;
import java.util.Scanner;

public class BJ2309 {
	
	static int[] selected;
	static int[] dwarf; 
	
	private static void comb(int cnt, int idx) {
		if(cnt == 7) {
			int sum = 0;
			for(int i=0; i<7; i++) {
				sum += selected[i];
			}
			
			if(sum == 100) {
				Arrays.sort(selected);
				for(int i=0; i<7; i++) {
					System.out.println(selected[i]);
				}
				return;
			}
			return;
		}
		
		for(int i=idx; i<9; i++) {
			selected[cnt] = dwarf[i];
			comb(cnt+1, i+1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		dwarf = new int [9];
		
		for(int i=0; i<9; i++) {
			dwarf[i] = sc.nextInt();
		}
		selected = new int [7];
		
		comb(0, 0);
		sc.close();
	}

}
