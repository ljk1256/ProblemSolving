package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1244 {
	
	static int[] swit;
	
	private static void turn(int sex, int number) {
		
		switch(sex) {
		
		case 1 : {
			int dir = number; 
			while(number < swit.length) {  
				if(swit[number] == 0) {  
					swit[number] = 1;
					number += dir;
					continue;
				}
				else {
					swit[number] = 0;
					number += dir;
					continue;
				}
			}
			break;
		}
		
		case 2 : {
			int minus = -1;
			int plus = 1;
			int temp = number;
			
			while(true) {
				if(number + minus >=1 && number + plus < swit.length && swit[number + minus] == swit[number + plus]) {
					if(swit[number + minus] == 0) {
						swit[number + minus] = 1;
						swit[number + plus] = 1;
						minus--;
						plus++;
						continue;
					}
					else {
						swit[number + minus] = 0;
						swit[number + plus] = 0;
						minus--;
						plus++;
						continue;
					}
				}
				else {
					if(swit[temp] == 0) {
						swit[temp] = 1;
						break;
					}
					else {
						swit[temp] = 0;
						break;
					}
				}
			}
			
		}
		break;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		swit = new int [N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			swit[i] = Integer.parseInt(st.nextToken());
		}
		
		int students = Integer.parseInt(br.readLine());
		for(int i=0; i<students; i++) {
			st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int number = Integer.parseInt(st.nextToken());
			turn(sex, number);
		}
		
		for(int i=1; i<N+1; i++) {
			System.out.print(swit[i] + " ");
			if(i % 20 == 0) {
				System.out.println();
			}
		}
	}

}
