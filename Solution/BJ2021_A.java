package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ2021_A {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> list = new ArrayList<>();
		
		cp:for(int i=0; i<=N; i++) {
			if(i == 0 || i == 1) continue;
			else if(i == 2) list.add(i);
			else {
				for(int j=2; j<i; j++) {
					if(i % j == 0) continue cp;
				}
				list.add(i);
			}
		}
		
		if(N <= 5) {
			System.out.println(6);
			return;
		}
		
		int left = list.size()-2, right = list.size()-1;
		int mul = list.get(left) * list.get(right);
		
		while(mul > N) {
			left--;
			right--;
			mul = list.get(left) * list.get(right);
		}
		
		System.out.println(list.get(left+1) * list.get(right+1));
	}

}
