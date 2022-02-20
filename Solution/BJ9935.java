package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ9935 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		
		String input = br.readLine(); // 폭발물이 포함된 문자열
		char[] bomb = br.readLine().toCharArray();
		
		Stack<Character> stack = new Stack<>(); // 어차피 문자열을 result에 넣어야하니 스택대신 빌더를 스택처럼 사용해도 된다.
		for(int i=0; i<input.length(); i++) {
			char c = input.charAt(i);
			stack.push(c);
			
			if(stack.size() >= bomb.length) { // 스택에 쌓인 문자열이 폭발물 문자열 보다 같거나 길다면 검사시작
				boolean flag = true;
				for(int j=0; j<bomb.length; j++) {
					char x = stack.get(stack.size() - bomb.length + j); // 스택에 쌓인 문자열중 검사를 시작하는 문자열인덱스
					
					if(x != bomb[j]) { // 검사 도중 다른 문자열 이라면 중단
						flag = false;
						break;
					}
				}
				
				if(flag) { // 폭발물이라면 스택에서 해당 문자열 제외
					for(int j=0; j<bomb.length; j++) {
						stack.pop();
					}
				}
				
			}
		}
		
		if(stack.isEmpty()) { // 모두 탐색 후 남아있는 문자가 없다면
			System.out.println("FRULA");
		}
		
		for(char c : stack) { // 남은 문자열 저장
			result.append(c);
		}
		System.out.println(result.toString());
	}

}
