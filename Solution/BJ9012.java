package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ9012 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		cp : for(int i=0; i<T; i++) {
			char[] input = br.readLine().toCharArray();
			
			Stack<Character> stack = new Stack<>();
			for(char c : input) {
				if(c == '(') { // 여는 괄호라면 스택에 저장
					stack.push(c);
				}
				else { // 닫는 괄호 라면
					if(stack.isEmpty()) { // 여는 괄호가 없다면
						System.out.println("NO");
						continue cp;
					}
					else { // 있다면
						stack.pop(); // 여는 괄호 하나 뽑는다
						continue;
					}
				}
			}
			
			if(stack.isEmpty()) { // VPS를 만족한다면
				System.out.println("YES");
				continue cp;
			}
			else { // 만족하지 않는다면
				System.out.println("NO");
				continue cp;
			}
		}
	}
}
