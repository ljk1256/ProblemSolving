package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ16566 {
	
	static int[] deck;
	
	private static int binarySearch(int number) {
		int left = 0, mid = 0, right = deck.length-1;
		
		while(left < right) {
			mid = (left + right) / 2;
			
			if(deck[mid] < number) left = mid+1;
			else {
				if(mid-1 > 0) right = mid-1;
				else right = mid;
			}
		}
		
		return right;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		deck = new int [M];
		boolean[] selected = new boolean [M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			deck[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int[] game = new int [K];
		for(int i=0; i<K; i++) {
			game[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		Arrays.sort(deck);
		
		for(int i=0; i<K; i++) {
			int index = binarySearch(game[i]);
			
			while(index < deck.length) {
				if(game[i] < deck[index] && !selected[index]) {
					selected[index] = true;
					break;
				}
				else index++;
			}
			
			sb.append(deck[index]).append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
