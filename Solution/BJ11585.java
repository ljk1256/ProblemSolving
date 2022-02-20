package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11585 {
	
	static int cnt;
	static int[] table;
	static char[] pattern;
	
	private static int[] makeTable(char[] pattern) {
		int patternSize = pattern.length;
		int[] table = new int [patternSize];
		int index = 0;
		
		for(int i=1; i<patternSize; i++) {
			while(index > 0 && pattern[i] != pattern[index]) index = table[index-1];
			if(pattern[i] == pattern[index]) table[i] = ++index;
		}
		return table;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		char[] input = new char [N];
		pattern = new char [N];
		
		for(int i=0; i<2; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				if(i == 0) input[j] = st.nextToken().charAt(0);
				else pattern[j] = st.nextToken().charAt(0);
			}
		}
		
		cnt = 0;
		table = makeTable(pattern);
		char[] multiple = new char [input.length*2]; // 2배로 늘려주기
		System.arraycopy(input, 0, multiple, 0, input.length);
		System.arraycopy(input, 0, multiple, input.length, input.length);
		
		int index = 0;
		for(int i=0; i<multiple.length-1; i++) { // 인덱스를 두배 한 것에서 하나를 빼줘야 한다 아니면 원형이 아닌 그냥 문자열 2배라 중복된 결과가 나온다
			while(index > 0 && multiple[i] != pattern[index]) index = table[index-1];
			if(multiple[i] == pattern[index]) {
				if(index == pattern.length-1) {
					cnt++;
					index = table[index];
				}
				else index++;
			}
		}
		
		if(cnt == N) {
			System.out.println("1/1");
			return;
		}
		else {
			StringBuilder sb = new StringBuilder();
			if(N % cnt == 0) sb.append(1).append("/").append(N/cnt);
			else sb.append(cnt).append("/").append(N);
			System.out.println(sb.toString());
			return;
		}
	}

}
