package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ5076 {
	
	static StringBuilder sb;
	static StringTokenizer st;
	static String input;
	
	private static void inspection(char[] words) {
		Stack<String> stack = new Stack<>();
		for(int i=0; i<words.length; i++) {
			if(words[i] == '<') {
				int index = i+1;
				
				if(words[index] == '/') { // 닫는 태그를 만났을때
					
					if(stack.isEmpty()) { // 여는 태그가 없는데 닫는 태그를 만났다면 더 이상 볼 필요없다
						sb.append("illegal").append("\n");
						return;
					}
						
					while(words[index] != '>') index++;
					String tag = input.substring(i+2, index);
					
					st = new StringTokenizer(stack.pop()); // <a herf> 태그 가 있을 수 있으므로 태그만 파싱한다
					String htmlTag = st.nextToken();
					
					if(!tag.equals(htmlTag)) { // 다를 경우 더 이상 탐색할 필요가 없다
						sb.append("illegal").append("\n");
						return;
					}
				}
				
				else {
					boolean flag = false;
					while(words[index] != '>') {
						if(words[index] == '/' && words[index+1] == '>') flag = true; // <br /> 와 같은 태그를 만났을때
						index++;
					}
					
					if(!flag) {
						String tag = input.substring(i+1, index);
						stack.push(tag);
					}
				}
				
				i = index; // 다시 문자열 탐색으로 돌아간다
			}	
		}
		
		if(stack.isEmpty()) sb.append("legal").append("\n");
		else sb.append("illegal").append("\n");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		while(true) {
			input = br.readLine();
			if(input.equals("#")) break;
			
			char[] charArr = input.toCharArray();
			inspection(charArr);
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
