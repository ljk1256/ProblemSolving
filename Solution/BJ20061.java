package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20061 {
	
	static class Tile {
		
		int tileNum;
		int firstX;
		int firstY;
		int secondX;
		int secondY;
		
		public Tile(int tileNum, int firstX, int firstY, int secondX, int secondY) {
			super();
			this.tileNum = tileNum;
			this.firstX = firstX;
			this.firstY = firstY;
			this.secondX = secondX;
			this.secondY = secondY;
		}
		
	}
	
	static int[][] blue;
	static int[][] green;
	static int score;
	
	private static void dropBlock(boolean isGreen, int markNum, Tile tile) {
		int[][] tempBoard = new int [6][4];
		
		if(isGreen) {
			for(int i=0; i<6; i++) {
				System.arraycopy(green[i], 0, tempBoard[i], 0, 4);
			}
		}
		else {
			for(int i=0; i<6; i++) {
				System.arraycopy(blue[i], 0, tempBoard[i], 0, 4);
			}
		}
		
		// 일단 먼저 타일을 내려본다
		int stopRow = 0;
		
		if(tile.tileNum == 1) { // 1x1
			
			for(int i=0; i<=5; i++) {
				if(tempBoard[stopRow][tile.firstY] == 0) {
					if(i == 5) break;
					stopRow++;
				}
				else {
					stopRow--;
					break;
				}
			}
			
			tempBoard[stopRow][tile.firstY] = markNum;
		}
		
		else if(tile.tileNum == 2) { // 1x2
			
			for(int i=0; i<=5; i++) {
				if(tempBoard[stopRow][tile.firstY] == 0 && tempBoard[stopRow][tile.secondY] == 0) {
					if(i == 5) break;
					stopRow++;
				}
				else {
					stopRow--;
					break;
				}
			}
			
			tempBoard[stopRow][tile.firstY] = markNum;
			tempBoard[stopRow][tile.secondY] = markNum;
		}
		
		else { // 2x1
			stopRow = tile.secondX;
			
			for(int i=0; i<=4; i++) {
				if(tempBoard[stopRow][tile.firstY] == 0) {
					if(stopRow == 5) break;
					stopRow++;
				}
				else {
					stopRow--;
					break;
				}
			}
			
			tempBoard[stopRow-1][tile.firstY] = markNum;
			tempBoard[stopRow][tile.secondY] = markNum;
		}
		
		// 2~5번째 행에 한 줄 완성이 있는지 확인한다.
		
		for(int i=5; i>=2; i--) { // 제일 아래부터 확인
			boolean isFull = true;
			
			for(int j=0; j<=3; j++) {
				if(tempBoard[i][j] == 0) {
					isFull = false;
					break;
				}
			}
			
			if(isFull) { // 한 줄이 완성 됐다면
				for(int j=0; j<=3; j++) tempBoard[i][j] = 0; // 해당 라인 비우기
				
				for(int j=i-1; j>=0; j--) { // 윗 줄에 타일이 있다면 한칸 씩 내려준다
					for(int k=0; k<=3; k++) {
						if(tempBoard[j][k] != 0) {
							tempBoard[j+1][k] = tempBoard[j][k];
							tempBoard[j][k] = 0;
						}
					}
				}
				
				score++; // 점수 추가
				i=6;
			}
		}
		
		// 0~1번째 행에 타일이 존재 하는지 확인한다
		
		int specialRow = 0;
		
		for(int i=0; i<=1; i++) {
			for(int j=0; j<=3; j++) {
				if(tempBoard[i][j] != 0) {
					specialRow++;
					break;
				}
			}
		}
		
		if(specialRow > 0) { // 존재한다면
			/*for(int i=5; i>5-specialRow; i--) {
				for(int j=0; j<=3; j++) tempBoard[i][j] = 0;
			}
			*/
			for(int i=5-specialRow; i>=0; i--) {
				for(int j=0; j<=3; j++) {
					if(tempBoard[i][j] != 0) {
						tempBoard[i+specialRow][j] = tempBoard[i][j];
						tempBoard[i][j] = 0;
					}
				}
			}
		}
		
		if(isGreen) {
			for(int i=0; i<6; i++) {
				System.arraycopy(tempBoard[i], 0, green[i], 0, 4);
			}
		}
		else {
			for(int i=0; i<6; i++) {
				System.arraycopy(tempBoard[i], 0, blue[i], 0, 4);
			}
		}
	}
	
	private static void playGame(int markNum, int tileNum, int row, int column) {
		Tile blueTile;
		Tile greenTile;
		
		if(tileNum == 1) {
			blueTile = new Tile(tileNum, column, row, -1, -1);
			greenTile = new Tile(tileNum, row, column, -1, -1);
		}
		
		else if(tileNum == 2) {
			blueTile = new Tile(tileNum+1, column, row, column+1, row);
			greenTile = new Tile(tileNum, row, column, row, column+1);
		}
		
		else {
			blueTile = new Tile(tileNum-1, column, row, column, row+1);
			greenTile = new Tile(tileNum, row, column, row+1, column);
		}
		
		dropBlock(true, markNum, greenTile);
		dropBlock(false, markNum, blueTile);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int gameCnt = Integer.parseInt(br.readLine());
		
		blue = new int [6][4];
		green = new int [6][4];
		score = 0;
		
		for(int i=1; i<=gameCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int tileNum = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			int column = Integer.parseInt(st.nextToken());
			playGame(i, tileNum, row, column);
		}
		
		int tileCnt = 0;
		
		for(int i=2; i<=5; i++) {
			for(int j=0; j<=3; j++) {
				if(blue[i][j] != 0) tileCnt++;
				if(green[i][j] != 0) tileCnt++;
 			}
		}
		
		StringBuilder answerBuilder = new StringBuilder();
		
		answerBuilder.append(score).append("\n");
		answerBuilder.append(tileCnt);
		
		System.out.println(answerBuilder.toString());
	}

}
