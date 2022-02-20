package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1759 {
	
	static int L;
	static int C;
	static String[] s;
	static String[] selected;
	
	public static void combination(int cnt, int index) {
		
		if(cnt == L) {
			int vowel = 0, consonant = 0;
			for(int i=0; i<L; i++) {
				if(selected[i].equals("a") || selected[i].equals("e") || selected[i].equals("i") || selected[i].equals("o") || selected[i].equals("u")) {
					vowel++;
				}
				else {
					consonant++;
				}
			}
			
			if(vowel >= 1 && consonant >=2) {
				for(String t : selected) {
					System.out.print(t);
				}
				System.out.println();
			}
			return;
		}
		
		for(int i=index; i<C; i++) {
			selected[cnt] = s[i];
			combination(cnt+1, i+1);
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		s = br.readLine().split(" ");
		Arrays.sort(s);
		
		selected = new String [L];
		combination(0, 0);
	}

}
