package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11053 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] array = new int [N+1];
		st = new StringTokenizer(br.readLine());
		
		for(int j=1; j<N+1; j++) {
			array[j] = Integer.parseInt(st.nextToken());
		}
		
		int max = Integer.MIN_VALUE;
		int[] Lis = new int [N+1];
		
		for(int i=1; i<N+1; i++) {
			Lis[i] = 1;
			for(int j=1; j<i; j++) {
				if(array[i] > array[j] && Lis[i] < Lis[j] + 1) { // 현재 시점 바로 이전 까지 반복 탐색하면서 비교 및 갱신 하는데 비교점이 바뀌면서 +1 값과 비교 하는것이 핵심
					Lis[i] = Lis[j] + 1;
				}
			}
			if(max < Lis[i]) { // 갱신과 동시에 최대, 최소 값 비교 갱신 (굳이 반복문을 다시 사용할 이유없음)
				max = Lis[i];
			}
		}
		
		System.out.println(max);
	}

}
