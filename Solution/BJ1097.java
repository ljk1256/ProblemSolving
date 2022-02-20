package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1097 {
	
	static int N;
	static int K;
	static int[] selected;
	static String[] inputs;
	static boolean[] check;
	static int answer;
	
	private static int[] makeTable(String pattern) {
		int patternSize = pattern.length();
		int[] table = new int [patternSize];
		int index = 0;
		
		for(int i=1; i<patternSize; i++) {
			while(index > 0 && pattern.charAt(i) != pattern.charAt(index)) index = table[index-1];
			if(pattern.charAt(i) == pattern.charAt(index)) table[i] = ++index;
		}
		
		return table;
	}
	
	private static void kmp(String parent, String pattern) {
		int parentSize = parent.length();
		int patternSize = pattern.length();
		int[] table = makeTable(pattern);
		int index = 0, tempCnt = 0;
		
		for(int i=0; i<parentSize-1; i++) {
			while(index > 0 && parent.charAt(i) != pattern.charAt(index)) index = table[index-1];
			if(parent.charAt(i) == pattern.charAt(index)) {
				if(index == patternSize-1) {
					tempCnt++;
					index = table[index];
				}
				else index++;
			}
		}
		
		if(tempCnt == K) {
			answer++;
			return;
		}
		
		else return;
	}
	
	private static void perm(int cnt) {
		if(cnt == N) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<N; i++) {
				sb.append(inputs[selected[i]]);
			}
			
			String parent = sb.toString() + sb.toString();
			String pattern = sb.toString();
			
			kmp(parent, pattern);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!check[i]) {
				selected[cnt] = i;
				check[i] = true;
				perm(cnt+1);
				check[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		inputs = new String [N];
		
		for(int i=0; i<N; i++) {
			inputs[i] = br.readLine();
		}
		
		K = Integer.parseInt(br.readLine());
		
		answer = 0;
		selected = new int [N];
		check = new boolean [N];
		
		perm(0);
		
		System.out.println(answer);
	}

}
