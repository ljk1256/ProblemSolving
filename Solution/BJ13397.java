package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ13397 {
	
	static int[] arr;
	static int M;
	
	private static boolean weatherIsPossible(int mid) { // 네이밍을 길게 해주는것
		int sectionCnt = 1; // 전체도 하나의 구간으로 볼 수 있기때문에 시작값은 1
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		
		for(int temp : arr) {
			int tempMin = Math.min(min, temp);
			int tempMax = Math.max(max, temp);
			
			if(tempMax - tempMin <= mid) {
				min = tempMin;
				max = tempMax;
			}
			else {
				max = min = temp; // 반복문은 else 문이 끝나면 다음으로 넘어가기에 그전에 값도 포함시켜야 한다 *** 
				sectionCnt++;
			}
			
			if(sectionCnt > M) return false;
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int [N];
		int left = 0, right = Integer.MIN_VALUE; // 구간에서 최소값은 항상 0이기때문
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, arr[i]);
		}
		
		int answer = Integer.MAX_VALUE;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			if(weatherIsPossible(mid)) { // 매게변수 탐색
				answer = Math.min(answer, mid);
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
		
		System.out.println(answer);
		
	}

}
