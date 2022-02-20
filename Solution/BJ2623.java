package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ2623 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[] totalCheck = new boolean [N+1];
		boolean[][] pdCheck = new boolean [M+1][N+1]; // true 면 안되는거
		ArrayList<Integer>[] list = new ArrayList [M+1];
		
		for(int i=1; i<M+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=1; i<M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<cnt; j++) {
				int n = Integer.parseInt(st.nextToken());
				list[i].add(n);
				if(j != 0) pdCheck[i][n] = true;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		
		while(true) { // 하나 골랐을때 해당 값 false부터 해야한다
			flag = false;
			
			for(int i=1; i<M+1; i++) {
				if(list[i].isEmpty()) continue;
				
				int next = list[i].get(0);
				pdCheck[i][next] = false;
				
				if(totalCheck[next]) {
					list[i].remove(0);
					continue;
				}
				
				boolean isNext = true; // true가 더 진행가능
				for(int j=1; j<M+1; j++) {
					if(pdCheck[j][next]) {
						isNext = false;
						break;
					}
				}
				
				if(isNext) {
					totalCheck[next] = true;
					sb.append(next).append(" ");
					list[i].remove(0);
					flag = true;
					
					for(int j=1; j<M+1; j++) {
						if(!list[j].isEmpty() && list[j].get(0) == next) {
							list[j].remove(0);
							if(!list[j].isEmpty()) {
								int n = list[j].get(0);
								pdCheck[j][n] = false;
							}
						}
					}
					break;
				}
			}
			
			if(!flag) break;
		}
		
		for(int i=1; i<N+1; i++) { // 문제를 정확하게 해석해야 하는 이유 : pd가 순서를 정하지 않아도 가수를 나열해야한다!
			if(!totalCheck[i]) {
				for(int j=1; j<M+1; j++) {
					if(pdCheck[j][i]) {
						System.out.println(0);
						return;
					}
				}
				
				sb.append(i).append(" ");
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
