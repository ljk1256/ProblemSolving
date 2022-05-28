package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ4889 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answerBuilder = new StringBuilder();
		
		int caseCnt = 1;
		while(true) {
			String input = br.readLine();
			
			if(input.charAt(0) == '-') break;
			
			Stack<Character> stack = new Stack<>();
			int changeCnt = 0;
			
			for(int i=0; i<input.length(); i++) {
				if(input.charAt(i) == '}') {
					if(stack.isEmpty()) stack.push(input.charAt(i));
					else {
						char item = stack.pop();
						if(item != '{') {
							stack.push(item);
							stack.push(input.charAt(i));
						}
					}
				}
				
				else stack.push(input.charAt(i));
			}
			
			while(!stack.isEmpty()) { // 오른쪽이 닫는 괄호, 왼쪽이 여는 괄호 인 경우만 조건 만족
				char right = stack.pop();
				char left = stack.pop();
				
				if(right == '{') changeCnt++;
				if(left == '}') changeCnt++;
			}
			
			answerBuilder.append(caseCnt).append(". ").append(changeCnt).append("\n");
			caseCnt++;
		}
		
		answerBuilder.setLength(answerBuilder.length()-1);
		System.out.println(answerBuilder.toString());
	}

}
