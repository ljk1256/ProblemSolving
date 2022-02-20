package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ7575 {
	
	private static int[] makeTable(int[] pattern) {
		int patternSize = pattern.length;
		int[] table = new int [patternSize];
		int index = 0;
		
		for(int i=1; i<patternSize; i++) {
			while(index > 0 && pattern[i] != pattern[index]) index = table[index-1];
			if(pattern[i] == pattern[index]) table[i] = ++index;
		}
		return table;
	}
	
	private static boolean KMP(int[] program, int[] code) {
		int codeSize = code.length;
		int programSize = program.length;
		int[] table = makeTable(code);
		int index = 0;
		
		for(int i=0; i<programSize; i++) {
			while(index > 0 && program[i] != code[index]) index = table[index-1];
			if(program[i] == code[index]) {
				if(index == codeSize-1) return true;
				index++;
			}
		}
		return false;
	}
	
	private static boolean isVirus(boolean[] check) {
		for(boolean b : check) {
			if(!b) return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 프로그램 개수
		int K = Integer.parseInt(st.nextToken()); // 추정 최소 길이
		
		int[][] programs = new int [N][];
		for(int i=0; i<N; i++) {
			int M1 = Integer.parseInt(br.readLine());
			programs[i] = new int [M1];
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M1; j++) {
				programs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] code = new int [K]; // 코드를 담을 부분 배열
		boolean[] check = new boolean [N];
		
		for(int i=0; i<programs[0].length-K+1; i++) {
			System.arraycopy(programs[0], i, code, 0, K);
			Arrays.fill(check, false);
			check[0] = true; // 비교기준이 0번째 프로그램이므로 
			
			for(int j=1; j<N; j++) { // 기준 프로그램을 제외한 다른 모든 프로그램에 대해서
				if(KMP(programs[j], code)) check[j] = true;
			}
			
			if(isVirus(check)) {
				System.out.println("YES");
				return;
			}
			
			// 정방향으로 모든 프로그램을 탐색 하지 않고 하나만 탐색해서 정방향이 없을때 역방향도 탐색 해주고 없다면 그 이후 프로그램은 탐색 할 필요가없다
			
			int[] reverse = new int [K];
			for(int j=0, k=code.length-1; k>=0; k--, j++) {
				reverse[j] = code[k];
			}
			
			for(int j=1; j<N; j++) { // 기준 프로그램을 제외한 다른 모든 프로그램에 대해서
				if(check[j]) continue;
				if(KMP(programs[j], reverse)) check[j] = true;
			}
			
			if(isVirus(check)) {
				System.out.println("YES");
				return;
			}
		}
		
		System.out.println("NO");
	}

}
