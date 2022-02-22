package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ14889 {
	
	static int N;
	static int[][] stats;
	static boolean[] selected;
	static int answer;
	static ArrayList<Integer> startList;
	static ArrayList<Integer> linkList;
	
	private static void combination(int cnt, int idx) {
		if(cnt == N/2) {
			startList.clear();
			linkList.clear();
			
			for(int i=0; i<N; i++) {
				if(!selected[i]) startList.add(i); // false
				else linkList.add(i); // true
			}
			
			int startSum = 0;
			int linkSum = 0;
			
			for(int i=0; i<startList.size(); i++) {
				int player = startList.get(i);
				
				for(int j=0; j<N; j++) { // false 같은팀
					if(j == player) continue;
					if(!selected[j]) startSum += stats[player][j];
				}
			}
			
			for(int i=0; i<linkList.size(); i++) {
				int player = linkList.get(i);
				
				for(int j=0; j<N; j++) { // true 같은팀
					if(j == player) continue;
					if(selected[j]) linkSum += stats[player][j];
				}
			}
			
			answer = Math.min(answer, Math.abs(startSum - linkSum));
			return;
		}
		
		for(int i=idx; i<N; i++) {
			if(!selected[i]) {
				selected[i] = true;
				combination(cnt+1, i+1);
				selected[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		stats = new int [N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				stats[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Integer.MAX_VALUE;
		selected = new boolean [N];
		startList = new ArrayList<>();
		linkList = new ArrayList<>();
		
		combination(0, 0);
		
		System.out.println(answer);
	}

}
