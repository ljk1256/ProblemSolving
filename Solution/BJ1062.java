package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1062 {
	
	static boolean[] alphabet;
	static String[] words;
	static int N;
	static int K;
	static int maxAns;
	
	private static void combination(int cnt, int idx) {
		if(cnt == K) {
			int tempans = 0;
		cp : for(int i=0; i<N; i++) {
				String tempWord = words[i];
				for(int j=0; j<tempWord.length(); j++) {
					if(!alphabet[tempWord.charAt(j) - 'a']) continue cp;
				}
				tempans++;
			}
			
			maxAns = Math.max(tempans, maxAns);
			return;
		}
		
		for(int i=idx; i<26; i++) {
			if(!alphabet[i]) {
				alphabet[i] = true;
				combination(cnt+1, i+1);
				alphabet[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		words = new String [N];
		alphabet = new boolean [26];
		alphabet['a' - 'a'] = true;
		alphabet['n' - 'a'] = true;
		alphabet['t' - 'a'] = true;
		alphabet['i' - 'a'] = true;
		alphabet['c' - 'a'] = true;
		
		for(int i=0; i<N; i++) {
			words[i] = br.readLine();
		}
		
		if(K < 5) {
			System.out.println(0);
			System.exit(0);
		}
		
		maxAns = 0;
		combination(5, 0);
		
		System.out.println(maxAns);
	}

}
