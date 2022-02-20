package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ2635 {
	
	static int maxAns;
	static ArrayList<Integer> ansList;
	
	private static void bruteForce(int prevprev, int prev, int cnt, ArrayList<Integer> tempList) {
		if(prevprev - prev < 0) { // 전전값 과 전값을 뺏을때 음수가 된다면
			if(maxAns < cnt) { // 현재 개수가 최대 개수라면
				maxAns = cnt; // 값 갱신 해준다
				ansList = tempList; // 리스트도 바꿔준다
			}
			return;
		}
		
		tempList.add(prevprev - prev); // 아직 양수라면 리스트에 넣고
		bruteForce(prev, prevprev - prev, cnt+1, tempList); // 다음 탐색 시작
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		maxAns = 0;
		ansList = new ArrayList<>();
		
		for(int i=1; i<=N; i++) { // N 보다 작거나 같은 값을 완전 탐색한다
			ArrayList<Integer> list = new ArrayList<>();
			list.add(N); // 최초 첫번째 값
			list.add(i); // 두번째 값 넣고
			bruteForce(N, i, 2, list); // 탐색 시작
		}
		
		StringBuilder sb = new StringBuilder(); // 출력 만들기
		sb.append(maxAns).append("\n");
		for(int i=0; i<ansList.size(); i++) {
			sb.append(ansList.get(i)).append(" ");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
 