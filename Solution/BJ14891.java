package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14891 {
	
	static class Gear {
		
		int leftIdx;
		int rightIdx;
		int[] table;
		
		public Gear(int leftIdx, int rightIdx, int[] table) {
			super();
			this.leftIdx = leftIdx;
			this.rightIdx = rightIdx;
			this.table = table;
		}
		
	}
	
	static ArrayList<Gear> gearList;
	static int[] rotateTable;
	
	private static void rotate(int gearNum, int isClock) {
		// 다를 경우 반대 방향으로 회전
		int mainIdx = gearNum;
		int mainClock = isClock;
		for(int i=gearNum-1; i>=0; i--) { // 왼쪽 기어들 확인
			Gear tempGear = gearList.get(i);
			
			if(gearList.get(mainIdx).table[gearList.get(mainIdx).leftIdx] != tempGear.table[tempGear.rightIdx]) { // 서로 극이 달라 회전 하는 경우
				if(mainClock == 1) rotateTable[i] = -1;
				else rotateTable[i] = 1;
				
				mainIdx = i;
				mainClock = rotateTable[i];
			}
			
			else break; // 영향이 없다면 다음 기어부터는 움직이지 않음
		}
		
		mainIdx = gearNum;
		mainClock = isClock;
		for(int i=gearNum+1; i<4; i++) { // 오른쪽 기어들 확인
			Gear tempGear = gearList.get(i);
			
			if(gearList.get(mainIdx).table[gearList.get(mainIdx).rightIdx] != tempGear.table[tempGear.leftIdx]) { // 서로 극이 달라 회전 하는 경우
				if(mainClock == 1) rotateTable[i] = -1;
				else rotateTable[i] = 1;
				
				mainIdx = i;
				mainClock = rotateTable[i];
			}
			
			else break; // 영향이 없다면 다음 기어부터는 움직이지 않음
		}
		
		rotateTable[gearNum] = isClock; // 원래 돌리고자 한 기어도 테이블에 저장
		
		// 돌릴때 left, right 전부 갱신 해줘야 한다.
		for(int i=0; i<4; i++) {
			if(rotateTable[i] == 1) {
				gearList.get(i).leftIdx = (gearList.get(i).leftIdx + 7) % 8;
				gearList.get(i).rightIdx = (gearList.get(i).rightIdx + 7) % 8;
			}
			
			else if(rotateTable[i] == -1) {
				gearList.get(i).leftIdx = (gearList.get(i).leftIdx + 1) % 8;
				gearList.get(i).rightIdx = (gearList.get(i).rightIdx + 1) % 8;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		gearList = new ArrayList<>();
		
		for(int i=0; i<4; i++) {
			String input = br.readLine();
			int[] table = new int [8];
			
			for(int j=0; j<8; j++) {
				table[j] = input.charAt(j) - '0';
			}
			
			int leftIdx = 6;
			int rightIdx = 2;
			
			gearList.add(new Gear(leftIdx, rightIdx, table));
		}
		
		int commandcnt = Integer.parseInt(br.readLine());
		rotateTable = new int [4];
		
		while(commandcnt-- > 0) {
			st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken()) - 1;
			int isClock = Integer.parseInt(st.nextToken()); // 1 시계방향, -1 반시계
			
			Arrays.fill(rotateTable, 0); // 매번 초기화
			
			rotate(gearNum, isClock);
		}
		
		int answer = 0;
		
		for(int i=0; i<4; i++) {
			Gear tempGear = gearList.get(i);
			
			int twelve = (tempGear.rightIdx + 6) % 8;
			
			if(i == 0 && tempGear.table[twelve] == 1) answer += 1;
			else if(i == 1 && tempGear.table[twelve] == 1) answer += 2;
			else if(i == 2 && tempGear.table[twelve] == 1) answer += 4;
			else if(i == 3 && tempGear.table[twelve] == 1) answer += 8;
		}
		
		System.out.println(answer);
	}

}
