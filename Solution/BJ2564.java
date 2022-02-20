package Solution;

import java.util.Scanner;

public class BJ2564 {
	
	static int[][] data;
	static int min = 0;
	static int target; // 배열의 크기는 target+1 이지만 동근이 정보 인덱스는 target
	static int row;
	static int column;
	
	static void one(int dis) {
		switch(data[target][0]) {
		case 1 : {
			min += Math.abs(dis - data[target][1]);
			break;
		}
		case 2 : {
			min += Math.min((dis + column + data[target][1]), (row - data[target][1] + column + row - dis));
			break;
		}
		case 3 : {
			min += data[target][1] + dis;
			break;
		}
		case 4 : {
			min += data[target][1] + row - dis;
			break;
		}
		}
	}
	static void two(int dis) {
		switch(data[target][0]) {
		case 1 : {
			min += Math.min((dis + column + data[target][1]), (row - data[target][1] + column + row - dis));
			break;
		}
		case 2 : {
			min += Math.abs(dis - data[target][1]);
			break;
		}
		case 3 : {
			min += column - data[target][1] + dis;
			break;
		}
		case 4 : {
			min += column - data[target][1] + row - dis;
			break;
		}
		}
	}
	static void three(int dis) {
		switch(data[target][0]) {
		case 1 : {
			min += data[target][1] + dis;
			break;
		}
		case 2 : {
			min += column - dis + data[target][1];
			break;
		}
		case 3 : {
			min += Math.abs(dis - data[target][1]);
			break;
		}
		case 4 : {
			min += Math.min(dis + row + data[target][1], column - dis + row + column - data[target][1]);
			break;
		}
		}
	}
	static void four(int dis) {
		switch(data[target][0]) {
		case 1 : {
			min += row - data[target][1] + dis;
			break;
		}
		case 2 : {
			min += column - dis + row - data[target][1];
			break;
		}
		case 3 : {
			min += Math.min(dis + row + data[target][1], column - dis + row + column - data[target][1]);
			break;
		}
		case 4 : {
			min += Math.abs(dis - data[target][1]);
			break;
		}
		}
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		row = sc.nextInt();  // 가로길이  [][*] 
		column = sc.nextInt(); // 세로길이 [*][]
		
		target = sc.nextInt();
		data = new int [target+1][2]; // 마지막 인덱스 동근이 정보
		
		for(int i=0; i<data.length; i++) {
			for(int j=0; j<2; j++) {
				data[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<target; i++) {
			switch(data[i][0]) {
			case 1 : {
				one(data[i][1]);
				break;
			}
			case 2 : {
				two(data[i][1]);
				break;
			}
			case 3 : {
				three(data[i][1]);
				break;
			}
			case 4 : {
				four(data[i][1]);
				break;
			}
			}
		}
		System.out.println(min);
	}

}
