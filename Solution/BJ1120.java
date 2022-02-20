package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1120 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String origin = st.nextToken();
		String comparasion = st.nextToken();
		
		int originLen = origin.length();
		int comparasionLen = comparasion.length();
		
		int minAns = Integer.MAX_VALUE;
		
		for(int i=0; i<comparasionLen - originLen+1; i++) { // 그리디 접근 알파벳을 전부 붙일 필요없다 앞 뒤로 붙일 문자열은 전부 같은 문자로 붙여 최소로 만들기때문
			int tempCnt = 0;
			for(int j=0; j<originLen; j++) {
				if(origin.charAt(j) != comparasion.charAt(j+i)) tempCnt++;
			}
			minAns = Math.min(minAns, tempCnt);
		}
		
		System.out.println(minAns);
	}

}
