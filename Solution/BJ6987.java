package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ6987 {
	
	static int[] checkWin;
	static int[] checkLose;
	
	private static boolean isMeetCondition() {
		int winPoint = 0, losePoint = 0;
		for(int i=1; i<6; i++) {
			if(checkWin[i] != 0) winPoint += checkWin[i] * (i+1);
			if(checkLose[i] != 0) losePoint += checkLose[i] * (i+1);
		}
		
		if(winPoint != losePoint) return false;
		else return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			HashSet<Integer> drawSet = new HashSet<>();
			checkWin = new int [6];
			checkLose = new int [6];
			boolean flag = true;
			
			for(int j=0; j<6; j++) {
				int win = Integer.parseInt(st.nextToken());
				int draw = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());
				
				if(win + lose + draw != 5) {
					flag = false;
					break;
				}
				
				else {
					if(draw != 0) drawSet.add(j);
					if(win != 0) checkWin[win]++;
					if(lose != 0) checkLose[lose]++;
				}
			}
			
			if(!flag || drawSet.size()%2 != 0 || !isMeetCondition()) answer.append(0).append(" ");
			else answer.append(1).append(" ");
		}
		
		answer.setLength(answer.length()-1);
		System.out.println(answer.toString());
	}

}
