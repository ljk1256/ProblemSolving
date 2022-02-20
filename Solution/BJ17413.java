package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17413 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		StringBuilder temp;
		
		char[] input = br.readLine().toCharArray();
		for(int i=0; i<input.length; i++) {
			
			if(input[i] == '<') { // < 태그를 만난 경우
				
				while(input[i] != '>') {
					output.append(input[i]);
					i++;
					
					if(i == input.length) {
						i -= 1;
						break;
					}
				}
				
				output.append(input[i]); // > 괄호 닫는거 넣어준다
				continue;
			}
			
			else if(input[i] == ' ') { // 공백일 경우
				output.append(input[i]);
				continue;
			}
			
			else { // 단어일 경우
				temp = new StringBuilder();
				while(input[i] != ' ' && input[i] != '<') { // 태그나 다음 단어가 나올때까지
					temp.append(input[i]);
					i++;
					
					if(i == input.length) {
						i -= 1;
						break;
					}
						
				}
				
				temp.reverse();
				output.append(temp.toString()); // 뒤집은 문자 넣기
				
				if(input[i] == ' ') {
					output.append(' ');
					continue;
				}
				
				else if(input[i] == '<') {
					i -= 1;
					continue;
				}
			}
			
		}
		
		System.out.println(output.toString());
	}

}
