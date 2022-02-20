package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2941 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] input = br.readLine().toCharArray();
		int cnt = 0;
		
		for(int i=0; i<input.length; i++) {
			if(i != input.length-1) {
				if(input[i] == 'c' && (input[i+1] == '=' || input[i+1] == '-')) {
					cnt++;
					i++;
				}
				else if(input[i] == 'd') {
					if(i+2 < input.length && input[i+1] == 'z' && input[i+2] == '=') {
						cnt++;
						i += 2;
					}
					else if(input[i+1] == '-') {
						cnt++;
						i++;
					}
					else cnt++;
				}
				else if((input[i] == 'l' && input[i+1] == 'j') || (input[i] == 'n' && input[i+1] == 'j')) {
					cnt++;
					i++;
				}
				else if((input[i] == 's' || input[i] == 'z') && input[i+1] == '=') {
					cnt++;
					i++;
				}
				else cnt++;
			}
			
			else cnt++;
		}
		
		System.out.println(cnt);
	}

}
