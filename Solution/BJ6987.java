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
		for(int i=0; i<6; i++) {
			if(checkWin[i] != checkLose[i]) return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		boolean flag = true;
		
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			HashSet<Integer> drawSet = new HashSet<>();
			checkWin = new int [6];
			checkLose = new int [6];
			
			for(int j=0; j<6; j++) {
				int win = Integer.parseInt(st.nextToken());
				int draw = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());
				
				if(win + lose + draw != 5) {
					flag = false;
					break;
				}
				
				else {
					drawSet.add(j);
					checkWin[win]++;
					checkLose[lose]++;
				}
			}
			
			if(!flag || drawSet.size()/2 != 0 || !isMeetCondition()) answer.append(0).append(" ");
			else answer.append(1).append(" ");
		}
		
		answer.setLength(answer.length()-1);
		System.out.println(answer.toString());
	}

}
