package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ16637 {
	
	static int operSize;
	static int maxAns;
	static char[] input;
	static boolean[] selected; // true 일 경우 괄호 사용
	static boolean[] parenthesis;
	static StringBuilder builder;
	
	private static void permutation(int cnt) {
		if(cnt == operSize) {
			builder.setLength(0); // 초기화
			Arrays.fill(parenthesis, false);
			
			for(int i=0; i<operSize; i++) {
				if(selected[i]) {
					int index = 2*i + 1;
					
					parenthesis[index-1] = true; // 수식 전부 체크
					parenthesis[index] = true;
					parenthesis[index+1] = true;
				}
			}
			
			for(int i=0; i<parenthesis.length; i++) {
				if(!parenthesis[i]) builder.append(input[i]);
				else {
					int left = input[i] - '0';
					char oper = input[i+1];
					int right = input[i+2] - '0';
					
					builder.append(calculate(left, oper, right));
					i += 2;
				}
			}
			
			char[] newInput = builder.toString().toCharArray();
			int prev = calculate(newInput[0]-'0', newInput[1], newInput[2]-'0');
			
			for(int i=3; i<newInput.length; i+=2) {
				prev = calculate(prev, newInput[i], newInput[i+1]);
			}
			
			maxAns = Math.max(maxAns, prev);
			return;
		}
		
		for(int i=0; i<operSize; i++) {
			if(!selected[i]) {
				selected[i] = true;
				permutation(cnt+1);
				selected[i] = false;
			}
		}
	}
	
	private static int calculate(int left, char oper, int right) {
		if(oper == '+') return left + right;
		else if(oper == '-') return left - right;
		else return left * right;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		input = br.readLine().toCharArray();
		
		operSize = N / 2;
		
		maxAns = Integer.MIN_VALUE;
		selected = new boolean [operSize];
		parenthesis = new boolean [N];
		builder = new StringBuilder();
		
		permutation(0);
		
		System.out.println(maxAns);
	}

}
