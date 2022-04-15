package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ17140 {
	
	static class Number {
		
		int num;
		int cnt;
		
		public Number(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}
		
	}
	
	static int[][] arr;
	
	private static void operation() {
		int rowLen = 0;
		int colLen = 0;
		
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(arr[i][j] != 0) {
					rowLen = Math.max(rowLen, i);
					colLen = Math.max(colLen, j);
				}
			}
		}
		
		HashMap<Integer, Number> numMap = new HashMap<>();
		ArrayList<Number> list = new ArrayList<>();
		int[][] newArr = new int [100][100];
		
		if(rowLen >= colLen) { // 행 연산
			
			for(int i=0; i<=rowLen; i++) { // 행 연산을 열 만큼 반복
				
				numMap.clear();
				list.clear();
				
				for(int j=0; j<=colLen; j++) {
					if(arr[i][j] != 0) {
						if(numMap.containsKey(arr[i][j])) numMap.get(arr[i][j]).cnt++;
						else numMap.put(arr[i][j], new Number(arr[i][j], 1));
					}
				}
				
				numMap.forEach((key, value) -> {
					list.add(value);
				});
				
				Collections.sort(list, new Comparator<Number>() {
					@Override
					public int compare(Number o1, Number o2) {
						if(o1.cnt == o2.cnt) return o1.num - o2.num;
						else return o1.cnt - o2.cnt;
					}
				});
				
				if(list.size() > 50) { // 배열의 크기 100 보다 클 경우
					int listIdx = 0;
					
					for(int j=0; j<100; j+=2) {
						Number tempNum = list.get(listIdx);
						newArr[i][j] = tempNum.num;
						newArr[i][j+1] = tempNum.cnt;
					}
					
				}
				
				else {
					int arrIdx = 0;
					
					for(int j=0; j<list.size(); j++) {
						Number tempNum = list.get(j);
						newArr[i][arrIdx] = tempNum.num;
						newArr[i][arrIdx+1] = tempNum.cnt;
						arrIdx += 2;
					}
				}
			}
			
		}
		
		else { // 열 연산
			
			for(int i=0; i<=colLen; i++) { // 열 연산을 행 만큼 반복
				
				numMap.clear();
				list.clear();
				
				for(int j=0; j<=rowLen; j++) {
					if(arr[j][i] != 0) {
						if(numMap.containsKey(arr[j][i])) numMap.get(arr[j][i]).cnt++;
						else numMap.put(arr[j][i], new Number(arr[j][i], 1));
					}
				}
				
				numMap.forEach((key, value) -> {
					list.add(value);
				});
				
				Collections.sort(list, new Comparator<Number>() {
					@Override
					public int compare(Number o1, Number o2) {
						if(o1.cnt == o2.cnt) return o1.num - o2.num;
						else return o1.cnt - o2.cnt;
					}
				});
				
				if(list.size() > 50) { // 배열의 크기 100 보다 클 경우
					int listIdx = 0;
					
					for(int j=0; j<100; j+=2) {
						Number tempNum = list.get(listIdx);
						newArr[j][i] = tempNum.num;
						newArr[j+1][i] = tempNum.cnt;
					}
					
				}
				
				else {
					int arrIdx = 0;
					
					for(int j=0; j<list.size(); j++) {
						Number tempNum = list.get(j);
						newArr[arrIdx][i] = tempNum.num;
						newArr[arrIdx+1][i] = tempNum.cnt;
						arrIdx += 2;
					}
				}
			}
		}
		
		arr = newArr;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());
		
		arr = new int [100][100]; // 최초 배열 입력
		
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		
		while(time <= 100) {
			if(arr[r][c] == k) break;
			operation();
			time++;
		}
		
		System.out.println(time > 100 ? -1 : time);
	}

}
