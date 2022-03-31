package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BJ1038 {
	
	private static void makeNum(ArrayList<Long> list, long num) {
		if(num > Long.parseLong("9876543210")) return; // 이 수보다 큰 감소하는 수는 없음
		
		list.add(num);
		
		for(long i=0; i<10; i++) {
			if(num % 10 > i) makeNum(list, num*10 + i); // 마지막 수 보다 작은 수라면 감소하는 수 가능
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Long> list = new ArrayList<>();
		
		for(long i=0; i<10; i++) {
			makeNum(list, i);
		}
		
		Collections.sort(list); // 정렬은 무조건 해줘야한다
		
		if(N >= list.size()) System.out.println(-1);
		else System.out.println(list.get(N));
	}

}
