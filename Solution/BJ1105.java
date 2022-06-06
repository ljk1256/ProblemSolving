package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1105 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String left = st.nextToken();
		String right = st.nextToken();
		
		int L = Integer.parseInt(left);
		int R = Integer.parseInt(right);
		
		int answer = 0;
		
		if(left.length() != right.length()) System.out.println(answer); // 자릿수가 다르다면 8이 없게 할 수 있다
		else {
			for(int i=0; i<right.length(); i++) {
				if(left.charAt(i) == right.charAt(i) && right.charAt(i) == '8') answer++;
				else if(left.charAt(i) != right.charAt(i)) break;
			}
			
			System.out.println(answer);
		}
	}

}
