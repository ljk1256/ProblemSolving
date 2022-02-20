package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ1484 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long G = Long.parseLong(br.readLine());
		
		ArrayList<Long> powerList = new ArrayList<>();
		for(long i=1; i<100000; i++) {
			powerList.add(i*i);
		}
		
		int left = 0, right = left+1;
		StringBuilder sb = new StringBuilder();
		
		while(left < right && right < powerList.size()) {
			long leftPower = powerList.get(left);
			long rightPower = powerList.get(right);
			
			if(rightPower - leftPower == G) {
				sb.append((int)Math.sqrt(rightPower)).append("\n");
				left++;
			}
			
			else if(rightPower - leftPower < G) right++;
			else left++;
		}
		
		if(sb.length() == 0) {
			System.out.println(-1);
		}
		else {
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}
