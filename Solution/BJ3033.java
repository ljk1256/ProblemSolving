package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ3033 {
	
static class Suffix implements Comparable<Suffix>{
		
		int index;
		int rank;
		int next_rank;
		
		public Suffix(int index, int rank, int next_rank) {
			super();
			this.index = index;
			this.rank = rank;
			this.next_rank = next_rank;
		}

		@Override
		public int compareTo(Suffix o) {
			if(this.rank != o.rank) {
				return this.rank - o.rank;
			}
			else {
				return this.next_rank - o.next_rank;
			}
		}
		
	}
	
	private static int[] makeSuffixArray(String input) {
		int input_length = input.length();
		Suffix[] sa = new Suffix [input_length];
		
		for(int i=0; i<sa.length; i++) {
			sa[i] = new Suffix(i, input.charAt(i) - 'A', 0); // 초기값 셋팅
		}
		
		for(int i=0; i<sa.length; i++) {
			sa[i].next_rank = sa[i].index + 1 < input_length ? sa[sa[i].index + 1].rank : -1; 
		}
		
		Arrays.sort(sa);
		
		int[] group = new int [input_length];
		
		for(int lenght=2; lenght<=input_length; lenght <<= 1) {
			
			int rank = 0, prev = sa[0].rank;
			sa[0].rank = rank;
			group[sa[0].index] = 0;
			
			for(int i=1; i<group.length; i++) {
				if(sa[i].rank == prev && sa[i-1].next_rank == sa[i].next_rank) {
					prev = sa[i].rank;
					sa[i].rank = rank;
				}
				else {
					prev = sa[i].rank;
					sa[i].rank = ++rank;
				}
				
				group[sa[i].index] = i;
			}
			
			for(int i=0; i<sa.length; i++) {
				int new_next = sa[i].index + lenght;
				sa[i].next_rank = new_next < input_length ? sa[group[new_next]].rank : -1;
			}
			
			Arrays.sort(sa);
		}
		
		int[] suffix_array = new int [input_length];
		for(int i=0; i<suffix_array.length; i++) {
			suffix_array[i] = sa[i].index;
		}
		
		return suffix_array;
	}
	
	private static int[] makeLCP(String input) {
		char[] text = input.toCharArray();
		int[] suffix_array = makeSuffixArray(input);
		int[] inverse = new int [input.length()];
		int[] LCP = new int [input.length()];
		
		for(int i=0; i<inverse.length; i++) {
			inverse[suffix_array[i]] = i; 
		}
		
		for(int i=0, j=0; i<input.length(); i++, j=Math.max(j-1, 0)) { // 매 루프마다 j가 >0 이라면 j--
			if(inverse[i] == input.length()-1) continue; // 마지막 한 글자 남은 접미사는 더 볼것도 없다
			
			for(int k=suffix_array[inverse[i]+1]; Math.max(i+j, k+j) < input.length() && text[i+j] == text[k+j]; j++); // 제일 긴 접미사부터 본다
			
			LCP[inverse[i]] = j;
		}
		return LCP;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		String input = br.readLine();
		
		int answer = 0;
		int[] lcp = makeLCP(input);
		for(int i : lcp) {
			if(i > answer) answer = i;
		}
		
		System.out.println(answer);
	}

}
