package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ18310 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> homeList = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int tempHome = Integer.parseInt(st.nextToken());
			homeList.add(tempHome);
		}
		
		Collections.sort(homeList);
		
		int midIdx = (N-1) / 2; // 가운데 인덱스 찾을때 먼저 1을 빼주고 나눠준다
		
		System.out.println(homeList.get(midIdx));
	}
}
