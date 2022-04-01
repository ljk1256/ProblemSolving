package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2525 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int hour = Integer.parseInt(st.nextToken());
		int min = Integer.parseInt(st.nextToken());
		
		int plus = Integer.parseInt(br.readLine());
		
		min += plus;
		
		if(min >= 60) {
			hour += min/60;
			min %= 60;
		}
		
		if(hour >= 24) hour -= 24;
		
		System.out.println(hour + " " + min);
	}

}
