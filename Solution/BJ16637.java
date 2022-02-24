package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BJ16637 {
	
	static int operSize;
	static int maxAns;
	static char[] input;
	static boolean[] selected; // true 일 경우 괄호 사용
	static boolean[] parenthesis;
	static ArrayList<String> builder;
	
	private static void permutation(int idx) {
		if(idx >= operSize) {
			builder.clear(); // 초기화
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
				if(!parenthesis[i]) builder.add(String.valueOf(input[i]));
				else {
					int left = input[i] - '0';
					char oper = input[i+1];
					int right = input[i+2] - '0';
					int result = calculate(left, oper, right);
					builder.add(String.valueOf(result));
					i += 2;
				}
			}
			
			if(builder.size() == 1) { // 항상 모든 조건을 생각해야 한다
				maxAns = Math.max(maxAns, Integer.parseInt(builder.get(0)));
				return;
			}
			
			int prev = calculate(Integer.parseInt(builder.get(0)), builder.get(1).charAt(0), Integer.parseInt(builder.get(2)));
			
			for(int i=3; i<builder.size(); i+=2) {
				prev = calculate(prev, builder.get(i).charAt(0), Integer.parseInt(builder.get(i+1)));
			}
			
			maxAns = Math.max(maxAns, prev);
			return;
		}
		
		for(int i=idx; i<operSize; i++) {
			if(!selected[i]) {
				selected[i] = true;
				permutation(i+2);
				
				selected[i] = false;
				permutation(i+2);
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
		
		if(N == 1) {
			System.out.println(input[0]);
			System.exit(0);
		}
		
		maxAns = Integer.MIN_VALUE;
		selected = new boolean [operSize];
		parenthesis = new boolean [N];
		builder = new ArrayList<>();
		
		permutation(0);
		
		System.out.println(maxAns);
	}

}
