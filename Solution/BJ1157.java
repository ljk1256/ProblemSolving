package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BJ1157 {
	
	static class Alpha {
		
		int idx;
		int cnt;
		
		public Alpha(int idx, int cnt) {
			super();
			this.idx = idx;
			this.cnt = cnt;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		int[] words = new int [26];
		
		for(int i=0; i<input.length(); i++) {
			char c = input.charAt(i);
			
			if('A' <= c && c <= 'Z') words[c - 'A']++;
			else words[c - 'a']++;
		}
		
		ArrayList<Alpha> maxList = new ArrayList<>();
		
		for(int i=0; i<26; i++) {
			maxList.add(new Alpha(i, words[i]));
		}
		
		Collections.sort(maxList, new Comparator<Alpha>() {
			@Override
			public int compare(Alpha o1, Alpha o2) {
				return o2.cnt - o1.cnt;
			}
		});
		
		if(maxList.size() > 1 && maxList.get(0).cnt == maxList.get(1).cnt) {
			System.out.println("?");
		}
		else {
			System.out.println((char)(maxList.get(0).idx + 'A'));
		}
	}

}
