package Solution;

public class test3 {

	public static void main(String[] args) {
		
		int[][] arr = new int [3][3];
		int cnt = 1;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				arr[i][j] = cnt++;
			}
		}
		
		char isClockWise = '-';
		
		int[][] copy = new int [3][3];
		for(int i=0; i<3; i++) {
			System.arraycopy(arr[i], 0, copy[i], 0, 3);
		}
		
		if(isClockWise == '+') { // 시계 방향
			arr[0][0] = copy[2][0];
			arr[0][1] = copy[1][0];
			arr[0][2] = copy[0][0];
			arr[1][0] = copy[2][1];
			arr[1][2] = copy[0][1];
			arr[2][0] = copy[2][2];
			arr[2][1] = copy[1][2];
			arr[2][2] = copy[0][2];
		}
		
		else {
			arr[0][0] = copy[0][2];
			arr[0][1] = copy[1][2];
			arr[0][2] = copy[2][2];
			arr[1][0] = copy[0][1];
			arr[1][2] = copy[2][1];
			arr[2][0] = copy[0][0];
			arr[2][1] = copy[1][0];
			arr[2][2] = copy[2][0];
		}
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}

}
