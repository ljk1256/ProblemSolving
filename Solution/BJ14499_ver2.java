package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14499_ver2 {
	
	static class Dice {
		
		int top;
		int bottom;
		int left;
		int right;
		int front;
		int back;
		
		public Dice(int top, int bottom, int left, int right, int front, int back) {
			super();
			this.top = top;
			this.bottom = bottom;
			this.left = left;
			this.right = right;
			this.front = front;
			this.back = back;
		}
	}
	
	static Dice tempDice;
	static int tempX, tempY;
	static int row, column;
	static Queue<Integer> commandQ;
	static StringBuilder answerBuilder;
	static int[][] map;
	static int[] dx = {0, 0, -1, +1};
	static int[] dy = {1, -1, 0, 0};
	
	private static void move(int command) {
		tempX += dx[command];
		tempY += dy[command];
		
		if(tempX < 0 || tempX >= row || tempY < 0 || tempY >= column) {
			tempX -= dx[command];
			tempY -= dy[command];
			return;
		}
		
		Dice newDice = null;
		
		if(command == 0) { // 동
			newDice = new Dice(tempDice.left, tempDice.right, tempDice.bottom, tempDice.top, tempDice.front, tempDice.back);
		}
		
		else if(command == 1) { // 서
			newDice = new Dice(tempDice.right, tempDice.left, tempDice.top, tempDice.bottom, tempDice.front, tempDice.back);
		}
		
		else if(command == 2) { // 북
			newDice = new Dice(tempDice.front, tempDice.back, tempDice.left, tempDice.right, tempDice.bottom, tempDice.top);
		}
		
		else { // 남
			newDice = new Dice(tempDice.back, tempDice.front, tempDice.left, tempDice.right, tempDice.top, tempDice.bottom);
		}
		
		// 맵 과 주사위 마킹
		if(map[tempX][tempY] == 0) {
			map[tempX][tempY] = newDice.bottom;
		}
		else {
			newDice.bottom = map[tempX][tempY];
			map[tempX][tempY] = 0;
		}
		
		tempDice = newDice; // 주사위 갱신
		answerBuilder.append(tempDice.top).append("\n");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		tempDice = new Dice(0, 0, 0, 0, 0, 0);
		
		row = Integer.parseInt(st.nextToken()); // 맵 크기
		column = Integer.parseInt(st.nextToken());
		
		int startX = Integer.parseInt(st.nextToken()); // 시작 좌표
		int startY = Integer.parseInt(st.nextToken());
		
		int commandCnt = Integer.parseInt(st.nextToken()); // 명령 힛수
		
		map = new int [row][column];
		
		for(int i=0; i<row; i++) { // 맵 초기화
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<column; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		commandQ = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<commandCnt; i++) { // 명령어
			commandQ.offer(Integer.parseInt(st.nextToken())-1);
		}
		
		tempX = startX;
		tempY = startY;
		
		answerBuilder = new StringBuilder();
		
		while(!commandQ.isEmpty()) {
			int tempCommand = commandQ.poll();
			move(tempCommand);
		}
		
		answerBuilder.setLength(answerBuilder.length()-1);
		System.out.println(answerBuilder.toString());
	}

}
