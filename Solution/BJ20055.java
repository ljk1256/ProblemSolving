package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ20055 {
	
	static class Robot {
		
		int x;

		public Robot(int x) {
			super();
			this.x = x;
		}
		
	}
	
	static class Conveyor {
		
		int x;
		int y;
		boolean isRobot;
		int durability;
		
		public Conveyor(int x, int y, boolean isRobot, int durability) {
			super();
			this.x = x;
			this.y = y;
			this.isRobot = isRobot;
			this.durability = durability;
		}
		
	}
	
	static int N, K;
	static int level; // 누적 단계
	static Queue<Robot> robotQ; // 로봇이 올려진 순서대로 들어간 큐
	static Conveyor[][] belt; // 컨베이어 벨트
	static ArrayList<Conveyor> convList; // 벨트 위에 있는 정보를 담은 리스트
	
	private static void rotate() {
		convList.clear();
		for(int i=0; i<2; i++) {
			for(int j=0; j<N; j++) {
				convList.add(belt[i][j]);
			}
		}
		
		for(int i=0; i<convList.size(); i++) {
			Conveyor temp = convList.get(i);
			if(temp.x == 0) {
				if(temp.y < N-1) temp.y++;
				else temp.x++;
			}
			else {
				if(temp.y > 0) temp.y--;
				else temp.x--;
			}
			
			belt[temp.x][temp.y] = temp;
		}
	}
	
	private static void moveRobot() {
		int size = robotQ.size();
		
		for(int i=0; i<size; i++) {
			Robot temp = robotQ.poll();
			temp.x++; // 컨베이어와 함께 한칸 이동
			
			if(temp.x == N-1) {
				belt[0][temp.x].isRobot = false;
				continue; // 내리는 위치면 내려준다
			}
			
			if(!belt[0][temp.x+1].isRobot && belt[0][temp.x+1].durability >= 1) {
				belt[0][temp.x+1].isRobot = true;
				belt[0][temp.x+1].durability -= 1;
				temp.x++;
				
				if(temp.x < N-1) {
					belt[0][temp.x-1].isRobot = false;
					robotQ.offer(temp);
				}
				else {
					belt[0][temp.x].isRobot = false;
					belt[0][temp.x-1].isRobot = false;
				}
				continue;
			}
			
			robotQ.offer(temp);
		}
		
	}
	
	private static void makeNewRobot() {
		Conveyor startPoint = belt[0][0];
		if(!startPoint.isRobot && startPoint.durability > 0) {
			robotQ.offer(new Robot(0));
			startPoint.isRobot = true;
			startPoint.durability -= 1;
		}
	}
	
	private static boolean isBreak() {
		int cnt = 0;
		for(int i=0; i<2; i++) {
			for(int j=0; j<N; j++) {
				if(belt[i][j].durability == 0) cnt++;
			}
		}
		
		if(cnt >= K) return true;
		else return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		belt = new Conveyor [2][N];
		robotQ = new LinkedList<>();
		convList = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) { // 첫번째 라인 입력
			Conveyor newCon = new Conveyor(0, i, false, Integer.parseInt(st.nextToken()));
			belt[0][i] = newCon;
		}
		
		for(int i=N-1; i>=0; i--) { // 두번째 라인 입력
			Conveyor newCon = new Conveyor(1, i, false, Integer.parseInt(st.nextToken()));
			belt[1][i] = newCon;
		}
		
		level = 0;
		while(true) {
			level++;
			rotate();
			moveRobot();
			makeNewRobot();
			if(isBreak()) break;
		}
		
		System.out.println(level);
	}

}
