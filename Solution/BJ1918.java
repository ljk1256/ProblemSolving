package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ1918 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] input = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<>();
		
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<input.length; i++) {
			
			if('A' <= input[i] && input[i] <= 'Z') builder.append(input[i]); // 문자면 바로 넣어준다
			else { // 괄호 또는 연산자를 만났다면
				switch(input[i]) { // 우선 순위대로 위에부터 같은 레벨일 경우 break를 넣지 않는다
				case '(' : {
					 stack.push(input[i]);
					 break;
				}
				case '*' : 
				case '/' : { // 같은 우선순위 레벨이거나 스택이 모두 빠질때까지 빼준다
					while(!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) builder.append(stack.pop());
					stack.push(input[i]); // 작업이 수행된 후 현재 연산자를 넣어준다
					break;
				}
				case '+' : 
				case '-' : {
					while(!stack.isEmpty() && stack.peek() != '(') builder.append(stack.pop()); // 여는 괄호 전까지 뽑아준다
					stack.push(input[i]);
					break;
				}
				case ')' : {
					while(!stack.isEmpty() && stack.peek() != '(') builder.append(stack.pop());
					stack.pop(); // '(' 여는 괄호를 제거한다
					break;
				}
				}
			}
		}
		
		while(!stack.isEmpty()) builder.append(stack.pop()); // 스택에 남아있는 문자가 있다면 전부 뽑아준다
		
		System.out.println(builder.toString());
	}

}
