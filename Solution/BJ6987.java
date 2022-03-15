package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ6987 {
	
	static ArrayList<int []> list;
	static int[][] info;
	static boolean isValid;
	
	private static void backTracking(int cnt) {
		if(isValid) return; // 다른 경우에서 이미 유효한 대진이라면 더 이상 탐색할 필요없다
		
		if(cnt == 15) {
			isValid = true;
			return;
		}
		
		int[] temp = list.get(cnt);
		
		int team1 = temp[0];
		int team2 = temp[1];
		
		if(info[team1][0] > 0 && info[team2][2] > 0) { // 1팀이 이기고 2팀이 지는 경우
			info[team1][0]--;
			info[team2][2]--;
			
			backTracking(cnt+1);
			
			info[team1][0]++;
			info[team2][2]++;
		}
		
		if(info[team1][1] > 0 && info[team2][1] > 0) { // 무승부인 경우
			info[team1][1]--;
			info[team2][1]--;
			
			backTracking(cnt+1);
			
			info[team1][1]++;
			info[team2][1]++;
		}
		
		if(info[team1][2] > 0 && info[team2][0] > 0) { // 1팀이 지고 2팀이 이기는 경우
			info[team1][2]--;
			info[team2][0]--;
			
			backTracking(cnt+1);
			
			info[team1][2]++;
			info[team2][0]++;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		
		list = new ArrayList<>();
		info = new int [6][3];
		
		for(int i=0; i<6; i++) { // 대진표 만들기
			for(int j=i+1; j<6; j++) {
				list.add(new int [] {i, j});
			}
		}
		
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			boolean isPossible = true;
			
			for(int j=0; j<6; j++) {
				int win = Integer.parseInt(st.nextToken());
				int draw = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());
				
				info[j][0] = win;
				info[j][1] = draw;
				info[j][2] = lose;
				
				if(win + draw + lose != 5) {
					isPossible = false;
					break;
				}
			}
			
			if(isPossible) { // 일단 기본조건이 충족했다면 탐색을 들어가본다
				isValid = false;
				
				backTracking(0);
				
				if(isValid) answer.append(1).append(" ");
				else answer.append(0).append(" ");
			}
			else answer.append(0).append(" ");
		}
		
		answer.setLength(answer.length()-1);
		System.out.println(answer.toString());
	}

}
