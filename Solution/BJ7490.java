package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ7490 {
	
	static StringBuilder answerBuilder;
	static StringTokenizer tokenizer;
	static int N;
	static String[] origin;
	static String[] copy;
	static String[] oper = {" ", "+", "-"};
	static String[] selected;
	
	private static int operation(int left, String oper, int right) {
		int result = 0;
		
		if(oper.equals("+")) result = left + right;
		else result = left - right;
		
		return result;
	}
	
	private static void comb(int cnt, int end) {
		if(cnt == end) {
			System.arraycopy(origin, 0, copy, 0, origin.length);
			
			int index = 0;
			
			for(int i=1; i<copy.length; i+=2) {
				copy[i] = selected[index];
				index++;
			}
			
			String math = "";
			
			for(int i=0; i<copy.length; i++) {
				if(copy[i].equals(" ")) continue;
				else math += copy[i];
			}
			
			tokenizer = new StringTokenizer(math, "+-", true);
			
			int result = 0;
			
			if(tokenizer.countTokens() == 1) result = Integer.parseInt(math);
			
			else {
				result = operation(Integer.parseInt(tokenizer.nextToken()), tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()));
				
				while(tokenizer.hasMoreTokens()) {
					result = operation(result, tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()));
				}
			}
			
			if(result == 0) {
				for(int i=0; i<copy.length; i++) {
					answerBuilder.append(copy[i]);
				}
				answerBuilder.append("\n");
			}
			
			return;
		}
		
		for(int i=0; i<3; i++) {
			selected[cnt] = oper[i];
			comb(cnt+1, end);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testcase = Integer.parseInt(br.readLine());
		answerBuilder = new StringBuilder();
		
		for(int i=0; i<testcase; i++) {
			N = Integer.parseInt(br.readLine());
			
			origin = new String [2*N-1];
			copy = new String [2*N-1];
			selected = new String [N-1];
			
			int number = 1;
			
			for(int j=0; j<origin.length; j+=2) {
				origin[j] = String.valueOf(number);
				number++;
			}
			
			comb(0, N-1);
			
			answerBuilder.append("\n");
		}
		
		answerBuilder.setLength(answerBuilder.length()-1);
		System.out.println(answerBuilder.toString());
	}

}
