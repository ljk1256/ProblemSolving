package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ9248 {
	
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
			if(this.rank != o.rank) return this.rank - o.rank;
			else return this.next_rank - o.next_rank;
		}
		
	}
	
	private static int[] makeSuffix(char[] input) {
		int inputSize = input.length;
		Suffix[] suffixes = new Suffix [inputSize];
		
		for(int i=0; i<inputSize; i++) {
			suffixes[i] = new Suffix(i, input[i] - 'A', 0);
		}
		
		for(int i=0; i<inputSize; i++) {
			suffixes[i].next_rank = suffixes[i].index + 1 < inputSize ? suffixes[suffixes[i].index + 1].rank : -1;
		}
		
		Arrays.sort(suffixes);
		
		int[] group = new int [inputSize];
		
		for(int length=2; length<inputSize; length <<= 1) {
			int rank = 0, prev = suffixes[0].rank;
			suffixes[0].rank = rank; // 위에 이전 값 복사 해뒀으니 이제 0으로 초기화 뒤 새로운 그룹으로 묶어준다
			group[suffixes[0].index] = 0;
			
			for(int i=1; i<inputSize; i++) {
				if(suffixes[i].rank == prev && suffixes[i-1].next_rank == suffixes[i].next_rank) {
					prev = suffixes[i].rank;
					suffixes[i].rank = rank;
				}
				
				else {
					prev = suffixes[i].rank;
					suffixes[i].rank = ++rank;
				}
				
				group[suffixes[i].index] = i;
			}
			
			for(int i=0; i<inputSize; i++) {
				int newnext = suffixes[i].index + length;
				suffixes[i].next_rank = newnext < inputSize ? suffixes[group[newnext]].rank : -1;
			}
			
			Arrays.sort(suffixes);
		}
		
		int[] result = new int [inputSize];
		for(int i=0; i<inputSize; i++) {
			result[i] = suffixes[i].index;
		}
		
		return result;
	}
	
	private static int[] makeLcp(int[] suffix, char[] input) {
		int[] inverse = new int [suffix.length];
		int[] lcp = new int [suffix.length];
		
		for(int i=0; i<suffix.length; i++) {
			inverse[suffix[i]] = i; 
		}
		
		for(int i=0, j=0; i<suffix.length; i++, j=Math.max(j-1, 0)) {
			if(inverse[i] == suffix.length-1) continue;
			
			for(int k=suffix[inverse[i]+1]; Math.max(i+j, k+j) < suffix.length && input[i+j] == input[k+j]; j++);
			
			lcp[inverse[i]] = j;
		}
		
		return lcp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		
		int[] suffix = makeSuffix(input);
		int[] lcp = makeLcp(suffix, input);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<suffix.length; i++) {
			sb.append(suffix[i]+1).append(" ");
		}
		sb.setLength(sb.length()-1);
		
		sb.append("\n").append("x ");
		for(int i=0; i<lcp.length-1; i++) {
			sb.append(lcp[i]).append(" ");
		}
		sb.setLength(sb.length()-1);
		
		System.out.println(sb.toString());
	}

}
