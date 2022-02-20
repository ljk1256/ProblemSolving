package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1541 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "-");
		
		int total = 0;
		boolean flag = false;
		while(st.hasMoreTokens()) {
			String s = st.nextToken();
			int temp = 0;
			
			if(s.contains("+")) { // -단위로 끊었기 때문에 +덩어리가 존재할 수 있다
				StringTokenizer stt = new StringTokenizer(s, "+");
				while(stt.hasMoreTokens()) {
					temp += Integer.parseInt(stt.nextToken());
				}
			}
			else {
				temp = Integer.parseInt(s);
			}
			
			if(!flag) {
				total = temp;
				flag = true;
			}
			else {
				total -= temp; 
			}
		}
		
		System.out.println(total);
	}

}
