package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2138 {
	
	static int[] oper = {-1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int size = Integer.parseInt(br.readLine());
		String input = br.readLine();
		
		int[] temp1 = new int [size];
		int[] temp2 = new int [size];
		int[] purpose = new int [size];
		
		for(int i=0; i<size; i++) {
			temp1[i] = input.charAt(i) - '0';
			temp2[i] = input.charAt(i) - '0';
		}
		
		input = br.readLine();
		
		for(int i=0; i<size; i++) {
			purpose[i] = input.charAt(i) - '0';
		}
		
		int answer1 = 0;
		int answer2 = 1;
		int totalAns = Integer.MAX_VALUE;
		
		// 0번째를 누르지 않고 출발
		for(int i=1; i<size; i++) {
			boolean isSame = true;
			
			if(temp1[i-1] != purpose[i-1]) isSame = false;
			
			if(!isSame) {
				answer1++;
				for(int j=0; j<3; j++) {
					int di = i + oper[j];
					
					if(di < 0 || di >= size) continue;
					
					temp1[di] = temp1[di] == 0 ? 1 : 0;
				}
			}
		}
		
		boolean flag = true;
		for(int i=0; i<size; i++) {
			if(temp1[i] != purpose[i]) {
				flag = false;
				break;
			}
		}
		
		if(flag) totalAns = answer1;
		
		temp2[0] = 1 - temp2[0]; // 1이었다면 0이 되고 0이라면 1이 된다
		temp2[1] = 1 - temp2[1];
		for(int i=1; i<size; i++) {
			boolean isSame = true;
			
			if(temp2[i-1] != purpose[i-1]) isSame = false;
			
			if(!isSame) {
				answer2++;
				for(int j=0; j<3; j++) {
					int di = i + oper[j];
					
					if(di < 0 || di >= size) continue;
					
					temp2[di] = temp2[di] == 0 ? 1 : 0;
				}
			}
		}
		
		flag = true;
		for(int i=0; i<size; i++) {
			if(temp2[i] != purpose[i]) {
				flag = false;
				break;
			}
		}
		
		if(flag) totalAns = Math.min(totalAns, answer2);
		
		System.out.println(totalAns == Integer.MAX_VALUE ? -1 : totalAns);
	}

}
