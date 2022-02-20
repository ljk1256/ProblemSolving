package Solution;

import java.util.Scanner;

public class BJ2839 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int gram = sc.nextInt();
		int cnt = 0;
		
		while(true) {
			if(gram % 5 == 0) {
				System.out.println((gram / 5) + cnt);
				break;
			}
			else if(gram < 0) {
				System.out.println(-1);
				break;
			}		
			gram -= 3;
			cnt++;
		}

	}

}
