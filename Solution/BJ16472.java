package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ16472 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		char[] input = br.readLine().toCharArray();
		int[] words = new int [26];
		int left = 0, right = 0, prevLeft = 0;
		int maxAns = 0, kind = 0;
		
		if(input.length <= N) {
			System.out.println(input.length);
			System.exit(0);
		}
		
		while(left <= right && right < input.length) {
			if(words[input[right] - 'a'] == 0) { // set의 사이즈만으로 판단한다면 abab 사이즈는 바뀌지 않지만 문자는 계속 바뀐다
				kind++;
			}
			
			if((left != right || right == 0) && prevLeft == left) words[input[right] - 'a']++;
			
			if(kind <= N) {
				maxAns = Math.max(maxAns, right - left + 1);
				right++;
				prevLeft = left;
				continue;
			}
			
			words[input[left] - 'a']--;
			if(words[input[left] - 'a'] == 0) kind--;
			left++;
		}
		
		System.out.println(maxAns);
	}

}
