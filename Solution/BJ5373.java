package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ5373 {
	
	static class Rotate {
		
		char dest;
		boolean isRow; // row true, column false
		int index;
		char[] colors;
		
		public Rotate(char dest, boolean isRow, int index, char[] colors) {
			super();
			this.dest = dest;
			this.isRow = isRow;
			this.index = index;
			this.colors = colors;
		}
		
	}
	
	static ArrayList<String> cmList = new ArrayList<>();
	static HashMap<Character, Integer> table;
	static HashMap<Integer, Character> resetChar;
	static HashMap<Character, Character> resetColor;
	static ArrayList<Rotate> rotateList;
	static char[][][] cube;
	
	private static void cubeReset() {
		for(int i=0; i<6; i++) {
			char viewPoint = resetChar.get(i);
			char color = resetColor.get(viewPoint);
			int index = table.get(viewPoint);
			
			for(int j=0; j<3; j++) {
				Arrays.fill(cube[index][j], color);
			}
		}
	}
	
	private static void rotateCube(String command) {
		char viewPoint = command.charAt(0);
		char dir = command.charAt(1);
		rotateList.clear();
		
		switch(viewPoint) {
		case 'F' : { // 행 true 열 false
			if(dir == '+') {
				makeRotate('L', false, 2, 'U', true, 2, true); // L 2번째 열 >> U 2번째 행으로
				makeRotate('U', true, 2, 'R', false, 0, false); // U 2번째 행 >> R 0번째 열으로
				makeRotate('R', false, 0, 'D', true, 2, true); // R 0번째 열 >> D 2번째 행으로
				makeRotate('D', true, 2, 'L', false, 2, false); // D 2번째 행 >> L 2번째 열으로				
			}
			else {
				makeRotate('U', true, 2, 'L', false, 2, true); // L 2번째 열 >> U 2번째 행으로
				makeRotate('R', false, 0, 'U', true, 2, false); // U 2번째 행 >> R 0번째 열으로
				makeRotate('D', true, 2, 'R', false, 0, true); // R 0번째 열 >> D 2번째 행으로
				makeRotate('L', false, 2, 'D', true, 2, false); // D 2번째 행 >> L 2번째 열으로	
			}
			break;
		}
		case 'B' : { // 행 true 열 false
			if(dir == '+') {
				makeRotate('R', false, 2, 'U', true, 0, false); // R 2번째 열 >> U 0번째 행
				makeRotate('U', true, 0, 'L', false, 0, true); // U 0번째 행 >> L 0번째 열
				makeRotate('L', false, 0, 'D', true, 0, false); // L 0번째 열 >> D 0번째 행
				makeRotate('D', true, 0, 'R', false, 2, true); // D 0번째 행 >> R 2번째 열				
			}
			else {
				makeRotate('U', true, 0, 'R', false, 2, false); // 반대로
				makeRotate('L', false, 0, 'U', true, 0, true); 
				makeRotate('D', true, 0, 'L', false, 0, false); 
				makeRotate('R', false, 2, 'D', true, 0, true); 	
			}
			break;
		}
		case 'L' : { // 행 true 열 false
			if(dir == '+') {
				makeRotate('U', false, 0, 'F', false, 0, false); // U 0번째 열 >> F 0번째 열
				makeRotate('F', false, 0, 'D', false, 0, true); // F 0번째 열 >> D 0번째 열
				makeRotate('D', false, 0, 'B', false, 0, false); // D 0번째 열 >> B 0번째 열
				makeRotate('B', false, 0, 'U', false, 0, true); // B 0번째 열 >> U 0번째 열				
			}
			else {
				makeRotate('F', false, 0, 'U', false, 0, false); // 반대로
				makeRotate('D', false, 0, 'F', false, 0, true); 
				makeRotate('B', false, 0, 'D', false, 0, false); 
				makeRotate('U', false, 0, 'B', false, 0, true); 	
			}
			break;
		}
		case 'R' : { // 행 true 열 false
			if(dir == '+') {
				makeRotate('U', false, 2, 'B', false, 2, true); // U 2번째 열 >> B 2번째 열
				makeRotate('B', false, 2, 'D', false, 2, false); // B 2번째 열 >> D 2번째 열
				makeRotate('D', false, 2, 'F', false, 2, true); // D 2번째 열 >> F 2번째 열
				makeRotate('F', false, 2, 'U', false, 2, false); // F 2번째 열 >> U 2번째 열				
			}
			else {
				makeRotate('B', false, 2, 'U', false, 2, true); // 반대로
				makeRotate('D', false, 2, 'B', false, 2, false); 
				makeRotate('F', false, 2, 'D', false, 2, true); 
				makeRotate('U', false, 2, 'F', false, 2, false); 	
			}
			break;
		}
		case 'U' : { // 행 true 열 false
			if(dir == '+') {
				makeRotate('B', true, 0, 'R', true, 0, true); // B 0번째 행 >> R 0번째 행
				makeRotate('R', true, 0, 'F', true, 0, false); // R 0번째 행 >> F 0번째 행
				makeRotate('F', true, 0, 'L', true, 0, false); // F 0번째 행 >> L 0번째 행
				makeRotate('L', true, 0, 'B', true, 0, true); // L 0번째 행 >> B 0번째 행				
			}
			else {
				makeRotate('R', true, 0, 'B', true, 0, true); // 반대로
				makeRotate('F', true, 0, 'R', true, 0, false); 
				makeRotate('L', true, 0, 'F', true, 0, false); 
				makeRotate('B', true, 0, 'L', true, 0, true); 	
			}
			break;
		}
		case 'D' : { // 행 true 열 false
			if(dir == '+') { // 잠시 수정
				makeRotate('F', true, 2, 'R', true, 2, false); // F 2번째 행 >> R 2번째 행
				makeRotate('R', true, 2, 'B', true, 2, true); // R 2번째 행 >> B 2번째 행
				makeRotate('B', true, 2, 'L', true, 2, true); // B 2번째 행 >> L 2번째 행
				makeRotate('L', true, 2, 'F', true, 2, false); // L 2번째 행 >> F 2번째 행				
			}
			else {
				makeRotate('R', true, 2, 'F', true, 2, false); // 반대로
				makeRotate('B', true, 2, 'R', true, 2, true); 
				makeRotate('L', true, 2, 'B', true, 2, true); 
				makeRotate('F', true, 2, 'L', true, 2, false); 	
			}
			break;
		}
		
		}
		
		cubing(command); // 큐브를 돌린다
	}
	
	private static void makeRotate(char srcPoint, boolean srcIsRow, int srcIdx, char destPoint, boolean destIsRow, int destIdx, boolean isReverse) { // 순서가 반드시 0 ~ 2 가 아니다
		Rotate temp = new Rotate(destPoint, destIsRow, destIdx, new char [3]);
		int index = table.get(srcPoint);
		
		if(srcIsRow) {
			if(isReverse) for(int i=0, j=2; i<3; i++, j--) temp.colors[i] = cube[index][srcIdx][j]; // 큐브는 돌리면 돌렸을때 0번 인덱스가 반드시 다음면 0번 인덱스가 되진않는다
			else for(int i=0; i<3; i++) temp.colors[i] = cube[index][srcIdx][i];
		}
		else {
			if(isReverse) for(int i=0, j=2; i<3; i++, j--) temp.colors[i] = cube[index][j][srcIdx];
			else for(int i=0; i<3; i++) temp.colors[i] = cube[index][i][srcIdx];
		}
		
		rotateList.add(temp);
	}
	
	private static void cubing(String command) {
		for(int i=0; i<rotateList.size(); i++) { // 4면 돌리기
			Rotate temp = rotateList.get(i);
			int index = table.get(temp.dest);
			
			if(temp.isRow) for(int j=0; j<3; j++) cube[index][temp.index][j] = temp.colors[j];
			else for(int j=0; j<3; j++) cube[index][j][temp.index] = temp.colors[j];
		}
		
		int index = table.get(command.charAt(0)); // 바라보는면 자기 자신 회전
		char isClockWise = command.charAt(1);
		
		char[][] copy = new char [3][3];
		for(int i=0; i<3; i++) {
			System.arraycopy(cube[index][i], 0, copy[i], 0, 3);
		}
		
		if(command.charAt(0) == 'B' || command.charAt(0) == 'D') { // 반대면은 앞면을 보고 기준으로 봤을때 시계로 돌려도 반시계방향이다
			if(isClockWise == '+') isClockWise = '-';
			else isClockWise = '+';
		}
		
		if(isClockWise == '+') { // 시계 방향
			cube[index][0][0] = copy[2][0];
			cube[index][0][1] = copy[1][0];
			cube[index][0][2] = copy[0][0];
			cube[index][1][0] = copy[2][1];
			cube[index][1][2] = copy[0][1];
			cube[index][2][0] = copy[2][2];
			cube[index][2][1] = copy[1][2];
			cube[index][2][2] = copy[0][2];
		}
		
		else {
			cube[index][0][0] = copy[0][2];
			cube[index][0][1] = copy[1][2];
			cube[index][0][2] = copy[2][2];
			cube[index][1][0] = copy[0][1];
			cube[index][1][2] = copy[2][1];
			cube[index][2][0] = copy[0][0];
			cube[index][2][1] = copy[1][0];
			cube[index][2][2] = copy[2][0];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 정답 입출력
		StringTokenizer st;
		
		table = new HashMap<>();
		table.put('F', 0);
		table.put('B', 1);
		table.put('L', 2);
		table.put('R', 3);
		table.put('U', 4);
		table.put('D', 5);
		
		resetChar = new HashMap<>();
		resetChar.put(0, 'F');
		resetChar.put(1, 'B');
		resetChar.put(2, 'L');
		resetChar.put(3, 'R');
		resetChar.put(4, 'U');
		resetChar.put(5, 'D');
		
		resetColor = new HashMap<>();
		resetColor.put('F', 'r');
		resetColor.put('B', 'o');
		resetColor.put('L', 'g');
		resetColor.put('R', 'b');
		resetColor.put('U', 'w');
		resetColor.put('D', 'y');
		
		cube = new char [6][3][3];
		rotateList = new ArrayList<>();
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine()); // 돌리는 명령 횟수
			cmList.clear();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				cmList.add(st.nextToken());
			}
			
			cubeReset();
			
			for(int i=0; i<cmList.size(); i++) {
				rotateCube(cmList.get(i));
			}
			
			int key = table.get('U');
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					sb.append(cube[key][i][j]);
				}
				sb.append("\n");
			}
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
