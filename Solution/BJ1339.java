package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1339 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] inputs = new String [N];
		
		for(int i=0; i<N; i++) {
			inputs[i] = br.readLine();
		}
		
		int[] positional_number = new int [26];
		for(int i=0; i<N; i++) {
			int decimal = (int)Math.pow(10, inputs[i].length()-1);
			for(int j=0; j<inputs[i].length(); j++) {
				positional_number[inputs[i].charAt(j) - 'A'] += decimal; // 더 낮은 자리에 높은 수를 넣는게 더 큰 값이 올 수 있기에 자릿수로 먼저 판별한다
				decimal /= 10;
			}
		}
		
		Arrays.sort(positional_number); // 오름차순 >> 뒤에서부터 뽑는다
		int maxNum = 9, answer = 0;
		for(int i=positional_number.length-1; i>=0; i--) {
			if(positional_number[i] == 0) break;
			answer += positional_number[i] * maxNum;
			maxNum--;
		}
		
		System.out.println(answer);
      
	}
}
