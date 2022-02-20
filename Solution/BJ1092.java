package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1092 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 크레인 수
		ArrayList<Integer> crainList = new ArrayList<>(N);
		
		st = new StringTokenizer(br.readLine()); // 크레인 무게제한 입력
		int maxCrain = 0;
		for(int i=0; i<N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			crainList.add(temp);
			maxCrain = Math.max(maxCrain, temp);
		}
		
		Collections.sort(crainList, Collections.reverseOrder()); // 무게제한이 높은 순서대로 정렬
		
		int M = Integer.parseInt(br.readLine()); // 박스의 수
		PriorityQueue<Integer> boxQ = new PriorityQueue<>(new Comparator<Integer>() { // 무거운 순서대로 정렬
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		st = new StringTokenizer(br.readLine()); // 옮겨야 할 박스 입력
		int maxBox = 0;
		for(int i=0; i<M; i++) {
			int temp = Integer.parseInt(st.nextToken());
			boxQ.offer(temp);
			maxBox = Math.max(maxBox, temp);
		}
		
		if(maxBox > maxCrain) { // 가장 큰 무게 제한보다 더 큰 박스가 있다면 작업 불가능
			System.out.println(-1);
			System.exit(0);
		}
		
		int minTime = 0;
		ArrayList<Integer> remainList = new ArrayList<>(); // 큐에서 뽑았는데 무게제한이 맞지 않아 다시 넣어야 하는경우 임시저장소
		
		while(!boxQ.isEmpty()) { // 박스를 모두 옮길때까지 수행
			minTime++; // 최소 시간 증가
			
			for(int i=0; i<crainList.size(); i++) {
				if(boxQ.isEmpty()) break; // 모두 옮겼다면 그만 둔다
				
				int tempBox = boxQ.poll();
				int tempCrain = crainList.get(i); // 현재 크레인이 들수 있는 무게제한
				
				if(tempCrain >= tempBox) continue; // 무게제한을 넘지 않는다면 옮긴다
				
				else {
					remainList.add(tempBox); // 임시저장소에 담아 두고
					
					while(!boxQ.isEmpty()) { // 현재 크레인이 들 수 있는 박스가 있는지 끝까지 탐색한다
						tempBox = boxQ.poll();
						if(tempCrain >= tempBox) break; // 찾으면 탈출
						else remainList.add(tempBox); // 아니면 임시 저장소에 담아두고 계속 탐색진행
					}
					
					for(int j=0; j<remainList.size(); j++) boxQ.offer(remainList.get(j)); // 탐색이 끝났다면 임시 저장소에 있던 박스들 다시 큐에 삽입
					remainList.clear(); // 리스트 초기화
				}
			}
		}
		
		System.out.println(minTime);
	}

}
